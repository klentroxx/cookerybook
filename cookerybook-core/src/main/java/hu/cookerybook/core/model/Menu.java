package hu.cookerybook.core.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Objects;

public class Menu implements Serializable {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private IntegerProperty createdById = new SimpleIntegerProperty(this, "createdById");

    public Menu() {
    }

    public Menu(StringProperty name, IntegerProperty createdById) {
        this.name = name;
        this.createdById = createdById;
    }

    public Menu(IntegerProperty id, StringProperty name, IntegerProperty createdById) {
        this.id = id;
        this.name = name;
        this.createdById = createdById;
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

    public int getCreatedById() {
        return createdById.get();
    }

    public IntegerProperty createdByIdProperty() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById.set(createdById);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id && createdById == menu.createdById && name.equals(menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdById);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", createdById=" + getCreatedById() +
                '}';
    }
}
