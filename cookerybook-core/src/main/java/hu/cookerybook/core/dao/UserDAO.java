package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.List;

public interface UserDAO {

    void addUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;

    void removeUser(User user);

    List<User> getAllUsers() throws ParseException;

    User getUserById(int id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User getUserByUsernameOrEmail(String usernameOrEmail);

    User validateUser(String emailOrUsername, String password);

    void updateUser(User user);

}
