package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Recipe;
import hu.cookerybook.model.RecipeOtherName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeOtherNameDAOImpl implements RecipeOtherNameDAO {
    @Override
    public void addRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "INSERT INTO recipe_other_names SET " +
                "recipe_id='" + recipeOtherName.getRecipeId() + "', " +
                "recipe_name='" + recipeOtherName.getRecipeName() + "';";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeRecipeOtherName(int id) {
        String queryString = "DELETE FROM recipe_other_names WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<RecipeOtherName> getAllRecipeOtherNames() throws SQLException {
        List<RecipeOtherName> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipe_other_names";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            RecipeOtherName ron = new RecipeOtherName(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3)
            );
            result.add(ron);
        }

        return result;
    }

    @Override
    public List<RecipeOtherName> getRecipeNames(Recipe recipe) throws SQLException {
        List<RecipeOtherName> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipe_other_names WHERE recipe_id=" + recipe.getId();
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            RecipeOtherName ron = new RecipeOtherName(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3)
            );
            result.add(ron);
        }

        return result;
    }

    @Override
    public RecipeOtherName getRecipeOtherName(int id) throws SQLException {
        RecipeOtherName result = null;
        String queryString = "SELECT * FROM recipe_other_names WHERE recipe_id=" + id;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        if (resultSet.next()) {
            result = new RecipeOtherName(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3)
            );
        }

        return result;
    }

    @Override
    public void updateRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "INSERT INTO recipe_other_names SET " +
                "recipe_id='" + recipeOtherName.getRecipeId() + "', " +
                "recipe_name=" + recipeOtherName.getRecipeName() + " " +
                "WHERE id=" + recipeOtherName.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
