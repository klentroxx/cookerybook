package hu.cookerybook.dao;

import hu.cookerybook.model.Menu;
import hu.cookerybook.model.MenuRecipe;
import hu.cookerybook.model.Recipe;

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
