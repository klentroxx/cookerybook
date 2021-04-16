package hu.cookerybook.core.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.util.Objects;

public class MenuRecipe implements Serializable {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty menuId = new SimpleIntegerProperty(this, "menuId");
    private IntegerProperty recipeId = new SimpleIntegerProperty(this, "recipeId");

    public MenuRecipe() {
    }

    public MenuRecipe(IntegerProperty menuId, IntegerProperty recipeId) {
        this.menuId = menuId;
        this.recipeId = recipeId;
    }

    public MenuRecipe(IntegerProperty id, IntegerProperty menuId, IntegerProperty recipeId) {
        this.id = id;
        this.menuId = menuId;
        this.recipeId = recipeId;
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

    public int getMenuId() {
        return menuId.get();
    }

    public IntegerProperty menuIdProperty() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId.set(menuId);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuRecipe that = (MenuRecipe) o;
        return id == that.id && menuId == that.menuId && recipeId == that.recipeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuId, recipeId);
    }

    @Override
    public String toString() {
        return "MenuRecipes{" +
                "id=" + getId() +
                ", menuId=" + getMenuId() +
                ", recipeId=" + getRecipeId() +
                '}';
    }
}
