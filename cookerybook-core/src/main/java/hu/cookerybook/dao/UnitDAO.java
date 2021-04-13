package hu.cookerybook.dao;

import hu.cookerybook.model.Unit;

import java.util.List;

public interface UnitDAO {
    
    void addUnit(Unit unit);

    void removeUnit(Unit unit);

    List<Unit> getAllUnits();

    List<Unit> getChangesOfUnit(Unit unit);

    Unit getUnit(int id);

    void updateUnit(Unit unit);
    
}
