package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.Recipe;
import hu.cookerybook.core.model.RequiredIngredient;

import java.util.List;

public interface RequiredIngredientDAO {

    void addRequiredIngredient(RequiredIngredient requiredIngredient);

    void removeRequiredIngredient(RequiredIngredient requiredIngredient);

    List<RequiredIngredient> getAllRequiredIngredients();

    List<RequiredIngredient> getRequiredIngredients(Recipe recipe);

    RequiredIngredient getRequiredIngredient(int id);

    void updateRequiredIngredient(RequiredIngredient requiredIngredient);

}
