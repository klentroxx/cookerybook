package hu.cookerybook.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class MenuRecipe {
    @Getter private int id;
    @Getter @Setter private int menuId;
    @Getter @Setter private int recipeId;

    public MenuRecipe(int menuId, int recipeId) {
        this.menuId = menuId;
        this.recipeId = recipeId;
    }

    public MenuRecipe(int id, int menuId, int recipeId) {
        this.id = id;
        this.menuId = menuId;
        this.recipeId = recipeId;
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
                "id=" + id +
                ", menuId=" + menuId +
                ", recipeId=" + recipeId +
                '}';
    }
}
