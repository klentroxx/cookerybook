package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Menu;
import hu.cookerybook.model.PantryIngredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {
    @Override
    public void addMenu(Menu menu) {
        String queryString = "INSERT INTO menu SET " +
                "name=" + menu.getName() + ", " +
                "created_by=" + menu.getCreatedById() + ";";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeMenu(int id) {
        String queryString = "DELETE FROM menu WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<Menu> getAllMenus() throws SQLException {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        while (resultSet.next()) {
            Menu m = new Menu(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
            result.add(m);
        }

        return result;
    }

    @Override
    public List<Menu> getMenus(int createdBy) throws SQLException {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu WHERE created_by=" + createdBy;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        while (resultSet.next()) {
            Menu m = new Menu(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
            result.add(m);
        }

        return result;
    }

    @Override
    public Menu getMenu(int id) throws SQLException {
        Menu result = null;
        String queryString = "SELECT * FROM menu WHERE id=" + id;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        if (resultSet.next()) {
            result = new Menu(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }

        return result;
    }

    @Override
    public void updateMenu(Menu menu) {
        String queryString = "UPDATE menu SET " +
                "name=" + menu.getName() + ", " +
                "created_by=" + menu.getCreatedById() +
                "WHERE id=" + menu.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
