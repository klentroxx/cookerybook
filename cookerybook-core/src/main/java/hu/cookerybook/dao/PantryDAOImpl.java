package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.PantryIngredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PantryDAOImpl implements PantryDAO {
    @Override
    public void addPantryIngredient(PantryIngredient pantryIngredient) {
        String queryString = "INSERT INTO pantry SET " +
                "user_id=" + pantryIngredient.getUserId() + ", " +
                "ingredient_id=" + pantryIngredient.getIngredientId() + ", " +
                "ingredient_quantity=" + pantryIngredient.getIngredientQuantity() + ", " +
                "minimum_amount=" + pantryIngredient.getMinimumAmount() + ";";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removePantryIngredient(int id) {
        String queryString = "DELETE FROM pantry WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<PantryIngredient> getAllPantryIngredients() throws SQLException {
        List<PantryIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM pantry";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        while (resultSet.next()) {
            PantryIngredient pi = new PantryIngredient(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getFloat(5)
            );
            result.add(pi);
        }

        return result;
    }

    @Override
    public List<PantryIngredient> getPantryIngredients(int userId) throws SQLException {
        List<PantryIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM pantry WHERE user_id=" + userId;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        while (resultSet.next()) {
            PantryIngredient pi = new PantryIngredient(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getFloat(5)
            );
            result.add(pi);
        }

        return result;
    }

    @Override
    public PantryIngredient getPantryIngredient(int id) throws SQLException {
        PantryIngredient pi = null;
        String queryString = "SELECT * FROM pantry WHERE id=" + id;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        if (resultSet.next()) {
            pi = new PantryIngredient(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getFloat(5)
            );
        }

        return pi;
    }

    @Override
    public void updatePantryIngredient(PantryIngredient pantryIngredient) {
        String queryString = "UPDATE pantry SET " +
                "user_id=" + pantryIngredient.getUserId() + ", " +
                "ingredient_id=" + pantryIngredient.getIngredientId() + ", " +
                "ingredient_quantity=" + pantryIngredient.getIngredientQuantity() + ", " +
                "minimum_amount=" + pantryIngredient.getMinimumAmount() + " " +
                "WHERE id=" + pantryIngredient.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
