package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Recipe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RecipeDAOImpl implements RecipeDAO {

    @Override
    public void addRecipe(Recipe recipe) {
        String queryString = "INSERT INTO users (name, photo, directions, difficulty, time_to_cook, servings, category, created_by) " +
                "VALUES('" + recipe.getName() + "', " +
                "'" + recipe.getPhoto() + "', " +
                "'" + recipe.getDirections() + "', " +
                recipe.getDifficulty() + ", " +
                recipe.getTimeToCook() + ", " +
                recipe.getServings() + ", " +
                "'" + recipe.getCategory() + "', " +
                recipe.getServings() + ", " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        String queryString = "DELETE FROM recipes WHERE id=" + recipe.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipes";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new Recipe(parseInt(row[0]), row[1], row[2], row[3], parseInt(row[4]), parseInt(row[5]), parseInt(row[6]), row[7], parseInt(row[8])));
        }

        return result;
    }

    @Override
    public Recipe getRecipe(int id) {
        String queryString = "SELECT * FROM recipes WHERE id=" + id;
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        return new Recipe(parseInt(row[0]), row[1], row[2], row[3], parseInt(row[4]), parseInt(row[5]), parseInt(row[6]), row[7], parseInt(row[8]));
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
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + recipe.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

}
