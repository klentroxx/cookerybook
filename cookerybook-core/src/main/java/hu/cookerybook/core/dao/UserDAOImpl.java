package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.dbconn.PreparedStatementParameter;
import hu.cookerybook.core.model.User;
import hu.cookerybook.core.security.Security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String queryString = "INSERT INTO users (user_role, username, email, password, first_name, last_name, register_date, updated_at) " +
                "VALUES(?, ?, ?, ?, ?, ?, " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", user.getUserRole()));
        parameters.add(new PreparedStatementParameter(2, "string", user.getUsername()));
        parameters.add(new PreparedStatementParameter(3, "string", user.getEmail()));
        parameters.add(new PreparedStatementParameter(4, "string", Security.generatePasswordHash(user.getPassword())));
        parameters.add(new PreparedStatementParameter(5, "string", user.getFirstName()));
        parameters.add(new PreparedStatementParameter(6, "string", user.getLastName()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public void removeUser(User user) {
        String queryString = "DELETE FROM users " +
                "WHERE id = ? OR " +
                "username = ? AND " +
                "email = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", user.getId()));
        parameters.add(new PreparedStatementParameter(2, "string", user.getUsername()));
        parameters.add(new PreparedStatementParameter(3, "string", user.getEmail()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String queryString = "SELECT * FROM users";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabaseStat(queryString);

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
    public User getUserById(int id) {
        User result = new User();
        String queryString = "SELECT * FROM users WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", id));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
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
    public User getUserByUsername(String username) {
        User result = new User();
        String queryString = "SELECT * FROM users WHERE username = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", username));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
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
    public User getUserByEmail(String email) {
        User result = new User();
        String queryString = "SELECT * FROM users WHERE email = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", email));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
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
    public User getUserByUsernameOrEmail(String usernameOrEmail) throws IndexOutOfBoundsException {
        User result = new User();
        String queryString = "SELECT * FROM users WHERE username = ? OR email = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", usernameOrEmail));
        parameters.add(new PreparedStatementParameter(2, "string", usernameOrEmail));
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabasePrepStat(queryString, parameters);
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
    public User validateUser(String emailOrUsername, String password) {
        User user = new User();
        try {
            user = getUserByUsernameOrEmail(emailOrUsername);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("A felhasznalonev vagy e-mail cim hibas.");
        }
        if (user.getId() > 0) {
            boolean isValidPass = false;
            try {
                isValidPass = Security.validatePassword(password, user.getPassword());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
            if (!isValidPass) {
                user.setId(-2);
            }
            return user;
        } else {
            user.setId(-1);
            return user;
        }
    }

    @Override
    public void updateUser(User user) {
        String queryString = "UPDATE users SET " +
                "user_role = ?, " +
                "username = ?, " +
                "email = ?, " +
                "first_name = ?, " +
                "last_name = ?, " +
                "updated_at = '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "int", user.getUserRole()));
        parameters.add(new PreparedStatementParameter(2, "string", user.getUsername()));
        parameters.add(new PreparedStatementParameter(3, "string", user.getEmail()));
        parameters.add(new PreparedStatementParameter(4, "string", user.getFirstName()));
        parameters.add(new PreparedStatementParameter(5, "string", user.getLastName()));
        parameters.add(new PreparedStatementParameter(6, "int", user.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

    public void updateUserPassword(User user, String newPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String queryString = "UPDATE users SET " +
                "password = ? " +
                "WHERE id = ?";
        List<PreparedStatementParameter> parameters = new ArrayList<>();

        parameters.add(new PreparedStatementParameter(1, "string", Security.generatePasswordHash(newPassword)));
        parameters.add(new PreparedStatementParameter(2, "int", user.getId()));
        new DatabaseFunctions().setDataInDatabase(queryString, parameters);
    }

}
