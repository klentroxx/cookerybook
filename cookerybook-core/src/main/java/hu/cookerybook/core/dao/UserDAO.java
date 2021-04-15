package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.User;

import java.text.ParseException;
import java.util.List;

public interface UserDAO {

    void addUser(User user);

    void removeUser(User user);

    List<User> getAllUsers() throws ParseException;

    User getUser(int id);

    void updateUser(User user);

}
