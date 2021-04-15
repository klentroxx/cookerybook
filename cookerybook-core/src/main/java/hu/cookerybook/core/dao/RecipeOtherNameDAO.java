package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.Recipe;
import hu.cookerybook.core.model.RecipeOtherName;

import java.util.List;

public interface RecipeOtherNameDAO {

    void addRecipeOtherName(RecipeOtherName recipeOtherName);

    void removeRecipeOtherName(RecipeOtherName recipeOtherName);

    List<RecipeOtherName> getAllRecipeOtherNames();

    List<RecipeOtherName> getRecipeNames(Recipe recipe);

    RecipeOtherName getRecipeOtherName(int id);

    void updateRecipeOtherName(RecipeOtherName recipeOtherName);
    
}
