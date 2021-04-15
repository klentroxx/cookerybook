package hu.cookerybook.core.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class Recipe implements Serializable {
    @Getter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private String photo;
    @Getter @Setter private String directions;
    @Getter @Setter private int difficulty;
    @Getter @Setter private int timeToCook;
    @Getter @Setter private int servings;
    @Getter @Setter private String category;
    @Getter @Setter private int createdById;

    public Recipe() {
    }

    public Recipe(String name, String directions, int difficulty, int timeToCook, int servings, String category, int createdById) {
        this.name = name;
        this.directions = directions;
        this.difficulty = difficulty;
        this.timeToCook = timeToCook;
        this.servings = servings;
        this.category = category;
        this.createdById = createdById;
    }

    public Recipe(String name, String photo, String directions, int difficulty, int timeToCook, int servings, String category, int createdById) {
        this.name = name;
        this.photo = photo;
        this.directions = directions;
        this.difficulty = difficulty;
        this.timeToCook = timeToCook;
        this.servings = servings;
        this.category = category;
        this.createdById = createdById;
    }

    public Recipe(int id, String name, String photo, String directions, int difficulty, int timeToCook, int servings, String category, int createdById) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.directions = directions;
        this.difficulty = difficulty;
        this.timeToCook = timeToCook;
        this.servings = servings;
        this.category = category;
        this.createdById = createdById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && difficulty == recipe.difficulty && timeToCook == recipe.timeToCook && servings == recipe.servings && createdById == recipe.createdById && name.equals(recipe.name) && Objects.equals(photo, recipe.photo) && directions.equals(recipe.directions) && category.equals(recipe.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, photo, directions, difficulty, timeToCook, servings, category, createdById);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", timeToCook=" + timeToCook +
                ", servings=" + servings +
                ", category='" + category + '\'' +
                ", createdById=" + createdById +
                '}';
    }
}
