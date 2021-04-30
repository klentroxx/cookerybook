package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.UnitDAO;
import hu.cookerybook.core.dao.UnitDAOImpl;
import hu.cookerybook.core.model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private Unit unitToBeModified;
    private Stage stage;

    public UnitModifyController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDefaultUnitSelect();
    }

    public void initializePage() {
        UnitDAO unitDAO = new UnitDAOImpl();
        Unit parentUnit = null;
        try {
            parentUnit = unitDAO.getUnit(this.unitToBeModified.getDefaultUnitId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("A mertekegysegnek nincs szuloje.");
        }
        this.unitNameLabel.setText(this.unitToBeModified.toString());
        this.unitNameInputField.setText(this.unitToBeModified.getName());
        this.unitShortNameInputField.setText(this.unitToBeModified.getShortName());
        if (parentUnit != null) this.unitDefaultParentUnitSelect.getSelectionModel().select(parentUnit);
        if (this.unitToBeModified.getUnitChange() != (float) 0) this.unitChangeMultiplierInputField.setText(Float.toString(this.unitToBeModified.getUnitChange()));
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

    public void initializeData(Unit unit, Stage stage) {
        this.unitToBeModified = unit;
        this.stage = stage;
        initializePage();
    }


    @FXML private void saveModification(ActionEvent actionEvent) {
        UnitDAO unitDAO = new UnitDAOImpl();
        Unit modifiedUnit = new Unit();
        String unitName = this.unitNameInputField.getText();
        String unitShort = this.unitShortNameInputField.getText();
        int unitParent;
        float unitChange;

        System.out.println("SelectedIndex: " + this.unitDefaultParentUnitSelect.getSelectionModel().getSelectedItem());
        System.out.println("Multiplier nem üres: " + !this.unitChangeMultiplierInputField.getText().equals(""));

        if (this.unitDefaultParentUnitSelect.getSelectionModel().getSelectedItem() != null && !this.unitChangeMultiplierInputField.getText().equals("")) {
            unitParent = this.unitDefaultParentUnitSelect.getSelectionModel().getSelectedItem().getId();
            unitChange = Float.parseFloat(this.unitChangeMultiplierInputField.getText());
        } else {
            unitParent = 0;
            unitChange = 0;
        }

        modifiedUnit.setId(this.unitToBeModified.getId());
        modifiedUnit.setName(unitName);
        modifiedUnit.setShortName(unitShort);
        modifiedUnit.setDefaultUnitId(unitParent);
        modifiedUnit.setUnitChange(unitChange);

        unitDAO.updateUnit(modifiedUnit);

        stage.close();
    }

    @FXML private void cancel(ActionEvent actionEvent) {
        stage.close();
    }
}
