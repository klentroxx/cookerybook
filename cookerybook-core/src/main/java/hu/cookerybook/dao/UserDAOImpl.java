package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements hu.cookerybook.dao.UserDAO {

    @Override
    public void addUser(User user) {
        String queryString = "INSERT INTO users SET " +
                "user_role=" + user.getUserRole() + ", " +
                "username='" + user.getUsername() + "', " +
                "email='" + user.getEmail() + "', " +
                "password='" + user.getPassword() + "', " +
                "first_name='" + user.getFirstName() + "', " +
                "last_name='" + user.getLastName() + "';";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeUser(int id) {
        String queryString = "DELETE FROM users WHERE id=" + id;
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> result = new ArrayList<>();
        String queryString = "SELECT * FROM users";
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        while (resultSet.next()) {
            User u = new User(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getDate(9)
            );
            result.add(u);
        }

        return result;
    }

    @Override
    public User getUser(int id) throws SQLException {
        User result = null;
        String queryString = "SELECT * FROM users WHERE id=" + id;
        ResultSet resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        if (resultSet.next()) {
            result = new User(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getDate(9)
            );
        }
        return result;
    }

    @Override
    public void updateUser(User user) {
        String queryString = "UPDATE users SET " +
                "user_role=" + user.getUserRole() + ", " +
                "username='" + user.getUsername() + "', " +
                "email='" + user.getEmail() + "', " +
                "password='" + user.getPassword() + "', " +
                "first_name='" + user.getFirstName() + "', " +
                "last_name='" + user.getLastName() + "' " +
                "WHERE id=" + user.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

}
