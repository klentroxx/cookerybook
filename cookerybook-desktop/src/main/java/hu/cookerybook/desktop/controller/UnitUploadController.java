package hu.cookerybook.desktop.controller;

import hu.cookerybook.core.dao.UnitDAO;
import hu.cookerybook.core.dao.UnitDAOImpl;
import hu.cookerybook.core.model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UnitUploadController implements Initializable {

    @FXML private TextField unitNameInputField;
    @FXML private ListView<String> unitList = new ListView<>();
    @FXML private ChoiceBox<String> unitDefaultParentUnitSelect = new ChoiceBox<>();
    @FXML private Button unitUploadButton;

    public UnitUploadController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initUnitList();
        initDefaultUnitSelect();
    }

    private void initUnitList() {
        UnitDAO unitDAO = new UnitDAOImpl();
        List<Unit> unitsList = unitDAO.getAllUnits();
        List<String> unitNames = new ArrayList<>();
        for (Unit unit : unitsList) {
            unitNames.add(unit.getName() + " (" + unit.getShortName() + ")");
            System.out.println(unitNames);
        }
        ObservableList<String> units = FXCollections.observableArrayList(unitNames);
        unitList.setItems(units);
        System.out.println(unitList.getItems());
    }

    private void initDefaultUnitSelect() {
        UnitDAO unitDAO = new UnitDAOImpl();
        List<Unit> unitsList = unitDAO.getAllParentUnits();
        List<String> unitNames = new ArrayList<>();
        for (Unit unit : unitsList) {
            unitNames.add(unit.getName() + " (" + unit.getShortName() + ")");
            System.out.println(unitNames);
        }
        ObservableList<String> units = FXCollections.observableArrayList(unitNames);
        unitDefaultParentUnitSelect.setItems(units);
        System.out.println(unitDefaultParentUnitSelect.getItems());
    }
}
