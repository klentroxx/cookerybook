package hu.cookerybook.web.controller;

import hu.cookerybook.core.dao.*;
import hu.cookerybook.core.model.Ingredient;
import hu.cookerybook.core.model.PantryIngredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/spajz")
public class PantryController {

    @GetMapping
    public String initializePage(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user_uid") != null) {
            IngredientDAO ingredientDAO = new IngredientDAOImpl();
            PantryDAO pantryDAO = new PantryDAOImpl();
            UnitDAO unitDAO = new UnitDAOImpl();

            List<Ingredient> visibleIngredients = ingredientDAO.getAllIngredients();
            List<PantryIngredient> pantryOfUser = pantryDAO.getPantryIngredients(Integer.parseInt(request.getSession().getAttribute("user_uid").toString()));



            model.addAttribute("select_ingredients", visibleIngredients);
            model.addAttribute("ingredient_dao", ingredientDAO);
            model.addAttribute("pantry_of_user", pantryOfUser);
            model.addAttribute("pantry_dao", pantryDAO);
            model.addAttribute("unit_dao", unitDAO);

            return "pages/pantry";
        } else {
            response.sendRedirect("/bejelentkezes");
            return null;
        }
    }

    @PostMapping("/feltolt")
    public void getAndCheckData (HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user_uid") != null) {

            int userId = Integer.parseInt(request.getSession().getAttribute("user_uid").toString());
            int ingredientId = stringToInteger(request.getParameter("ingredient"));
            float quantity = stringToInteger(request.getParameter("quantity"));
            float minimumAmount = stringToFloat(request.getParameter("minimum_amount"));

            String errorMessage = "";

            if (ingredientId <= 0 || quantity == 0) {
                errorMessage += "Az \"Alapanyag\" és a \"Vásárolt alapanyag mennyisége\" mezők kitöltése kötelező!";
            }
            if (quantity <= 0) {
                errorMessage += "A vásárolt alapanyag mennyiségének nagyobbnak kell lenni, mint 0!<br>";
            }
            if (minimumAmount < 0) {
                errorMessage += "A minimális mennyiség nem lehet kisebb, mint 0!<br>";
            }

            if (errorMessage.equals("")) {
                uploadPantryIngredient(userId, ingredientId, quantity, minimumAmount);
                request.getSession().setAttribute("recipe_response", "Az alapanyag sikeresen feltöltve!");
            } else {
                request.getSession().setAttribute("recipe_response", errorMessage);
            }
            response.sendRedirect("/spajz");
        } else {
            response.sendRedirect("/bejelentkezes");
        }
    }

    @PostMapping("/torol")
    public void deletePantryItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user_uid") != null) {

            int pantryItemId = stringToInteger(request.getParameter("pantry_item_del"));
            PantryDAO pantryDAO = new PantryDAOImpl();

            List<PantryIngredient> pantryOfUser = pantryDAO.getPantryIngredients(Integer.parseInt(request.getSession().getAttribute("user_uid").toString()));

            for (PantryIngredient pi : pantryOfUser) {
                if (pi.getId() == pantryItemId) {
                    pantryDAO.removePantryIngredient(pi);
                    break;
                }
            }

            request.getSession().setAttribute("recipe_response", "Az alapanyag sikeresen törölve!");
            response.sendRedirect("/spajz");
        } else {
            response.sendRedirect("/bejelentkezes");
        }
    }

    @PostMapping("/modosit")
    public void modifyPantryItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user_uid") != null) {

            int pantryItemId = stringToInteger(request.getParameter("pantry_item_mod"));
            float pantryItemQuantity = stringToFloat(request.getParameter("pantry_item_quantity"));
            PantryDAO pantryDAO = new PantryDAOImpl();

            List<PantryIngredient> pantryOfUser = pantryDAO.getPantryIngredients(Integer.parseInt(request.getSession().getAttribute("user_uid").toString()));

            for (PantryIngredient pi : pantryOfUser) {
                if (pi.getId() == pantryItemId) {
                    pi.setIngredientQuantity(pantryItemQuantity);
                    pantryDAO.updatePantryIngredient(pi);
                    break;
                }
            }

            request.getSession().setAttribute("recipe_response", "Az alapanyag sikeresen módosítva!");
            response.sendRedirect("/spajz");
        } else {
            response.sendRedirect("/bejelentkezes");
        }
    }

    private void uploadPantryIngredient(int userId, int ingredientId, float quantity, float minimumAmount) {
        PantryDAO pantryDAO = new PantryDAOImpl();
        PantryIngredient pantryIngredient = new PantryIngredient();
        List<PantryIngredient> userList = pantryDAO.getPantryIngredients(userId);

        pantryIngredient.setUserId(userId);
        pantryIngredient.setIngredientId(ingredientId);
        pantryIngredient.setIngredientQuantity(quantity);
        pantryIngredient.setMinimumAmount(minimumAmount);

        for (PantryIngredient pi : userList) {
            if (pi.getIngredientId() == ingredientId) {
                pi.setIngredientQuantity(pi.getIngredientQuantity() + quantity);
                pi.setMinimumAmount(minimumAmount);
                pantryDAO.updatePantryIngredient(pi);
                return;
            }
        }

        pantryDAO.addPantryIngredient(pantryIngredient);
    }

    private Integer stringToInteger(String number) {
        int integerNumber = 0;
        try {
            integerNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("NaN.");
        }
        return integerNumber;
    }

    private Float stringToFloat(String number) {
        float floatNumber = (float) 0;
        try {
            floatNumber = Float.parseFloat(number);
        } catch (NumberFormatException e) {
            System.out.println("NaN.");
        }
        return floatNumber;
    }

}
