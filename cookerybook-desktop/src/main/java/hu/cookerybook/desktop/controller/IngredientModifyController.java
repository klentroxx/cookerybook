package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.IngredientDAO;
import hu.cookerybook.core.dao.IngredientDAOImpl;
import hu.cookerybook.core.dao.UnitDAO;
import hu.cookerybook.core.dao.UnitDAOImpl;
import hu.cookerybook.core.model.Ingredient;
import hu.cookerybook.core.model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IngredientModifyController implements Initializable {
    @FXML private Label ingredientNameLabel;
    @FXML private TextField ingredientNameInputField;
    @FXML private ComboBox<Unit> ingredientUnitSelect;
    private Ingredient ingredientToBeModified;
    private Stage stage;

    public IngredientModifyController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUnitSelect();
    }

    public void saveChanges(ActionEvent actionEvent) {
        IngredientDAO ingredientDAO = new IngredientDAOImpl();
        this.ingredientToBeModified.setName(ingredientNameInputField.getText());
        this.ingredientToBeModified.setUnitId(ingredientUnitSelect.getSelectionModel().getSelectedItem().getId());
        ingredientDAO.updateIngredient(this.ingredientToBeModified);
        stage.close();
    }

    public void cancelModify(ActionEvent actionEvent) {
        stage.close();
    }

    public void initializeData(Ingredient ingredient, Stage stage) {
        this.ingredientToBeModified = ingredient;
        this.stage = stage;
        initializePage();
    }

    public void initializePage() {
        IngredientDAO ingredientDAO = new IngredientDAOImpl();
        Unit ingredientUnit = ingredientDAO.getUnitOfIngredient(ingredientToBeModified);
        this.ingredientNameLabel.setText(this.ingredientToBeModified.toString());
        this.ingredientNameInputField.setText(this.ingredientToBeModified.getName());
        this.ingredientUnitSelect.getSelectionModel().select(ingredientUnit);
    }

    private void loadUnitSelect() {
        UnitDAO unitDAO = new UnitDAOImpl();
        ObservableList<Unit> units = FXCollections.observableArrayList(unitDAO.getAllUnits());
        ingredientUnitSelect.setItems(units);
    }

}
