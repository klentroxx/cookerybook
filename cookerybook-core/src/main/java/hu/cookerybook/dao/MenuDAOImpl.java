package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.Menu;
import hu.cookerybook.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MenuDAOImpl implements MenuDAO {
    @Override
    public void addMenu(Menu menu) {
        String queryString = "INSERT INTO menu (name, created_by) " +
                "VALUES(" + menu.getName() + ", " +
                menu.getCreatedById() + ", " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeMenu(Menu menu) {
        String queryString = "DELETE FROM menu WHERE id=" + menu.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new Menu(parseInt(row[0]), row[1], parseInt(row[2])));
        }

        return result;
    }

    @Override
    public List<Menu> getMenus(User user) {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu WHERE created_by=" + user.getId();
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            result.add(new Menu(parseInt(row[0]), row[1], parseInt(row[2])));
        }

        return result;
    }

    @Override
    public Menu getMenu(int id) {
        String queryString = "SELECT * FROM menu WHERE id=" + id;
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        return new Menu(parseInt(row[0]), row[1], parseInt(row[2]));
    }

    @Override
    public void updateMenu(Menu menu) {
        String queryString = "UPDATE menu SET " +
                "name=" + menu.getName() + ", " +
                "created_by=" + menu.getCreatedById() + " " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + menu.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }
}
