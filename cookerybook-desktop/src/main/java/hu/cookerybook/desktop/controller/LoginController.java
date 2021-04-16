package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.UserDAO;
import hu.cookerybook.core.dao.UserDAOImpl;
import hu.cookerybook.core.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameOrEmailInputField;
    @FXML
    private TextField passwordInputField;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink registrationViewLink;

    public LoginController() {}

    @FXML
    public void loginProcedure(ActionEvent actionEvent) {
        UserDAO userDAO = new UserDAOImpl();
        User requestedUser = userDAO.getUserByUsernameOrEmail(usernameOrEmailInputField.getText());
        System.out.println(requestedUser.toString());
    }
}
