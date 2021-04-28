package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.UnitDAO;
import hu.cookerybook.core.dao.UnitDAOImpl;
import hu.cookerybook.core.model.Unit;
import hu.cookerybook.desktop.Desktop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UnitModifyController implements Initializable {

    @FXML private Label unitNameLabel;
    @FXML private TextField unitNameInputField;
    @FXML private TextField unitShortNameInputField;
    @FXML private ComboBox<Unit> unitDefaultParentUnitSelect;
    @FXML private TextField unitChangeMultiplierInputField;
    Unit unitToBeModified;

    UnitModifyController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDefaultUnitSelect();
        initializePage();
    }

    public void initializePage() {
        unitNameLabel.setText(this.unitToBeModified.toString());
        unitNameInputField.setText(unitToBeModified.getName());
        unitShortNameInputField.setText(unitToBeModified.getShortName());

        unitChangeMultiplierInputField.setText(Float.toString(unitToBeModified.getUnitChange()));
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

    public void initializeData(Unit unit) {
        this.unitToBeModified = unit;
    }

}
