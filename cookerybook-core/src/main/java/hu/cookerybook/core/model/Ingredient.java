package hu.cookerybook.core.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private IntegerProperty unitId = new SimpleIntegerProperty(this, "unitId");

    public Ingredient() {
    }

    public Ingredient(StringProperty name, IntegerProperty unitId) {
        this.name = name;
        this.unitId = unitId;
    }

    public Ingredient(IntegerProperty id, StringProperty name, IntegerProperty unitId) {
        this.id = id;
        this.name = name;
        this.unitId = unitId;
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

    public int getUnitId() {
        return unitId.get();
    }

    public IntegerProperty unitIdProperty() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId.set(unitId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id && unitId == that.unitId && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitId);
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitId=" + unitId +
                '}';
    }
}
