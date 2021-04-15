package hu.cookerybook.core.model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.util.Objects;

public class Unit implements Serializable {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty shortName = new SimpleStringProperty(this, "shortName");
    private IntegerProperty defaultUnitId = new SimpleIntegerProperty(this, "defaultUnitId");
    private FloatProperty unitChange = new SimpleFloatProperty(this, "unitChange");

    public Unit() {
    }

    public Unit(StringProperty name) {
        this.name = name;
    }

    public Unit(StringProperty name, StringProperty shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public Unit(StringProperty name, IntegerProperty defaultUnitId, FloatProperty unitChange) {
        this.name = name;
        this.defaultUnitId = defaultUnitId;
        this.unitChange = unitChange;
    }

    public Unit(StringProperty name, StringProperty shortName, IntegerProperty defaultUnitId, FloatProperty unitChange) {
        this.name = name;
        this.shortName = shortName;
        this.defaultUnitId = defaultUnitId;
        this.unitChange = unitChange;
    }

    public Unit(IntegerProperty id, StringProperty name) {
        this.id = id;
        this.name = name;
    }

    public Unit(IntegerProperty id, StringProperty name, StringProperty shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public Unit(IntegerProperty id, StringProperty name, StringProperty shortName, IntegerProperty defaultUnitId, FloatProperty unitChange) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.defaultUnitId = defaultUnitId;
        this.unitChange = unitChange;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getShortName() {
        return shortName.get();
    }

    public StringProperty shortNameProperty() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName.set(shortName);
    }

    public int getDefaultUnitId() {
        return defaultUnitId.get();
    }

    public IntegerProperty defaultUnitIdProperty() {
        return defaultUnitId;
    }

    public void setDefaultUnitId(int defaultUnitId) {
        this.defaultUnitId.set(defaultUnitId);
    }

    public float getUnitChange() {
        return unitChange.get();
    }

    public FloatProperty unitChangeProperty() {
        return unitChange;
    }

    public void setUnitChange(float unitChange) {
        this.unitChange.set(unitChange);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(id, unit.id) && Objects.equals(name, unit.name) && Objects.equals(shortName, unit.shortName) && Objects.equals(defaultUnitId, unit.defaultUnitId) && Objects.equals(unitChange, unit.unitChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, defaultUnitId, unitChange);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name=" + name +
                ", shortName=" + shortName +
                ", defaultUnitId=" + defaultUnitId +
                ", unitChange=" + unitChange +
                '}';
    }
}
