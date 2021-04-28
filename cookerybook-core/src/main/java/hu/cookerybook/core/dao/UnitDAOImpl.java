package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
import hu.cookerybook.core.model.Unit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class UnitDAOImpl implements UnitDAO {
    @Override
    public void addUnit(Unit unit) {
        String queryString = "INSERT INTO units (name, short_name, default_unit_id, unit_change, created_at, updated_at) " +
                "VALUES(?, ?, ?, ?, " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", unit.getName()));
        parameters.add(new PreparedStatementParameter(2, "string", unit.getShortName()));
        parameters.add(new PreparedStatementParameter(3, "int", unit.getDefaultUnitId()));
        parameters.add(new PreparedStatementParameter(4, "float", unit.getUnitChange()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public void removeUnit(Unit unit) {
        String queryString = "DELETE FROM units WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", unit.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public List<Unit> getAllUnits() {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT * FROM units";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabaseStat(queryString);

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
    public List<Unit> getAllParentUnits() {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT * FROM units WHERE default_unit_id = 0 AND unit_change = 0";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabaseStat(queryString);

        for (String[] row : resultSet) {
            Unit u = new Unit();
            u.setId(parseInt(row[0]));
            u.setName(row[1]);
            u.setShortName(row[2]);
            u.setDefaultUnitId(parseInt(row[3]));
            u.setUnitChange(parseInt(row[4]));
            result.add(u);
        }

        return result;
    }

    @Override
    public List<Unit> getChangesOfUnit(Unit unit) {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT * FROM units WHERE id = ? OR default_unit = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", unit.getId()));
        parameters.add(new PreparedStatementParameter(2, "int", unit.getId()));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);

        for (String[] row : resultSet) {
            Unit u = new Unit();
            u.setId(parseInt(row[0]));
            u.setName(row[1]);
            u.setShortName(row[2]);
            u.setDefaultUnitId(parseInt(row[3]));
            u.setUnitChange(parseInt(row[4]));
            result.add(u);
        }

        return result;
    }

    @Override
    public Unit getUnit(int id) {
        String queryString = "SELECT * FROM units WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", id));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
        String[] row = resultSet.get(0);

        Unit u = new Unit();

        u.setId(parseInt(row[0]));
        u.setName(row[1]);
        u.setShortName(row[2]);
        u.setDefaultUnitId(parseInt(row[3]));
        u.setUnitChange(parseInt(row[4]));

        return u;
    }

    @Override
    public void updateUnit(Unit unit) {
        String queryString = "UPDATE units SET " +
                "name = ?, " +
                "short_name = ?, " +
                "default_unit_id = ?, " +
                "unit_change = ?, " +
                "updated_at = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id = ?;";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", unit.getName()));
        parameters.add(new PreparedStatementParameter(2, "string", unit.getShortName()));
        parameters.add(new PreparedStatementParameter(3, "int", unit.getDefaultUnitId()));
        parameters.add(new PreparedStatementParameter(4, "float", unit.getUnitChange()));
        parameters.add(new PreparedStatementParameter(5, "int", unit.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }
}
