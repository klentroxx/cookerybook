package hu.cookerybook.dao;

import hu.cookerybook.model.PantryIngredient;

import java.sql.SQLException;
import java.util.List;

public interface PantryDAO {

    void addPantryIngredient(PantryIngredient pantryIngredient);

    void removePantryIngredient(int id);

    List<PantryIngredient> getAllPantryIngredients() throws SQLException;

    List<PantryIngredient> getPantryIngredients(int userId) throws SQLException;

    PantryIngredient getPantryIngredient(int id) throws SQLException;

    void updatePantryIngredient(PantryIngredient pantryIngredient);


}
