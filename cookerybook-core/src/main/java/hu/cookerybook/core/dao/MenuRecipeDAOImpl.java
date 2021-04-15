package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.model.Menu;
import hu.cookerybook.core.model.MenuRecipe;
import hu.cookerybook.core.model.Recipe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MenuRecipeDAOImpl implements MenuRecipeDAO {
    @Override
    public void addMenuRecipe(MenuRecipe menuRecipe) {
        String queryString = "INSERT INTO menu_recipes (menu_id, recipe_id) " +
                "VALUES(" + menuRecipe.getMenuId() + ", " +
                menuRecipe.getRecipeId() + ", " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeMenuRecipe(MenuRecipe menuRecipe) {
        String queryString = "DELETE FROM menu_recipes WHERE id=" + menuRecipe.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<MenuRecipe> getAllMenuRecipes() {
        List<MenuRecipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu_recipes";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new MenuRecipe(parseInt(row[0]), parseInt(row[1]), parseInt(row[2])));
        }

        return result;
    }

    @Override
    public List<Menu> getMenusWithRecipe(Recipe recipe) {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menus WHERE id=(SELECT menu_id FROM menu_recipes WHERE recipe_id=" + recipe.getId() + ")";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new Menu(parseInt(row[0]), row[1], parseInt(row[2])));
        }

        return result;
    }

    @Override
    public List<Recipe> getMenuRecipes(Menu menu) {
        List<Recipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipes WHERE id=(SELECT recipe_id FROM menu_recipes WHERE menu_id=" + menu.getId() + ")";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new Recipe(parseInt(row[0]), row[1], row[2], row[3], parseInt(row[4]), parseInt(row[5]), parseInt(row[6]), row[7], parseInt(row[8])));
        }

        return result;
    }

    @Override
    public MenuRecipe getMenuRecipe(int id) {
        String queryString = "SELECT * FROM menu_recipes WHERE id=" + id;
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        return new MenuRecipe(parseInt(row[0]), parseInt(row[1]), parseInt(row[2]));
    }

    @Override
    public void updateMenuRecipe(MenuRecipe menuRecipe) {
        String queryString = "INSERT INTO menu_recipes SET " +
                "menu_id=" + menuRecipe.getMenuId() + ", " +
                "recipe_id=" + menuRecipe.getRecipeId() + " " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + menuRecipe.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
