package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.UnitDAO;
import hu.cookerybook.core.dao.UnitDAOImpl;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class UnitUploadController implements Initializable {

    @FXML private Button unitUploadButton;
    @FXML private Button unitDeleteButton;
    @FXML private Button unitModifyButton;
    @FXML private TextField unitNameInputField;
    @FXML private TextField unitShortNameInputField;
    @FXML private TextField unitChangeMultiplierInputField;
    @FXML private ComboBox<Unit> unitDefaultParentUnitSelect;
    @FXML private ListView<Unit> unitList;

    public UnitUploadController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUnitList();
        loadDefaultUnitSelect();
        setMultiplierFieldToFloat();
        initializeButtons();
    }

    private void loadUnitList() {
        UnitDAO unitDAO = new UnitDAOImpl();
        ObservableList<Unit> units = FXCollections.observableArrayList(unitDAO.getAllUnits());
        unitList.setItems(units);
    }

    private void loadDefaultUnitSelect() {
        UnitDAO unitDAO = new UnitDAOImpl();
        List<Unit> unitsList = new ArrayList<>(unitDAO.getAllParentUnits());
        ObservableList<Unit> units = FXCollections.observableArrayList(unitsList);

        unitDefaultParentUnitSelect.setItems(units);
        unitDefaultParentUnitSelect.setCellFactory(new Callback<ListView<Unit>, ListCell<Unit>>() {
            @Override
            public ListCell<Unit> call(ListView<Unit> stringListView) {
                return new ListCell<Unit>() {
                    @Override
                    protected void updateItem(Unit unit, boolean b) {
                        super.updateItem(unit, b);

                        if (unit != null) {
                            setText(unit.toString());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void uploadUnit() {
        Unit unit = new Unit();
        UnitDAO unitDAO = new UnitDAOImpl();
        String unitName = unitNameInputField.getText();
        String unitShort = unitShortNameInputField.getText();
        int parentUnitId = 0;
        float unitChargeMulti = (float) 0;
        if (unitDefaultParentUnitSelect.getValue() != null && !unitChangeMultiplierInputField.getText().equals("")) {
            parentUnitId = unitDefaultParentUnitSelect.getValue().getId();
            unitChargeMulti = Float.parseFloat(unitChangeMultiplierInputField.getText());
        }

        unit.setName(unitName);
        unit.setShortName(unitShort);
        unit.setDefaultUnitId(parentUnitId);
        unit.setUnitChange(unitChargeMulti);

        unitDAO.addUnit(unit);
        reInitialize();
        unitNameInputField.setText("");
        unitShortNameInputField.setText("");
        unitDefaultParentUnitSelect.getSelectionModel().clearSelection();
        unitChangeMultiplierInputField.setText("");
    }

    public void modifyUnit() {
        Unit selectedItem = unitList.getSelectionModel().getSelectedItem();
        openModifyWindow(selectedItem);
    }

    public void deleteUnit(ActionEvent actionEvent) {
        Unit selectedItem = unitList.getSelectionModel().getSelectedItem();
        if (new ConfirmationBox().confirmProcess("Törlés megerősítése", "Biztosan törölni szeretné a mértékegységet?")) {
            UnitDAO unitDAO = new UnitDAOImpl();
            unitDAO.removeUnit(selectedItem);
            System.out.println("Torolve");
            reInitialize();
        } else {
            System.out.println("Megsem");
        }
    }

    private void setMultiplierFieldToFloat() {
        unitChangeMultiplierInputField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}([\\.]\\d{0,4})?")) {
                unitChangeMultiplierInputField.setText(oldValue);
            }
        });
    }

    public void openModifyWindow(Unit unit) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/hu/cookerybook/desktop/unit_modify.fxml")
        );

        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UnitModifyController unitModifyController = loader.getController();
        unitModifyController.initializeData(unit, stage);

        stage.setTitle("Mértékegység módosítása");
        stage.show();

        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                reInitialize();
            }
        });
    }

    private void initializeButtons() {
        unitUploadButton.disableProperty().bind((unitNameInputField.textProperty().isEmpty()).or(unitShortNameInputField.textProperty().isEmpty()));
        unitDeleteButton.disableProperty().bind(unitList.getSelectionModel().selectedItemProperty().isNull());
        unitModifyButton.disableProperty().bind(unitList.getSelectionModel().selectedItemProperty().isNull());
    }

    public void reInitialize() {
        loadDefaultUnitSelect();
        loadUnitList();
    }


    public void backToHome(ActionEvent actionEvent) {
        try {
            Desktop.setRoot("homepage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
