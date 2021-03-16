package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Ingredient;
import hu.cookerybook.model.MenuRecipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public void addIngredient(Ingredient ingredient) {
        String queryString = "INSERT INTO ingredients SET " +
                "name=" + ingredient.getName() + ", " +
                "unit_id=" + ingredient.getUnitId() + ";";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeIngredient(int id) {
        String queryString = "DELETE FROM ingredients WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<Ingredient> getAllIngredients() throws SQLException {
        List<Ingredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM ingredients";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            Ingredient i = new Ingredient(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
            result.add(i);
        }

        return result;
    }

    @Override
    public Ingredient getIngredient(int id) throws SQLException {
        Ingredient result = null;
        String queryString = "SELECT * FROM ingredients WHERE id=" + id;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        if (resultSet.next()) {
            result = new Ingredient(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }

        return result;
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        String queryString = "INSERT INTO ingredients SET " +
                "name=" + ingredient.getName() + ", " +
                "unit_id=" + ingredient.getUnitId() + " " +
                "WHERE id=" + ingredient.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
