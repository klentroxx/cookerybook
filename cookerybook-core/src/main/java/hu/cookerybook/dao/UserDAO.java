package hu.cookerybook.dao;

import hu.cookerybook.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void addUser(User user);

    void removeUser(int id);

    List<User> getAllUsers() throws SQLException;

    User getUser(int id) throws SQLException;

    void updateUser(User user);

}
