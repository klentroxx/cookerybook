package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDAOImpl implements UnitDAO {
    @Override
    public void addUnit(Unit unit) {
        String queryString = "INSERT INTO units SET " +
                "name='" + unit.getName() + "', " +
                "default_unit=" + unit.getDefaultUnitId() + ", " +
                "unit_change=" + unit.getUnitChange() + ";";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeUnit(int id) {
        String queryString = "DELETE FROM users WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<Unit> getAllUnits() throws SQLException {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT * FROM units";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        while (resultSet.next()) {
            Unit u = new Unit(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
            result.add(u);
        }

        return result;
    }

    @Override
    public List<Unit> getChangesOfUnit(Unit unit) throws SQLException {
        List<Unit> result = new ArrayList<>();
        String queryString = "SELECT * FROM units WHERE id=" + unit.getId() + " OR default_unit=" + unit.getId();
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            Unit u = new Unit(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
            result.add(u);
        }

        return result;
    }

    @Override
    public Unit getUnit(int id) throws SQLException {
        Unit result = null;
        String queryString = "SELECT * FROM units WHERE id=" + id;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        if (resultSet.next()) {
            result = new Unit(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
        }

        return result;
    }

    @Override
    public void updateUnit(Unit unit) {
        String queryString = "INSERT INTO units SET " +
                "name='" + unit.getName() + "', " +
                "default_unit=" + unit.getDefaultUnitId() + ", " +
                "unit_change=" + unit.getUnitChange() + ", " +
                "WHERE id=" + unit.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
