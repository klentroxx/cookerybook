package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Unit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class UnitDAOImpl implements UnitDAO {
    @Override
    public void addUnit(Unit unit) {
        String queryString = "INSERT INTO units (name, default_unit_id, unit_change, created_at, updated_at) " +
                "VALUES('" + unit.getName() + "', " +
                unit.getDefaultUnitId() + ", " +
                unit.getUnitChange() + ", " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeUnit(Unit unit) {
        String queryString = "DELETE FROM units WHERE id=" + unit.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<Unit> getAllUnits() {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT * FROM units";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new Unit(parseInt(row[0]), row[1], parseInt(row[2]), parseFloat(row[3])));
        }

        return result;
    }

    @Override
    public List<Unit> getChangesOfUnit(Unit unit) {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT * FROM units WHERE id=" + unit.getId() + " OR default_unit=" + unit.getId();
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new Unit(parseInt(row[0]), row[1], parseInt(row[2]), parseFloat(row[3])));
        }

        return result;
    }

    @Override
    public Unit getUnit(int id) {
        String queryString = "SELECT * FROM units WHERE id=" + id;
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        return new Unit(parseInt(row[0]), row[1], parseInt(row[2]), parseFloat(row[3]));
    }

    @Override
    public void updateUnit(Unit unit) {
        String queryString = "UPDATE units SET " +
                "name='" + unit.getName() + "', " +
                "default_unit=" + unit.getDefaultUnitId() + ", " +
                "unit_change=" + unit.getUnitChange() + ", " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + unit.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
