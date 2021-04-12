package hu.cookerybook.dao;

import hu.cookerybook.model.Menu;
import hu.cookerybook.model.User;

import java.util.List;

public interface MenuDAO {
    
    void addMenu(Menu menu);

    void removeMenu(Menu menu);

    List<Menu> getAllMenus();

    List<Menu> getMenus(User user);

    Menu getMenu(int id);

    void updateMenu(Menu menu);
    
}