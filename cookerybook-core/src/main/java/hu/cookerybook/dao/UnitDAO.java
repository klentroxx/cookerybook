package hu.cookerybook.dao;

import hu.cookerybook.model.Unit;

import java.sql.SQLException;
import java.util.List;

public interface UnitDAO {
    
    void addUnit(Unit unit);

    void removeUnit(int id);

    List<Unit> getAllUnits() throws SQLException;

    List<Unit> getChangesOfUnit(Unit unit) throws SQLException;

    Unit getUnit(int id) throws SQLException;

    void updateUnit(Unit unit);
    
}
