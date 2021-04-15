package hu.cookerybook.core.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Objects;

public class Recipe implements Serializable {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty photo = new SimpleStringProperty(this, "photo");
    private StringProperty directions = new SimpleStringProperty(this, "directions");
    private IntegerProperty difficulty = new SimpleIntegerProperty(this, "difficulty");
    private IntegerProperty timeToCook = new SimpleIntegerProperty(this, "timeToCook");
    private IntegerProperty servings = new SimpleIntegerProperty(this, "servings");
    private StringProperty category = new SimpleStringProperty(this, "category");
    private IntegerProperty createdById = new SimpleIntegerProperty(this, "createdById");

    public Recipe() {
    }

    public Recipe(StringProperty name, StringProperty photo, StringProperty directions, IntegerProperty difficulty, IntegerProperty timeToCook, IntegerProperty servings, StringProperty category, IntegerProperty createdById) {
        this.name = name;
        this.photo = photo;
        this.directions = directions;
        this.difficulty = difficulty;
        this.timeToCook = timeToCook;
        this.servings = servings;
        this.category = category;
        this.createdById = createdById;
    }

    public Recipe(IntegerProperty id, StringProperty name, StringProperty photo, StringProperty directions, IntegerProperty difficulty, IntegerProperty timeToCook, IntegerProperty servings, StringProperty category, IntegerProperty createdById) {
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

    public String getPhoto() {
        return photo.get();
    }

    public StringProperty photoProperty() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }

    public String getDirections() {
        return directions.get();
    }

    public StringProperty directionsProperty() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions.set(directions);
    }

    public int getDifficulty() {
        return difficulty.get();
    }

    public IntegerProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty.set(difficulty);
    }

    public int getTimeToCook() {
        return timeToCook.get();
    }

    public IntegerProperty timeToCookProperty() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook.set(timeToCook);
    }

    public int getServings() {
        return servings.get();
    }

    public IntegerProperty servingsProperty() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings.set(servings);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
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
