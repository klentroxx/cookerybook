package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.UserDAO;
import hu.cookerybook.core.dao.UserDAOImpl;
import hu.cookerybook.core.model.User;
import hu.cookerybook.core.security.Security;
import hu.cookerybook.desktop.Desktop;
import hu.cookerybook.desktop.session.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginController {

    @FXML private TextField usernameOrEmailInputField;
    @FXML private PasswordField passwordInputField;
    @FXML private Button loginButton;
    @FXML private Hyperlink registrationViewLink;

    public LoginController() {}

    @FXML
    public void loginProcedure(ActionEvent actionEvent) {
        UserDAO userDAO = new UserDAOImpl();
        User requestedUser = null;
        try {
            requestedUser = userDAO.getUserByUsernameOrEmail(usernameOrEmailInputField.getText());
            if (Security.validatePassword(passwordInputField.getText(), requestedUser.getPassword())) {
                UserSession.getInstance(requestedUser.getUsername(), requestedUser.getUserRole());
                try {
                    Desktop.setRoot("primary");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Sikeres bejelentkezes!");
            } else {
                System.err.println("A megadott jelszo hibas");
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("A felhasznalo nem talalhato!");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public void goToRegistrationForm(ActionEvent actionEvent) {
        try {
            Desktop.setRoot("registration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
