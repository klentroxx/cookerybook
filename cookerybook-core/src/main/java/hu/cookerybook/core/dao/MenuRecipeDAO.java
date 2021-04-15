package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.Menu;
import hu.cookerybook.core.model.MenuRecipe;
import hu.cookerybook.core.model.Recipe;

import java.util.List;

public interface MenuRecipeDAO {

    void addMenuRecipe(MenuRecipe menuRecipe);

    void removeMenuRecipe(MenuRecipe menuRecipe);

    List<MenuRecipe> getAllMenuRecipes();

    List<Menu> getMenusWithRecipe(Recipe recipe);

    List<Recipe> getMenuRecipes(Menu menu);

    MenuRecipe getMenuRecipe(int id);

    void updateMenuRecipe(MenuRecipe menuRecipe);
    
}
