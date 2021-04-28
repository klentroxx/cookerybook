package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
import hu.cookerybook.core.model.Ingredient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public void addIngredient(Ingredient ingredient) {
        String queryString = "INSERT INTO ingredients (name, unit_id, created_at, updated_at) " +
                "VALUES(?, ?, " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", ingredient.getName()));
        parameters.add(new PreparedStatementParameter(2, "int", ingredient.getUnitId()));

        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public void removeIngredient(Ingredient ingredient) {
        String queryString = "DELETE FROM ingredients WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", ingredient.getId()));

        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM ingredients";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabaseStat(queryString);

        for (String[] row : resultSet) {
            Ingredient i = new Ingredient();
            i.setId(parseInt(row[0]));
            i.setName(row[1]);
            i.setUnitId(parseInt(row[2]));
            result.add(i);
        }

        return result;
    }

    @Override
    public Ingredient getIngredient(int id) {
        String queryString = "SELECT * FROM ingredients WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", id));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
        String[] row = resultSet.get(0);

        Ingredient i = new Ingredient();
        i.setId(parseInt(row[0]));
        i.setName(row[1]);
        i.setUnitId(parseInt(row[2]));

        return i;
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        String queryString = "INSERT INTO ingredients SET " +
                "name = ?, " +
                "unit_id = ?, " +
                "updated_at = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id = ?;";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", ingredient.getName()));
        parameters.add(new PreparedStatementParameter(2, "int", ingredient.getUnitId()));
        parameters.add(new PreparedStatementParameter(3, "int", ingredient.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }
}
