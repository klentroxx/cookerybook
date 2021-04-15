package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UserDAOImpl implements UserDAO {

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
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public void removeUser(User user) {
        String queryString = "DELETE FROM users WHERE id=" + user.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String queryString = "SELECT * FROM users";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            User u = new User();
            try {
                u.setId(parseInt(row[0]));
                u.setUserRole(parseInt(row[1]));
                u.setUsername(row[2]);
                u.setEmail(row[3]);
                u.setPassword(row[4]);
                u.setFirstName(row[5]);
                u.setLastName(row[6]);
                u.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row[7]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            result.add(u);
        }

        return result;
    }

    @Override
    public User getUser(int id) {
        User result = new User();
        String queryString = "SELECT * FROM users WHERE id=" + id;
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        try {
            result.setId(parseInt(row[0]));
            result.setUserRole(parseInt(row[1]));
            result.setUsername(row[2]);
            result.setEmail(row[3]);
            result.setPassword(row[4]);
            result.setFirstName(row[5]);
            result.setLastName(row[6]);
            result.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row[7]));
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
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

}
