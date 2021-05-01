package hu.cookerybook.desktop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hu.cookerybook.core.model.User;
import hu.cookerybook.desktop.Desktop;
import hu.cookerybook.desktop.session.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomepageController implements Initializable {

    @FXML private Label userNameLabel;
    @FXML private Button nextToIngredientsButton;
    @FXML private Button nextToUnitsButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameLabel.setText(UserSession.getInstance("", 0).getUserName());
        if (UserSession.getInstance("", 0).getUserRole() == 2 || UserSession.getInstance("", 0).getUserRole() == 3) {
            nextToIngredientsButton.setDisable(true);
            nextToUnitsButton.setDisable(true);
        }
    }

    @FXML private void toIngredientsPage(ActionEvent actionEvent) throws IOException {
        Desktop.setRoot("ingredient_upload");
    }

    @FXML private void toUnitsPage(ActionEvent actionEvent) throws IOException {
        Desktop.setRoot("unit_upload");
    }
}
