package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
import hu.cookerybook.core.model.Recipe;
import hu.cookerybook.core.model.RecipeOtherName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RecipeOtherNameDAOImpl implements RecipeOtherNameDAO {
    @Override
    public void addRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "INSERT INTO recipe_other_names (recipe_id, recipe_name, created_at, updated_at) " +
                "VALUES(?, ?, " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", recipeOtherName.getRecipeId()));
        parameters.add(new PreparedStatementParameter(2, "string", recipeOtherName.getRecipeName()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public void removeRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "DELETE FROM recipe_other_names WHERE id=" + recipeOtherName.getId();
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", recipeOtherName.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public List<RecipeOtherName> getAllRecipeOtherNames() {
        List<RecipeOtherName> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipe_other_names";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabaseStat(queryString);

        for (String[] row : resultSet) {
            RecipeOtherName ron = new RecipeOtherName();
            ron.setId(parseInt(row[0]));
            ron.setRecipeId(parseInt(row[1]));
            ron.setRecipeName(row[2]);
            result.add(ron);
        }

        return result;
    }

    @Override
    public List<RecipeOtherName> getRecipeNames(Recipe recipe) {
        List<RecipeOtherName> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipe_other_names WHERE recipe_id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", recipe.getId()));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);

        for (String[] row : resultSet) {
            RecipeOtherName ron = new RecipeOtherName();
            ron.setId(parseInt(row[0]));
            ron.setRecipeId(parseInt(row[1]));
            ron.setRecipeName(row[2]);
            result.add(ron);
        }

        return result;
    }

    @Override
    public RecipeOtherName getRecipeOtherName(int id) {
        String queryString = "SELECT * FROM recipe_other_names WHERE recipe_id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", id));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
        String[] row = resultSet.get(0);

        RecipeOtherName ron = new RecipeOtherName();
        ron.setId(parseInt(row[0]));
        ron.setRecipeId(parseInt(row[1]));
        ron.setRecipeName(row[2]);

        return ron;
    }

    @Override
    public void updateRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "UPDATE recipe_other_names SET " +
                "recipe_id = ?, " +
                "recipe_name = ?, " +
                "updated_at = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id = ?;";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", recipeOtherName.getRecipeId()));
        parameters.add(new PreparedStatementParameter(2, "string", recipeOtherName.getRecipeName()));
        parameters.add(new PreparedStatementParameter(2, "string", recipeOtherName.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }
}
