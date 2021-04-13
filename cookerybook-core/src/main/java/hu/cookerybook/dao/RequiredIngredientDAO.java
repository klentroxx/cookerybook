package hu.cookerybook.dao;

import hu.cookerybook.model.Recipe;
import hu.cookerybook.model.RequiredIngredient;

import java.util.List;

public interface RequiredIngredientDAO {

    void addRequiredIngredient(RequiredIngredient requiredIngredient);

    void removeRequiredIngredient(RequiredIngredient requiredIngredient);

    List<RequiredIngredient> getAllRequiredIngredients();

    List<RequiredIngredient> getRequiredIngredients(Recipe recipe);

    RequiredIngredient getRequiredIngredient(int id);

    void updateRequiredIngredient(RequiredIngredient requiredIngredient);

}
