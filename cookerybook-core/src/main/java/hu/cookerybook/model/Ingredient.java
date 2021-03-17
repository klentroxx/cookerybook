package hu.cookerybook.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable {
    @Getter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private int unitId;

    public Ingredient() {
    }

    public Ingredient(String name, int unitId) {
        this.name = name;
        this.unitId = unitId;
    }

    public Ingredient(int id, String name, int unitId) {
        this.id = id;
        this.name = name;
        this.unitId = unitId;
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
