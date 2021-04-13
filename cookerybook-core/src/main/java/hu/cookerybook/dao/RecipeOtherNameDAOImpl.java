package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Recipe;
import hu.cookerybook.model.RecipeOtherName;

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
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "DELETE FROM recipe_other_names WHERE id=" + recipeOtherName.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<RecipeOtherName> getAllRecipeOtherNames() {
        List<RecipeOtherName> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipe_other_names";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new RecipeOtherName(parseInt(row[0]), parseInt(row[1]), row[2]));
        }

        return result;
    }

    @Override
    public List<RecipeOtherName> getRecipeNames(Recipe recipe) {
        List<RecipeOtherName> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipe_other_names WHERE recipe_id=" + recipe.getId();
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new RecipeOtherName(parseInt(row[0]), parseInt(row[1]), row[2]));
        }

        return result;
    }

    @Override
    public RecipeOtherName getRecipeOtherName(int id) {
        String queryString = "SELECT * FROM recipe_other_names WHERE recipe_id=" + id;
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        return new RecipeOtherName(parseInt(row[0]), parseInt(row[1]), row[2]);
    }

    @Override
    public void updateRecipeOtherName(RecipeOtherName recipeOtherName) {
        String queryString = "INSERT INTO recipe_other_names SET " +
                "recipe_id='" + recipeOtherName.getRecipeId() + "', " +
                "recipe_name=" + recipeOtherName.getRecipeName() + " " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + recipeOtherName.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
