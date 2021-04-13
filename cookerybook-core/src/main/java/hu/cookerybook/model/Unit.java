package hu.cookerybook.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class Unit implements Serializable {
    @Getter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private String shortName;
    @Getter @Setter private int defaultUnitId;
    @Getter @Setter private float unitChange;

    public Unit() {
    }

    public Unit(String name) {
        this.name = name;
    }

    public Unit(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public Unit(String name, int defaultUnitId, float unitChange) {
        this.name = name;
        this.defaultUnitId = defaultUnitId;
        this.unitChange = unitChange;
    }

    public Unit(String name, String shortName, int defaultUnitId, float unitChange) {
        this.name = name;
        this.shortName = shortName;
        this.defaultUnitId = defaultUnitId;
        this.unitChange = unitChange;
    }

    public Unit(int id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public Unit(int id, String name, String shortName, int defaultUnitId, float unitChange) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.defaultUnitId = defaultUnitId;
        this.unitChange = unitChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return id == unit.id && defaultUnitId == unit.defaultUnitId && Float.compare(unit.unitChange, unitChange) == 0 && name.equals(unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, defaultUnitId, unitChange);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", defaultUnitId=" + defaultUnitId +
                ", unitChange=" + unitChange +
                '}';
    }
}
