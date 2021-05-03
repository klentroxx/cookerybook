package hu.cookerybook.web.controller;

import hu.cookerybook.core.dao.IngredientDAOImpl;
import hu.cookerybook.core.dao.RecipeDAO;
import hu.cookerybook.core.dao.RecipeDAOImpl;
import hu.cookerybook.core.model.Ingredient;
import hu.cookerybook.core.model.Recipe;
import hu.cookerybook.core.model.RecipeOtherName;
import hu.cookerybook.core.model.RequiredIngredient;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/recept-feltoltes")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5)
public class RecipeUploadController extends HttpServlet {

    @GetMapping
    public String initializeForm(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user_uid") != null) {
            List<Ingredient> ingredients = new IngredientDAOImpl().getAllIngredients();
            model.addAttribute("ingredients", ingredients);
            return "pages/new_recipe_form";
        } else {
            response.sendRedirect("/bejelentkezes");
            return null;
        }
    }

    @PostMapping("/feltolt")
    public @ResponseBody
    String getAndCheckData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user_uid") != null) {
            String errorMessage = "";

            String name = request.getParameter("name");
            String[] otherNames = request.getParameter("other_names").split(",");
            if (otherNames.length > 0) {
                for (int i = 0; i < otherNames.length; i++) {
                    otherNames[i] = otherNames[i].trim();
                }
            }

            Part photo = request.getPart("photo");
            String photoName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
            String photoExtension = FilenameUtils.getExtension(photoName);
            byte[] photoContent = photo.getInputStream().readAllBytes();
            String encodedString = Base64.getEncoder().encodeToString(photoContent);

            String directions = request.getParameter("directions");
            int difficulty = stringToInteger(request.getParameter("difficulty"));
            int timeToCook = stringToInteger(request.getParameter("time_to_cook"));
            List<Integer> requiredIngredients = new ArrayList<>();
            List<Float> quantity = new ArrayList<>();
            int servings = stringToInteger(request.getParameter("servings"));
            String category = request.getParameter("category");

            int i = 0;
            while (request.getParameter("ingredient_name" + i) != null) {
                int ingredientId = stringToInteger(request.getParameter("ingredient_name" + i));
                requiredIngredients.add(ingredientId);
                i++;
            }
            i = 0;
            while (request.getParameter("quantity" + i) != null) {
                float quantityNumber = stringToFloat(request.getParameter("quantity" + i));
                quantity.add(quantityNumber);
                i++;
            }

            if (name.equals("")) {
                errorMessage += "Az étel nevének kitöltése kötelező!<br>";
            }
            if (directions.equals("")) {
                errorMessage += "Kötelező elkészítési útmutatót írni!<br>";
            }
            if (difficulty < 1 || difficulty > 10) {
                errorMessage += "A nehézséget 1-től 10-ig tartó skálán kell megadni!<br>";
            }
            if (timeToCook < 1) {
                errorMessage += "Az elkészítési idő nem lehet kevesebb 1 percnél!<br>";
            }
            if (quantity.get(0) <= 0 || requiredIngredients.get(0) <= 0) {
                errorMessage += "Kötelező megadni legalább 1 hozzávalót és annak a mennyiségét!<br>";
            }
            if (quantity.size() != requiredIngredients.size()) {
                errorMessage += "Közelező megadni a hozzávaló nevét és annak mennyiségét!<br>";
            }
            if (servings < 1) {
                errorMessage += "Az adagok száma kötelezően legalább 1!<br>";
            }
            if (category.equals("")) {
                errorMessage += "Kötelező megadni a kategóriát!<br>";
            }
            if (!encodedString.equals("") && !photoExtension.equals("") && (!photoExtension.equals("jpg") && !photoExtension.equals("jpeg") && !photoExtension.equals("png"))) {
                errorMessage += "A kép kiterjesztése nem megfelelő! Megengedett kiterjesztések: jpg, jpeg, png.<br>";
            }
            if (!request.getSession().getAttribute("user_user_role").equals(1) && !request.getSession().getAttribute("user_user_role").equals(2)) {
                errorMessage += "Ez a felhasználó nem jogosult a feltöltésre!<br>";
            }

            if (errorMessage.equals("")) {
                request.getSession().setAttribute("recipe_response", "A recept sikeresen feltöltve!");
                response.sendRedirect("/recept-feltoltes");
                uploadRecipe(name, otherNames, encodedString, directions, difficulty, timeToCook, servings, category, Integer.parseInt(request.getSession().getAttribute("user_uid").toString()), requiredIngredients, quantity);
                return "A recept sikeresen feltöltve!";
            } else {
                request.getSession().setAttribute("recipe_response", errorMessage);
                response.sendRedirect("/recept-feltoltes");
                return errorMessage;
            }
        } else {
            response.sendRedirect("/bejelentkezes");
            return "Előbb be kell jelentkezned!";
        }
    }

    private void uploadRecipe(String name, String[] otherNames, String encodedImage, String directions, int difficulty, int timeToCook, int servings, String category, int userId, List<Integer> requiredIngredients, List<Float> quantity) {
        RecipeDAO recipeDAO = new RecipeDAOImpl();
        Recipe recipe = new Recipe(new SimpleStringProperty(name), new SimpleStringProperty(encodedImage), new SimpleStringProperty(directions), new SimpleIntegerProperty(difficulty), new SimpleIntegerProperty(timeToCook), new SimpleIntegerProperty(servings), new SimpleStringProperty(category), new SimpleIntegerProperty(userId));
        List<RecipeOtherName> recipeOtherNamesList = new ArrayList<>();
        List<RequiredIngredient> ingredientsList = new ArrayList<>();

        if (!otherNames[0].equals("")) {
            for (String otherName : otherNames) {
                RecipeOtherName ron = new RecipeOtherName();
                ron.setRecipeName(otherName);
                recipeOtherNamesList.add(ron);
            }
        }

        if (!requiredIngredients.isEmpty() && !quantity.isEmpty()) {
            for (int index = 0; index < requiredIngredients.size(); index++) {
                RequiredIngredient ri = new RequiredIngredient();
                ri.setIngredientId(requiredIngredients.get(index));
                ri.setIngredientAmount(quantity.get(index));
                ingredientsList.add(ri);
            }
        }

        recipeDAO.addRecipe(recipe, recipeOtherNamesList, ingredientsList);
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
