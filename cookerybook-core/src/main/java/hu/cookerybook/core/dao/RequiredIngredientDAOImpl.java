package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.model.Recipe;
import hu.cookerybook.core.model.RequiredIngredient;

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
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public void removeRequiredIngredient(RequiredIngredient requiredIngredient) {
        String queryString = "DELETE FROM required_ingredients WHERE id=" + requiredIngredient.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public List<RequiredIngredient> getAllRequiredIngredients() {
        List<RequiredIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM required_ingredients";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            RequiredIngredient ri = new RequiredIngredient();
            ri.setId(parseInt(row[0]));
            ri.setRecipeId(parseInt(row[0]));
            ri.setIngredientId(parseInt(row[0]));
            ri.setIngredientAmount(parseFloat(row[0]));
            result.add(ri);
        }

        return result;
    }

    @Override
    public List<RequiredIngredient> getRequiredIngredients(Recipe recipe) {
        List<RequiredIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM required_ingredients WHERE recipe_id=" + recipe.getId();
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            RequiredIngredient ri = new RequiredIngredient();
            ri.setId(parseInt(row[0]));
            ri.setRecipeId(parseInt(row[0]));
            ri.setIngredientId(parseInt(row[0]));
            ri.setIngredientAmount(parseFloat(row[0]));
            result.add(ri);
        }

        return result;
    }

    @Override
    public RequiredIngredient getRequiredIngredient(int id) {
        String queryString = "SELECT * FROM required_ingredients";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        RequiredIngredient ri = new RequiredIngredient();
        ri.setId(parseInt(row[0]));
        ri.setRecipeId(parseInt(row[0]));
        ri.setIngredientId(parseInt(row[0]));
        ri.setIngredientAmount(parseFloat(row[0]));

        return ri;
    }

    @Override
    public void updateRequiredIngredient(RequiredIngredient requiredIngredient) {
        String queryString = "UPDATE required_ingredients SET " +
                "recipe_id=" + requiredIngredient.getRecipeId() + ", " +
                "ingredient_id=" + requiredIngredient.getIngredientId() + ", " +
                "ingredient_amount=" + requiredIngredient.getIngredientAmount() + " " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + requiredIngredient.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }
}
