package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
import hu.cookerybook.core.model.Recipe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RecipeDAOImpl implements RecipeDAO {

    @Override
    public void addRecipe(Recipe recipe) {
        String queryString = "INSERT INTO users (name, photo, directions, difficulty, time_to_cook, servings, category, created_by, created_at, updated_at) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", recipe.getName()));
        parameters.add(new PreparedStatementParameter(2, "string", recipe.getPhoto()));
        parameters.add(new PreparedStatementParameter(3, "string", recipe.getDirections()));
        parameters.add(new PreparedStatementParameter(4, "int", recipe.getDifficulty()));
        parameters.add(new PreparedStatementParameter(5, "int", recipe.getTimeToCook()));
        parameters.add(new PreparedStatementParameter(6, "int", recipe.getServings()));
        parameters.add(new PreparedStatementParameter(7, "string", recipe.getCategory()));
        parameters.add(new PreparedStatementParameter(8, "int", recipe.getCreatedById()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        String queryString = "DELETE FROM recipes WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", recipe.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipes";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabaseStat(queryString);

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
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", id));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
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
                "name = ?, " +
                "photo = ?, " +
                "directions = ?, " +
                "difficulty = ?, " +
                "time_to_cook = ?, " +
                "servings = ?, " +
                "category = ?, " +
                "created_by = ?, " +
                "updated_at = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id = ?;" + recipe.getId();
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", recipe.getName()));
        parameters.add(new PreparedStatementParameter(2, "string", recipe.getPhoto()));
        parameters.add(new PreparedStatementParameter(3, "string", recipe.getDirections()));
        parameters.add(new PreparedStatementParameter(4, "int", recipe.getDifficulty()));
        parameters.add(new PreparedStatementParameter(5, "int", recipe.getTimeToCook()));
        parameters.add(new PreparedStatementParameter(6, "int", recipe.getServings()));
        parameters.add(new PreparedStatementParameter(7, "string", recipe.getCategory()));
        parameters.add(new PreparedStatementParameter(8, "int", recipe.getCreatedById()));
        parameters.add(new PreparedStatementParameter(9, "int", recipe.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

}
