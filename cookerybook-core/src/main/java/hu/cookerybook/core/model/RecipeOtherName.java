package hu.cookerybook.core.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Objects;

public class RecipeOtherName implements Serializable {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty recipeId = new SimpleIntegerProperty(this, "recipeId");
    private StringProperty recipeName = new SimpleStringProperty(this, "recipeName");

    public RecipeOtherName() {
    }

    public RecipeOtherName(IntegerProperty recipeId, StringProperty recipeName) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
    }

    public RecipeOtherName(IntegerProperty id, IntegerProperty recipeId, StringProperty recipeName) {
        this.id = id;
        this.recipeId = recipeId;
        this.recipeName = recipeName;
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

    public int getRecipeId() {
        return recipeId.get();
    }

    public IntegerProperty recipeIdProperty() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId.set(recipeId);
    }

    public String getRecipeName() {
        return recipeName.get();
    }

    public StringProperty recipeNameProperty() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName.set(recipeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeOtherName that = (RecipeOtherName) o;
        return id == that.id && recipeId == that.recipeId && recipeName.equals(that.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeId, recipeName);
    }

    @Override
    public String toString() {
        return "RecipeOtherNames{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                '}';
    }
}
