package hu.cookerybook.dao;

import hu.cookerybook.model.Recipe;

import java.sql.SQLException;
import java.util.List;

public interface RecipeDAO {

    void addRecipe(Recipe recipe);

    void removeRecipe(int id);

    List<Recipe> getAllRecipes() throws SQLException;

    Recipe getRecipe(int id) throws SQLException;

    void updateRecipe(Recipe recipe);

}
