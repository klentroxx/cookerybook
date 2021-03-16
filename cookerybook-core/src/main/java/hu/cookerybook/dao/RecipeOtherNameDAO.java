package hu.cookerybook.dao;

import hu.cookerybook.model.Recipe;
import hu.cookerybook.model.RecipeOtherName;

import java.sql.SQLException;
import java.util.List;

public interface RecipeOtherNameDAO {

    void addRecipeOtherName(RecipeOtherName recipeOtherName);

    void removeRecipeOtherName(int id);

    List<RecipeOtherName> getAllRecipeOtherNames() throws SQLException;

    List<RecipeOtherName> getRecipeNames(Recipe recipe) throws SQLException;

    RecipeOtherName getRecipeOtherName(int id) throws SQLException;

    void updateRecipeOtherName(RecipeOtherName recipeOtherName);
    
}
