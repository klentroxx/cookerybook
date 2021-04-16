package hu.cookerybook.core.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.util.Objects;

public class PantryIngredient implements Serializable {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty userId = new SimpleIntegerProperty(this, "userId");
    private IntegerProperty ingredientId = new SimpleIntegerProperty(this, "ingredientId");
    private IntegerProperty ingredientQuantity = new SimpleIntegerProperty(this, "ingredientQuantity");
    private FloatProperty minimumAmount = new SimpleFloatProperty(this, "ingredientQuantity");

    public PantryIngredient() {
    }

    public PantryIngredient(IntegerProperty userId, IntegerProperty ingredientId, IntegerProperty ingredientQuantity) {
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.ingredientQuantity = ingredientQuantity;
    }

    public PantryIngredient(IntegerProperty userId, IntegerProperty ingredientId, IntegerProperty ingredientQuantity, FloatProperty minimumAmount) {
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.ingredientQuantity = ingredientQuantity;
        this.minimumAmount = minimumAmount;
    }

    public PantryIngredient(IntegerProperty id, IntegerProperty userId, IntegerProperty ingredientId, IntegerProperty ingredientQuantity) {
        this.id = id;
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.ingredientQuantity = ingredientQuantity;
    }

    public PantryIngredient(IntegerProperty id, IntegerProperty userId, IntegerProperty ingredientId, IntegerProperty ingredientQuantity, FloatProperty minimumAmount) {
        this.id = id;
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.ingredientQuantity = ingredientQuantity;
        this.minimumAmount = minimumAmount;
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

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
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

    public int getIngredientQuantity() {
        return ingredientQuantity.get();
    }

    public IntegerProperty ingredientQuantityProperty() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(int ingredientQuantity) {
        this.ingredientQuantity.set(ingredientQuantity);
    }

    public float getMinimumAmount() {
        return minimumAmount.get();
    }

    public FloatProperty minimumAmountProperty() {
        return minimumAmount;
    }

    public void setMinimumAmount(float minimumAmount) {
        this.minimumAmount.set(minimumAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PantryIngredient that = (PantryIngredient) o;
        return Objects.equals(id, that.id) && userId.equals(that.userId) && ingredientId.equals(that.ingredientId) && ingredientQuantity.equals(that.ingredientQuantity) && Objects.equals(minimumAmount, that.minimumAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, ingredientId, ingredientQuantity, minimumAmount);
    }

    @Override
    public String toString() {
        return "PantryIngredient{" +
                "id=" + getId() +
                ", userId=" + getUserId() +
                ", ingredientId=" + getIngredientId() +
                ", ingredientQuantity=" + getIngredientQuantity() +
                ", minimumAmount=" + getMinimumAmount() +
                '}';
    }
}
