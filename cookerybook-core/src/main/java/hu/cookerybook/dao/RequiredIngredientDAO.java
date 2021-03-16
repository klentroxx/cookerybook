package hu.cookerybook.dao;

import hu.cookerybook.model.RequiredIngredient;

import java.sql.SQLException;
import java.util.List;

public interface RequiredIngredientDAO {

    void addRequiredIngredient(RequiredIngredient requiredIngredient);

    void removeRequiredIngredient(int id);

    List<RequiredIngredient> getAllRequiredIngredients() throws SQLException;

    List<RequiredIngredient> getRequiredIngredients(int recipeId) throws SQLException;

    RequiredIngredient getRequiredIngredient(int id) throws SQLException;

    void updateRequiredIngredient(RequiredIngredient requiredIngredient);

}
