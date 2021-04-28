package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
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
        String queryString = "INSERT INTO menu_recipes (menu_id, recipe_id, created_at, updated_at) " +
                "VALUES(?, ?, " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", menuRecipe.getMenuId()));
        parameters.add(new PreparedStatementParameter(2, "int", menuRecipe.getRecipeId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public void removeMenuRecipe(MenuRecipe menuRecipe) {
        String queryString = "DELETE FROM menu_recipes WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", menuRecipe.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public List<MenuRecipe> getAllMenuRecipes() {
        List<MenuRecipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu_recipes";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabaseStat(queryString);

        for (String[] row : resultSet) {
            MenuRecipe mr = new MenuRecipe();
            mr.setId(parseInt(row[0]));
            mr.setMenuId(parseInt(row[1]));
            mr.setRecipeId(parseInt(row[2]));
            result.add(mr);
        }

        return result;
    }

    @Override
    public List<Menu> getMenusWithRecipe(Recipe recipe) {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menus WHERE id = (SELECT menu_id FROM menu_recipes WHERE recipe_id = ?)";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", recipe.getId()));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);

        for (String[] row : resultSet) {
            Menu m = new Menu();
            m.setId(parseInt(row[0]));
            m.setName(row[1]);
            m.setCreatedById(parseInt(row[2]));
            result.add(m);
        }

        return result;
    }

    @Override
    public List<Recipe> getMenuRecipes(Menu menu) {
        List<Recipe> result = new ArrayList<>();
        String queryString = "SELECT * FROM recipes WHERE id = (SELECT recipe_id FROM menu_recipes WHERE menu_id = ?)";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", menu.getId()));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);

        for (String[] row : resultSet) {
            Recipe r = new Recipe();
            r.setId(parseInt(row[0]));
            r.setName(row[1]);
            r.setPhoto(row[2]);
            r.setDirections(row[3]);
            r.setDifficulty(parseInt(row[4]));
            r.setTimeToCook(parseInt(row[5]));
            r.setServings(parseInt(row[6]));
            r.setCategory(row[7]);
            r.setCreatedById(parseInt(row[8]));
            result.add(r);
        }

        return result;
    }

    @Override
    public MenuRecipe getMenuRecipe(int id) {
        String queryString = "SELECT * FROM menu_recipes WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", id));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
        String[] row = resultSet.get(0);

        MenuRecipe mr = new MenuRecipe();
        mr.setId(parseInt(row[0]));
        mr.setMenuId(parseInt(row[1]));
        mr.setRecipeId(parseInt(row[2]));

        return mr;
    }

    @Override
    public void updateMenuRecipe(MenuRecipe menuRecipe) {
        String queryString = "INSERT INTO menu_recipes SET " +
                "menu_id = ?, " +
                "recipe_id = ?, " +
                "updated_at = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id = ?;";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", menuRecipe.getMenuId()));
        parameters.add(new PreparedStatementParameter(2, "int", menuRecipe.getRecipeId()));
        parameters.add(new PreparedStatementParameter(3, "int", menuRecipe.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }
}
