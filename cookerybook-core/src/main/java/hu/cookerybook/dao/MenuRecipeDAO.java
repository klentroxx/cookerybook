package hu.cookerybook.dao;

import hu.cookerybook.model.Menu;
import hu.cookerybook.model.MenuRecipe;
import hu.cookerybook.model.Recipe;

import java.sql.SQLException;
import java.util.List;

public interface MenuRecipeDAO {

    void addMenuRecipe(MenuRecipe menuRecipe);

    void removeMenuRecipe(int id);

    List<MenuRecipe> getAllMenuRecipes() throws SQLException;

    List<Menu> getMenusWithRecipe(Recipe recipe) throws SQLException;

    List<Recipe> getMenuRecipes(Menu menu) throws SQLException;

    MenuRecipe getMenuRecipe(int id) throws SQLException;

    void updateMenuRecipe(MenuRecipe menuRecipe);
    
}
