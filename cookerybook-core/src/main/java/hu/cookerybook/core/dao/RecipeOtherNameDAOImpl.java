package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
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
        String queryString = "INSERT INTO recipe_other_names (recipe_id, recipe_name) " +
                "VALUES(" + recipeOtherName.getRecipeId() + ", " +
                "'" + recipeOtherName.getRecipeName() + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public void removeRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "DELETE FROM recipe_other_names WHERE id=" + recipeOtherName.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public List<RecipeOtherName> getAllRecipeOtherNames() {
        List<RecipeOtherName> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipe_other_names";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

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
        String queryString = "SELECT * FROM recipe_other_names WHERE recipe_id=" + recipe.getId();
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

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
        String queryString = "SELECT * FROM recipe_other_names WHERE recipe_id=" + id;
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        RecipeOtherName ron = new RecipeOtherName();
        ron.setId(parseInt(row[0]));
        ron.setRecipeId(parseInt(row[1]));
        ron.setRecipeName(row[2]);

        return ron;
    }

    @Override
    public void updateRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "INSERT INTO recipe_other_names SET " +
                "recipe_id='" + recipeOtherName.getRecipeId() + "', " +
                "recipe_name=" + recipeOtherName.getRecipeName() + " " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + recipeOtherName.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }
}
