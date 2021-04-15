package hu.cookerybook.core.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class RecipeOtherName implements Serializable {
    @Getter private int id;
    @Getter @Setter private int recipeId;
    @Getter @Setter private String recipeName;

    public RecipeOtherName() {
    }

    public RecipeOtherName(int recipeId, String recipeName) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
    }

    public RecipeOtherName(int id, int recipeId, String recipeName) {
        this.id = id;
        this.recipeId = recipeId;
        this.recipeName = recipeName;
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
