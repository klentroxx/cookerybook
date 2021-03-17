package hu.cookerybook.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class PantryIngredient implements Serializable {
    @Getter private int id;
    @Getter @Setter private int userId;
    @Getter @Setter private int ingredientId;
    @Getter @Setter private int ingredientQuantity;
    @Getter @Setter private float minimumAmount;

    public PantryIngredient() {
    }

    public PantryIngredient(int userId, int ingredientId, int ingredientQuantity) {
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.ingredientQuantity = ingredientQuantity;
    }

    public PantryIngredient(int id, int userId, int ingredientId, int ingredientQuantity) {
        this.id = id;
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.ingredientQuantity = ingredientQuantity;
    }

    public PantryIngredient(int id, int userId, int ingredientId, int ingredientQuantity, float minimumAmount) {
        this.id = id;
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.ingredientQuantity = ingredientQuantity;
        this.minimumAmount = minimumAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PantryIngredient that = (PantryIngredient) o;
        return id == that.id && userId == that.userId && ingredientId == that.ingredientId && ingredientQuantity == that.ingredientQuantity && Float.compare(that.minimumAmount, minimumAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, ingredientId, ingredientQuantity, minimumAmount);
    }

    @Override
    public String toString() {
        return "PantryIngredient{" +
                "id=" + id +
                ", userId=" + userId +
                ", ingredientId=" + ingredientId +
                ", ingredientQuantity=" + ingredientQuantity +
                ", minimumAmount=" + minimumAmount +
                '}';
    }
}
