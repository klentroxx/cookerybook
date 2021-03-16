package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {

    @Override
    public void addRecipe(Recipe recipe) {
        String queryString = "INSERT INTO users SET " +
                "name='" + recipe.getName() + "', " +
                "photo='" + recipe.getPhoto() + "', " +
                "directions='" + recipe.getDirections() + "', " +
                "difficulty=" + recipe.getDifficulty() + ", " +
                "time_to_cook=" + recipe.getTimeToCook() + ", " +
                "servings=" + recipe.getServings() + ", " +
                "category='" + recipe.getCategory() + "', " +
                "created_by=" + recipe.getServings() + ";";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeRecipe(int id) {
        String queryString = "DELETE FROM recipes WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<Recipe> getAllRecipes() throws SQLException {
        List<Recipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipes";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        while (resultSet.next()) {
            Recipe r = new Recipe(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getString(9),
                    resultSet.getInt(10)
            );
            result.add(r);
        }

        return result;
    }

    @Override
    public Recipe getRecipe(int id) throws SQLException {
        Recipe r = null;
        String queryString = "SELECT * FROM recipes WHERE id=" + id;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        if (resultSet.next()) {
            r = new Recipe(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getString(9),
                    resultSet.getInt(10)
            );
        }

        return r;
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        String queryString = "UPDATE users SET " +
                "name='" + recipe.getName() + "', " +
                "photo='" + recipe.getPhoto() + "', " +
                "directions='" + recipe.getDirections() + "', " +
                "difficulty=" + recipe.getDifficulty() + ", " +
                "time_to_cook=" + recipe.getTimeToCook() + ", " +
                "servings=" + recipe.getServings() + ", " +
                "category='" + recipe.getCategory() + "', " +
                "created_by=" + recipe.getServings() + " " +
                "WHERE id=" + recipe.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

}
