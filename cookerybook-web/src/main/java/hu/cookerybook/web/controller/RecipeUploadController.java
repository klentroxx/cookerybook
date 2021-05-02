package hu.cookerybook.web.controller;

import hu.cookerybook.core.dao.IngredientDAOImpl;
import hu.cookerybook.core.model.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/recept-feltoltes")
public class RecipeUploadController {

    @GetMapping
    public String initializeForm(Model model) {
        List<Ingredient> ingredients = new IngredientDAOImpl().getAllIngredients();
        model.addAttribute("ingredients", ingredients);
        return "pages/new_recipe_form";
    }

    @PostMapping("/feltolt")
    public @ResponseBody
    float[] uploadRecipe(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String[] otherNames = request.getParameter("other_names").split(",");
        String directions = request.getParameter("directions");
        int difficulty = Integer.parseInt(request.getParameter("difficulty"));
        int time_to_cook = Integer.parseInt(request.getParameter("time_to_cook"));
        int[] requiredIngredients = new int[60];
        float[] quantity = new float[60];
        int servings = Integer.parseInt(request.getParameter("servings"));
        String category = request.getParameter("category");

        int i = 0;
        while (request.getParameter("ingredient_name" + i) != null) {
            requiredIngredients[i] = Integer.parseInt(request.getParameter("ingredient_name" + i));
            i++;
        }
        i=0;
        while (request.getParameter("quantity" + i) != null) {
            quantity[i] = Float.parseFloat(request.getParameter("quantity" + i));
            i++;
        }

        return quantity;
    }


}
