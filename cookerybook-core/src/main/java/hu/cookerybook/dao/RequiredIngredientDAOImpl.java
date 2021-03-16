package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.RequiredIngredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequiredIngredientDAOImpl implements RequiredIngredientDAO {
    @Override
    public void addRequiredIngredient(RequiredIngredient requiredIngredient) {
        String queryString = "INSERT INTO required_ingredients SET " +
                "ingredient_id=" + requiredIngredient.getIngredientId() + ", " +
                "ingredient_name='" + requiredIngredient.getIngredientName() + "', " +
                "ingredient_amount=" + requiredIngredient.getIngredientAmount() + ";";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeRequiredIngredient(int id) {
        String queryString = "DELETE FROM required_ingredients WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<RequiredIngredient> getAllRequiredIngredients() throws SQLException {
        List<RequiredIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM required_ingredients";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            RequiredIngredient ri = new RequiredIngredient(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
            result.add(ri);
        }

        return result;
    }

    @Override
    public List<RequiredIngredient> getRequiredIngredients(int recipeId) throws SQLException {
        List<RequiredIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM required_ingredients WHERE recipe_id=" + recipeId;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            RequiredIngredient ri = new RequiredIngredient(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
            result.add(ri);
        }

        return result;
    }

    @Override
    public RequiredIngredient getRequiredIngredient(int id) throws SQLException {
        RequiredIngredient ri = null;
        String queryString = "SELECT * FROM required_ingredients";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        if (resultSet.next()) {
            ri = new RequiredIngredient(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
        }

        return ri;
    }

    @Override
    public void updateRequiredIngredient(RequiredIngredient requiredIngredient) {
        String queryString = "UPDATE required_ingredients SET " +
                "ingredient_id=" + requiredIngredient.getIngredientId() + ", " +
                "ingredient_name='" + requiredIngredient.getIngredientName() + "', " +
                "ingredient_amount=" + requiredIngredient.getIngredientAmount() + " " +
                "WHERE id=" + requiredIngredient.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
