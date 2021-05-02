package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
import hu.cookerybook.core.model.Ingredient;
import hu.cookerybook.core.model.Unit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Float.parseFloat;
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
    public List<Ingredient> getAllIngredientsSortedByName() {
        List<Ingredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM ingredients GROUP BY name";
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
    public Unit getUnitOfIngredient(Ingredient ingredient) {
        String queryString = "SELECT * FROM units WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", ingredient.getUnitId()));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
        String[] row = resultSet.get(0);

        Unit u = new Unit();

        u.setId(parseInt(row[0]));
        u.setName(row[1]);
        u.setShortName(row[2]);
        u.setDefaultUnitId(parseInt(row[3]));
        u.setUnitChange(parseFloat(row[4]));

        return u;
    }

    @Override
    public List<Unit> getAllUnitsOfIngredient(Ingredient ingredient) {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT units.id, units.name, units.short_name, units.default_unit_id, units.unit_change FROM units LEFT JOIN ingredients i on units.id = i.unit_id WHERE i.name = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", ingredient.getName()));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);

        for (String[] row : resultSet) {
            Unit u = new Unit();
            u.setId(parseInt(row[0]));
            u.setName(row[1]);
            u.setShortName(row[2]);
            u.setDefaultUnitId(parseInt(row[3]));
            u.setUnitChange(parseFloat(row[4]));
            result.add(u);
        }

        return result;
    }


    @Override
    public void updateIngredient(Ingredient ingredient) {
        String queryString = "UPDATE ingredients SET " +
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
