package hu.cookerybook.dao;

import hu.cookerybook.model.Recipe;
import hu.cookerybook.model.RecipeOtherName;

import java.util.List;

public interface RecipeOtherNameDAO {

    void addRecipeOtherName(RecipeOtherName recipeOtherName);

    void removeRecipeOtherName(RecipeOtherName recipeOtherName);

    List<RecipeOtherName> getAllRecipeOtherNames();

    List<RecipeOtherName> getRecipeNames(Recipe recipe);

    RecipeOtherName getRecipeOtherName(int id);

    void updateRecipeOtherName(RecipeOtherName recipeOtherName);
    
}
