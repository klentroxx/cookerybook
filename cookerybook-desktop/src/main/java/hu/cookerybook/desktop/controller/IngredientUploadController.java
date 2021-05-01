package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.IngredientDAO;
import hu.cookerybook.core.dao.IngredientDAOImpl;
import hu.cookerybook.core.dao.UnitDAO;
import hu.cookerybook.core.dao.UnitDAOImpl;
import hu.cookerybook.core.model.Ingredient;
import hu.cookerybook.core.model.Unit;
import hu.cookerybook.desktop.Desktop;
import hu.cookerybook.desktop.popup.ConfirmationBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IngredientUploadController implements Initializable {
    @FXML private Button uploadButton;
    @FXML private Button modifyButton;
    @FXML private Button removeButton;
    @FXML private TextField ingredientNameInputField;
    @FXML private ComboBox<Unit> ingredientUnitSelect;
    @FXML private ListView<Ingredient> ingredientList;

    public IngredientUploadController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUnitSelect();
        loadIngredientList();
        initButtons();
    }

    public void uploadIngredient(ActionEvent actionEvent) {
        IngredientDAO ingredientDAO = new IngredientDAOImpl();
        Ingredient newIngredient = new Ingredient();
        String ingredientName = ingredientNameInputField.getText();
        int ingredientUnit = ingredientUnitSelect.getSelectionModel().getSelectedItem().getId();

        newIngredient.setName(ingredientName);
        newIngredient.setUnitId(ingredientUnit);

        ingredientDAO.addIngredient(newIngredient);
        reInitialize();

        ingredientNameInputField.setText("");
        ingredientUnitSelect.getSelectionModel().clearSelection();
    }

    public void deleteIngredient(ActionEvent actionEvent) {
        Ingredient selectedItem = ingredientList.getSelectionModel().getSelectedItem();
        if (new ConfirmationBox().confirmProcess("Törlés megerősítése", "Biztosan törölni szeretné a hozzávalót?")) {
            IngredientDAO ingredientDAO = new IngredientDAOImpl();
            ingredientDAO.removeIngredient(selectedItem);
            System.out.println("Torolve");
            reInitialize();
        } else {
            System.out.println("Megsem");
        }
    }

    public void modifyIngredient(ActionEvent actionEvent) {
        Ingredient selectedIngredient = ingredientList.getSelectionModel().getSelectedItem();
        openModifyWindow(selectedIngredient);
    }

    public void openModifyWindow(Ingredient ingredient) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/hu/cookerybook/desktop/ingredient_modify.fxml")
        );

        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        IngredientModifyController ingredientModifyController = loader.getController();
        ingredientModifyController.initializeData(ingredient, stage);

        stage.setTitle("Mértékegység módosítása");
        stage.show();

        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                reInitialize();
            }
        });
    }

    private void loadUnitSelect() {
        UnitDAO unitDAO = new UnitDAOImpl();
        ObservableList<Unit> units = FXCollections.observableArrayList(unitDAO.getAllUnits());
        ingredientUnitSelect.setItems(units);
    }

    private void loadIngredientList() {
        IngredientDAO ingredientDAO = new IngredientDAOImpl();
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList(ingredientDAO.getAllIngredients());
        ingredientList.setItems(ingredients);
    }

    private void initButtons() {
        uploadButton.disableProperty().bind((ingredientUnitSelect.getSelectionModel().selectedItemProperty().isNull()).or(ingredientNameInputField.textProperty().isEmpty()));
        modifyButton.disableProperty().bind(ingredientList.getSelectionModel().selectedItemProperty().isNull());
        removeButton.disableProperty().bind(ingredientList.getSelectionModel().selectedItemProperty().isNull());
    }

    public void reInitialize() {
        loadIngredientList();
    }

    public void backToHome(ActionEvent actionEvent) {
        try {
            Desktop.setRoot("homepage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
