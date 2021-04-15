package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.Recipe;

import java.util.List;

public interface RecipeDAO {

    void addRecipe(Recipe recipe);

    void removeRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();

    Recipe getRecipe(int id);

    void updateRecipe(Recipe recipe);

}
