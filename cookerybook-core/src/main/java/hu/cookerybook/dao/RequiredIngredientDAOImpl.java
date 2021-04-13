package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Recipe;
import hu.cookerybook.model.RequiredIngredient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class RequiredIngredientDAOImpl implements RequiredIngredientDAO {
    @Override
    public void addRequiredIngredient(RequiredIngredient requiredIngredient) {
        String queryString = "INSERT INTO required_ingredients (recipe_id, ingredient_id, ingredient_amount) " +
                "VALUES(" + requiredIngredient.getRecipeId() + ", " +
                requiredIngredient.getIngredientId() + ", " +
                requiredIngredient.getIngredientAmount() + ", " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeRequiredIngredient(RequiredIngredient requiredIngredient) {
        String queryString = "DELETE FROM required_ingredients WHERE id=" + requiredIngredient.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<RequiredIngredient> getAllRequiredIngredients() {
        List<RequiredIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM required_ingredients";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new RequiredIngredient(parseInt(row[0]), parseInt(row[1]), parseInt(row[2]), parseFloat(row[4])));
        }

        return result;
    }

    @Override
    public List<RequiredIngredient> getRequiredIngredients(Recipe recipe) {
        List<RequiredIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM required_ingredients WHERE recipe_id=" + recipe.getId();
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new RequiredIngredient(parseInt(row[0]), parseInt(row[1]), parseInt(row[2]), parseFloat(row[4])));
        }

        return result;
    }

    @Override
    public RequiredIngredient getRequiredIngredient(int id) {
        String queryString = "SELECT * FROM required_ingredients";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        return new RequiredIngredient(parseInt(row[0]), parseInt(row[1]), parseInt(row[2]), parseFloat(row[4]));
    }

    @Override
    public void updateRequiredIngredient(RequiredIngredient requiredIngredient) {
        String queryString = "UPDATE required_ingredients SET " +
                "recipe_id=" + requiredIngredient.getRecipeId() + ", " +
                "ingredient_id=" + requiredIngredient.getIngredientId() + ", " +
                "ingredient_amount=" + requiredIngredient.getIngredientAmount() + " " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + requiredIngredient.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
