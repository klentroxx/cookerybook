package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Menu;
import hu.cookerybook.model.MenuRecipe;
import hu.cookerybook.model.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuRecipeDAOImpl implements MenuRecipeDAO {
    @Override
    public void addMenuRecipe(MenuRecipe menuRecipe) {
        String queryString = "INSERT INTO menu_recipes SET " +
                "menu_id=" + menuRecipe.getMenuId() + ", " +
                "recipe_id=" + menuRecipe.getRecipeId() + ";";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeMenuRecipe(int id) {
        String queryString = "DELETE FROM menu_recipes WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<MenuRecipe> getAllMenuRecipes() throws SQLException {
        List<MenuRecipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu_recipes";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            MenuRecipe mr = new MenuRecipe(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3)
            );
            result.add(mr);
        }

        return result;
    }

    @Override
    public List<Menu> getMenusWithRecipe(Recipe recipe) throws SQLException {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menus WHERE id=(SELECT menu_id FROM menu_recipes WHERE recipe_id=" + recipe.getId() + ")";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            Menu mr = new Menu(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
            result.add(mr);
        }

        return result;
    }

    @Override
    public List<Recipe> getMenuRecipes(Menu menu) throws SQLException {
        List<Recipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipes WHERE id=(SELECT recipe_id FROM menu_recipes WHERE menu_id=" + menu.getId() + ")";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        while (resultSet.next()) {
            Recipe mr = new Recipe(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getString(9),
                    resultSet.getInt(10)
            );
            result.add(mr);
        }

        return result;
    }

    @Override
    public MenuRecipe getMenuRecipe(int id) throws SQLException {
        MenuRecipe result = null;
        String queryString = "SELECT * FROM menu_recipes WHERE id=" + id;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        if (resultSet.next()) {
            result = new MenuRecipe(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3)
            );
        }

        return result;
    }

    @Override
    public void updateMenuRecipe(MenuRecipe menuRecipe) {
        String queryString = "INSERT INTO menu_recipes SET " +
                "menu_id=" + menuRecipe.getMenuId() + ", " +
                "recipe_id=" + menuRecipe.getRecipeId() + " " +
                "WHERE id=" + menuRecipe.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
