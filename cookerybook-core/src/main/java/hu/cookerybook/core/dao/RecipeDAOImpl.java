package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.model.Recipe;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        String queryString = "DELETE FROM recipes WHERE id=" + recipe.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipes";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            Recipe r = new Recipe();
            r.setId(parseInt(row[0]));
            r.setName(row[1]);
            r.setPhoto(row[2]);
            r.setDirections(row[3]);
            r.setDifficulty(parseInt(row[4]));
            r.setTimeToCook(parseInt(row[5]));
            r.setServings(parseInt(row[6]));
            r.setCategory(row[7]);
            r.setCreatedById(parseInt(row[8]));
            result.add(r);
        }

        return result;
    }

    @Override
    public Recipe getRecipe(int id) {
        String queryString = "SELECT * FROM recipes WHERE id=" + id;
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        Recipe r = new Recipe();
        r.setId(parseInt(row[0]));
        r.setName(row[1]);
        r.setPhoto(row[2]);
        r.setDirections(row[3]);
        r.setDifficulty(parseInt(row[4]));
        r.setTimeToCook(parseInt(row[5]));
        r.setServings(parseInt(row[6]));
        r.setCategory(row[7]);
        r.setCreatedById(parseInt(row[8]));

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
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + recipe.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

}
