package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
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
                "VALUES('" + unit.getName() + "', " +
                "'" + unit.getShortName() + "', " +
                unit.getDefaultUnitId() + ", " +
                unit.getUnitChange() + ", " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public void removeUnit(Unit unit) {
        String queryString = "DELETE FROM units WHERE id=" + unit.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public List<Unit> getAllUnits() {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT * FROM units";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

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
        String queryString = "SELECT * FROM units WHERE id=" + unit.getId() + " OR default_unit=" + unit.getId();
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

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
        String queryString = "SELECT * FROM units WHERE id=" + id;
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);
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
                "name = '" + unit.getName() + "', " +
                "short_name = '" + unit.getShortName() + "', " +
                "default_unit_id = " + unit.getDefaultUnitId() + ", " +
                "unit_change = " + unit.getUnitChange() + ", " +
                "updated_at = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id = " + unit.getId() + ";";
        new DatabaseFunctions().setDataInDatabase(queryString);
    }
}
