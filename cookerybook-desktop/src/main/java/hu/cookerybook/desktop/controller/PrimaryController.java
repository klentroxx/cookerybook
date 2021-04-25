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

public class PrimaryController implements Initializable {

    @FXML private Button ingredientUploadButton;
    @FXML private Button ingredientModifyButton;
    @FXML private Button unitUploadButton;
    @FXML private Button unitModifyButton;

    @FXML
    public void ingredientUploadRoute(ActionEvent actionEvent) throws IOException {
        Desktop.setRoot("ingredient_upload");
    }

    @FXML
    public void ingredientModifyRoute(ActionEvent actionEvent) throws IOException {
        Desktop.setRoot("ingredient_modify");
    }

    @FXML
    public void unitUploadRoute(ActionEvent actionEvent) throws IOException {
        Desktop.setRoot("unit_upload");
    }

    @FXML
    public void unitModifyRoute(ActionEvent actionEvent) throws IOException {
        Desktop.setRoot("unit_modify");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(UserSession.getInstance("", 0).getUserRole());
        if (UserSession.getInstance("", 0).getUserRole() == 2 || UserSession.getInstance("", 0).getUserRole() == 3) {
            System.out.println("Trued");
            ingredientUploadButton.setDisable(true);
            ingredientModifyButton.setDisable(true);
            unitUploadButton.setDisable(true);
            unitModifyButton.setDisable(true);
        }
    }
}
