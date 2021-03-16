package hu.cookerybook.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Menu {
    @Getter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private int createdById;

    public Menu(String name, int createdById) {
        this.name = name;
        this.createdById = createdById;
    }

    public Menu(int id, String name, int createdById) {
        this.id = id;
        this.name = name;
        this.createdById = createdById;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdById=" + createdById +
                '}';
    }
}
