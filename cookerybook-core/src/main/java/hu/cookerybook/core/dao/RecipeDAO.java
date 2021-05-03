package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.Recipe;
import hu.cookerybook.core.model.RecipeOtherName;
import hu.cookerybook.core.model.RequiredIngredient;

import java.util.List;

public interface RecipeDAO {

    void addRecipe(Recipe recipe, List<RecipeOtherName> recipeOtherNames, List<RequiredIngredient> requiredIngredients);

    void removeRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();

    Recipe getRecipe(int id);

    void updateRecipe(Recipe recipe);

}
