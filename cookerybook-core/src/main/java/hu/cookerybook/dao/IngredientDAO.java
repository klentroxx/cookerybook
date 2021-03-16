package hu.cookerybook.dao;

import hu.cookerybook.model.Ingredient;

import java.sql.SQLException;
import java.util.List;

public interface IngredientDAO {

    void addIngredient(Ingredient ingredient);

    void removeIngredient(int id);

    List<Ingredient> getAllIngredients() throws SQLException;

    Ingredient getIngredient(int id) throws SQLException;

    void updateIngredient(Ingredient ingredient);
    
}
