package hu.cookerybook.core.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.util.Objects;

public class RequiredIngredient implements Serializable {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty recipeId = new SimpleIntegerProperty(this, "recipeId");
    private IntegerProperty ingredientId = new SimpleIntegerProperty(this, "ingredientId");
    private FloatProperty ingredientAmount = new SimpleFloatProperty(this, "ingredientAmount");

    public RequiredIngredient() {
    }

    public RequiredIngredient(IntegerProperty recipeId, IntegerProperty ingredientId, FloatProperty ingredientAmount) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientAmount = ingredientAmount;
    }

    public RequiredIngredient(IntegerProperty id, IntegerProperty recipeId, IntegerProperty ingredientId, FloatProperty ingredientAmount) {
        this.id = id;
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientAmount = ingredientAmount;
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

    public int getIngredientId() {
        return ingredientId.get();
    }

    public IntegerProperty ingredientIdProperty() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId.set(ingredientId);
    }

    public float getIngredientAmount() {
        return ingredientAmount.get();
    }

    public FloatProperty ingredientAmountProperty() {
        return ingredientAmount;
    }

    public void setIngredientAmount(float ingredientAmount) {
        this.ingredientAmount.set(ingredientAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequiredIngredient that = (RequiredIngredient) o;
        return id == that.id && recipeId == that.recipeId && ingredientId == that.ingredientId && ingredientAmount == that.ingredientAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeId, ingredientId, ingredientAmount);
    }

    @Override
    public String toString() {
        return "RequiredIngredient{" +
                "id=" + getId() +
                ", recipeId=" + getRecipeId() +
                ", ingredientId=" + getIngredientId() +
                ", ingredientAmount=" + getIngredientAmount() +
                '}';
    }
}
