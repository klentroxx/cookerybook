package hu.cookerybook.web.controller;

import hu.cookerybook.core.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        RecipeDAO recipeDAO = new RecipeDAOImpl();
        RequiredIngredientDAO requiredIngredientDAO = new RequiredIngredientDAOImpl();
        RecipeOtherNameDAO recipeOtherNameDAO = new RecipeOtherNameDAOImpl();
        IngredientDAO ingredientDAO = new IngredientDAOImpl();
        UnitDAO unitDAO = new UnitDAOImpl();

        model.addAttribute("recipes", recipeDAO.getAllRecipes());
        model.addAttribute("recipe_other_names", recipeOtherNameDAO.getAllRecipeOtherNames());
        model.addAttribute("ingredients_dao", ingredientDAO);
        model.addAttribute("unit_dao", unitDAO);
        model.addAttribute("required_ingredients", requiredIngredientDAO.getAllRequiredIngredients());

        return "index";
    }

}
