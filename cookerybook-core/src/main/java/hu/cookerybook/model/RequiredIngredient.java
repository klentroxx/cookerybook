package hu.cookerybook.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class RequiredIngredient implements Serializable {
    @Getter private int id;
    @Getter @Setter private int recipeId;
    @Getter @Setter private int ingredientId;
    @Getter @Setter private String ingredientName;
    @Getter @Setter private int ingredientAmount;

    public RequiredIngredient() {
    }

    public RequiredIngredient(int recipeId, int ingredientId, String ingredientName, int ingredientAmount) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientAmount = ingredientAmount;
    }

    public RequiredIngredient(int id, int recipeId, int ingredientId, String ingredientName, int ingredientAmount) {
        this.id = id;
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientAmount = ingredientAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequiredIngredient that = (RequiredIngredient) o;
        return id == that.id && recipeId == that.recipeId && ingredientId == that.ingredientId && ingredientAmount == that.ingredientAmount && ingredientName.equals(that.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeId, ingredientId, ingredientName, ingredientAmount);
    }

    @Override
    public String toString() {
        return "RequiredIngredient{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", ingredientId=" + ingredientId +
                ", ingredientName='" + ingredientName + '\'' +
                ", ingredientAmount=" + ingredientAmount +
                '}';
    }
}
