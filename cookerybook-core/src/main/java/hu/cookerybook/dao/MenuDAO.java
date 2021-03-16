package hu.cookerybook.dao;

import hu.cookerybook.model.Menu;

import java.sql.SQLException;
import java.util.List;

public interface MenuDAO {
    
    void addMenu(Menu menu);

    void removeMenu(int id);

    List<Menu> getAllMenus() throws SQLException;

    List<Menu> getMenus(int createdBy) throws SQLException;

    Menu getMenu(int id) throws SQLException;

    void updateMenu(Menu menu);
    
}