package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.UserDAO;
import hu.cookerybook.core.dao.UserDAOImpl;
import hu.cookerybook.core.model.User;
import hu.cookerybook.core.security.Security;
import hu.cookerybook.desktop.Desktop;
import hu.cookerybook.desktop.popup.MessageBox;
import hu.cookerybook.desktop.validation.InputValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegistrationController {

    @FXML private TextField usernameInputField;
    @FXML private TextField emailInputField;
    @FXML private TextField firstNameInputField;
    @FXML private TextField lastNameInputField;
    @FXML private PasswordField passwordInputField;
    @FXML private PasswordField passwordAgainInputField;
    @FXML private Button registrationButton;
    @FXML private Hyperlink backToLoginButton;


    public void registration(ActionEvent actionEvent) {
        String username = usernameInputField.getText();
        String email = emailInputField.getText();
        String firstName = firstNameInputField.getText();
        String lastName = lastNameInputField.getText();
        String password = passwordInputField.getText();
        String passwordAgain = passwordAgainInputField.getText();
        UserDAO userDAO = new UserDAOImpl();
        User newUser = new User();

        System.out.println(username + " " + email + " " + firstName + " " + lastName + " " + password + " " + passwordAgain);
        if (!InputValidation.validateUsername(username)) {
            MessageBox.display("Hibás felhasználónév", "A felhasználónév formátuma vagy hossza nem megfelelő.\nA felhasználónév csak az angol ábécé kis- és nagybetűit illetve számokat tartalmazhat, hossza pedig legalább 3 és legfeljebb 20 karakter lehet.");
            return;
        } else if (!InputValidation.validateEmail(email)) {
            MessageBox.display("Hibás e-mail", "Az e-mail cím formátuma nem megfelelő.\nCsak szabványos e-mail címmel lehet regisztrálni.");
            return;
        } else if (!InputValidation.validateName(firstName)) {
            MessageBox.display("Hibás keresztnév", "A keresztnév formátuma nem megfelelő.\nA keresztnév nem tartalmazhat számot és különleges karaktereket");
            return;
        } else if (!InputValidation.validateName(lastName)) {
            MessageBox.display("Hibás vezetéknév", "A vezetéknév formátuma nem megfelelő.\nA vezetéknév nem tartalmazhat számot és különleges karaktereket.");
            return;
        } else if (!InputValidation.validatePassword(password)) {
            MessageBox.display("Hibás jelszó", "A jelszó legalább 8 és legfeljebb 32 karaktert tartalmazhat.");
            return;
        } else if (!password.equals(passwordAgain)) {
            MessageBox.display("Nem egyező jelszavak", "A megadott jelszavak nem egyeznek");
            return;
        }

        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setUserRole(1);
        newUser.setPassword(password);
        newUser.setLastName(lastName);
        newUser.setFirstName(firstName);
        try {
            userDAO.addUser(newUser);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        MessageBox.display("Sikeres regisztráció!", "A regisztráció sikeres volt!");
        try {
            Desktop.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToLogin(ActionEvent actionEvent) {
        try {
            Desktop.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
