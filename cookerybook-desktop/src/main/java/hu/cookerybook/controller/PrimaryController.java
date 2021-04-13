package hu.cookerybook.controller;

import java.io.IOException;

import hu.cookerybook.Desktop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

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
}
