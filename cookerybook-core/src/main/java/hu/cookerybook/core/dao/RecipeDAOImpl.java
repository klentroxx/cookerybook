package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
import hu.cookerybook.core.model.Recipe;
import hu.cookerybook.core.model.RecipeOtherName;
import hu.cookerybook.core.model.RequiredIngredient;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

public class RecipeDAOImpl implements RecipeDAO {

    @Override
    public void addRecipe(Recipe recipe, List<RecipeOtherName> recipeOtherNames, List<RequiredIngredient> requiredIngredients) {
        Map<String, List<PreparedStatementParameter>> transaction = new HashMap<>();
        List<PreparedStatementParameter> recipeParameters = new ArrayList<>();

        String queryString = "INSERT INTO recipes (name, photo, directions, difficulty, time_to_cook, servings, category, created_by, created_at, updated_at) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";

        recipeParameters.add(new PreparedStatementParameter(1, "string", recipe.getName()));
        recipeParameters.add(new PreparedStatementParameter(2, "string", recipe.getPhoto()));
        recipeParameters.add(new PreparedStatementParameter(3, "string", recipe.getDirections()));
        recipeParameters.add(new PreparedStatementParameter(4, "int", recipe.getDifficulty()));
        recipeParameters.add(new PreparedStatementParameter(5, "int", recipe.getTimeToCook()));
        recipeParameters.add(new PreparedStatementParameter(6, "int", recipe.getServings()));
        recipeParameters.add(new PreparedStatementParameter(7, "string", recipe.getCategory()));
        recipeParameters.add(new PreparedStatementParameter(8, "int", recipe.getCreatedById()));

        transaction.put(queryString, recipeParameters);

        if (recipeOtherNames != null) {
            StringBuilder otherNamesQuery = new StringBuilder();
            List<PreparedStatementParameter> parameters = new ArrayList<>();
            int index = 0;

            for (RecipeOtherName ron : recipeOtherNames) {
                otherNamesQuery.append("((SELECT MAX(recipes.id) FROM recipes), ?, ")
                        .append("'").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("', ")
                        .append("'").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("')");
                if (index + 1 == recipeOtherNames.size()) {
                    otherNamesQuery.append(";");
                } else {
                    otherNamesQuery.append(", ");
                }
                parameters.add(new PreparedStatementParameter(++index, "string", ron.getRecipeName()));
            }

            String finalQuery = "INSERT INTO recipe_other_names (recipe_id, recipe_name, created_at, updated_at) VALUES " + otherNamesQuery;

            transaction.put(finalQuery, parameters);
        }
        if (requiredIngredients != null) {
            StringBuilder requiredIngredientsQuery = new StringBuilder();
            List<PreparedStatementParameter> parameters = new ArrayList<>();
            int paramIndex = 0;
            int loopIndex = 0;

            for (RequiredIngredient ri : requiredIngredients) {
                requiredIngredientsQuery.append("((SELECT MAX(recipes.id) FROM recipes), ?, ?, ")
                        .append("'").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("', ")
                        .append("'").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("')");
                if (loopIndex + 1 == requiredIngredients.size()) {
                    requiredIngredientsQuery.append(";");
                } else {
                    requiredIngredientsQuery.append(", ");
                }
                parameters.add(new PreparedStatementParameter(++paramIndex, "int", ri.getIngredientId()));
                parameters.add(new PreparedStatementParameter(++paramIndex, "float", ri.getIngredientAmount()));
                loopIndex++;
            }

            String finalQuery = "INSERT INTO required_ingredients (recipe_id, ingredient_id, ingredient_amount, created_at, updated_at) VALUES " + requiredIngredientsQuery;


            transaction.put(finalQuery, parameters);
        }

        new DatabaseFunctions().setTransactionInDatabase(transaction);
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
