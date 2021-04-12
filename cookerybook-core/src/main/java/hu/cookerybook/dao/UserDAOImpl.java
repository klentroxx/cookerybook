package hu.cookerybook.dao;

import hu.cookerybook.dbconn.DatabaseFunctions;
import hu.cookerybook.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UserDAOImpl implements hu.cookerybook.dao.UserDAO {

    @Override
    public void addUser(User user) {
        String queryString = "INSERT INTO users (user_role, username, email, password, first_name, last_name, register_date, updated_at) " +
                "VALUES(" + user.getUserRole() + ", " +
                "'" + user.getUsername() + "', " +
                "'" + user.getEmail() + "', " +
                "'" + user.getPassword() + "', " +
                "'" + user.getFirstName() + "', " +
                "'" + user.getLastName() + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public void removeUser(User user) {
        String queryString = "DELETE FROM users WHERE id=" + user.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String queryString = "SELECT * FROM users";
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            User user = null;
            try {
                user = new User(parseInt(row[0]), parseInt(row[1]), row[2], row[3], row[4], row[5], row[6], new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row[7]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            result.add(user);
        }

        return result;
    }

    @Override
    public User getUser(int id) {
        User result = null;
        String queryString = "SELECT * FROM users WHERE id=" + id;
        List<String[]> resultSet = DatabaseFunctions.getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        try {
            result = new User(parseInt(row[0]), parseInt(row[1]), row[2], row[3], row[4], row[5], row[6], new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row[7]));
        } catch (ParseException e) {
            e.printStackTrace();
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
                "last_name='" + user.getLastName() + "', " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + user.getId();
        DatabaseFunctions.setDataInDatabase(queryString);
    }

}
