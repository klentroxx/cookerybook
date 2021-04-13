package hu.cookerybook.dao;

import hu.cookerybook.model.Recipe;

import java.util.List;

public interface RecipeDAO {

    void addRecipe(Recipe recipe);

    void removeRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();

    Recipe getRecipe(int id);

    void updateRecipe(Recipe recipe);

}
