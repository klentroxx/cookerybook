package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
import hu.cookerybook.core.model.Menu;
import hu.cookerybook.core.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MenuDAOImpl implements MenuDAO {
    @Override
    public void addMenu(Menu menu) {
        String queryString = "INSERT INTO menu (name, created_by, created_at, updated_at) " +
                "VALUES(?, ?, " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", menu.getName()));
        parameters.add(new PreparedStatementParameter(2, "int", menu.getCreatedById()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public void removeMenu(Menu menu) {
        String queryString = "DELETE FROM menu WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", menu.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabaseStat(queryString);

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
    public List<Menu> getMenus(User user) {
        List<Menu> result = new ArrayList<>();
        String queryString = "SELECT * FROM menu WHERE created_by = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", user.getId()));
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
    public Menu getMenu(int id) {
        String queryString = "SELECT * FROM menu WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", id));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
        String[] row = resultSet.get(0);

        Menu m = new Menu();
        m.setId(parseInt(row[0]));
        m.setName(row[1]);
        m.setCreatedById(parseInt(row[2]));

        return m;
    }

    @Override
    public void updateMenu(Menu menu) {
        String queryString = "UPDATE menu SET " +
                "name = ?, " +
                "created_by = ?, " +
                "updated_at = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id = ?;";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", menu.getName()));
        parameters.add(new PreparedStatementParameter(2, "int", menu.getCreatedById()));
        parameters.add(new PreparedStatementParameter(3, "int", menu.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }
}
