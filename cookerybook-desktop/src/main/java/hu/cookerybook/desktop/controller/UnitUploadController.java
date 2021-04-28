package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.UnitDAO;
import hu.cookerybook.core.dao.UnitDAOImpl;
import hu.cookerybook.core.model.Unit;
import hu.cookerybook.desktop.Desktop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class UnitUploadController implements Initializable {

    @FXML private TextField unitNameInputField;
    @FXML private TextField unitShortNameInputField;
    @FXML private TextField unitChangeMultiplierInputField;
    @FXML private ComboBox<Unit> unitDefaultParentUnitSelect;
    @FXML private ListView<Unit> unitList;
    @FXML private Button unitUploadButton;

    public UnitUploadController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUnitList();
        loadDefaultUnitSelect();
        setMultiplierFieldToFloat();
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
        loadDefaultUnitSelect();
        loadUnitList();
    }

    public void modifyUnit() {
        Unit selectedItem = unitList.getSelectionModel().getSelectedItem();
        openModifyWindow(selectedItem);
        System.out.println(selectedItem);
    }

    public void deleteUnit(ActionEvent actionEvent) {
        Unit selectedItem = unitList.getSelectionModel().getSelectedItem();
    }

    private void setMultiplierFieldToFloat() {
        unitChangeMultiplierInputField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}([\\.]\\d{0,4})?")) {
                unitChangeMultiplierInputField.setText(oldValue);
            }
        });
    }

    public Stage openModifyWindow(Unit unit) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("unit_modify.fxml")
        );

        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        UnitModifyController umc = new UnitModifyController();
        umc.initializeData(unit);

        stage.show();

        return stage;
    }


}
