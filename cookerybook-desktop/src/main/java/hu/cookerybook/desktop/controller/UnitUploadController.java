package hu.cookerybook.desktop.controller;


import hu.cookerybook.core.dao.UnitDAO;
import hu.cookerybook.core.dao.UnitDAOImpl;
import hu.cookerybook.core.model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class UnitUploadController {

    public UnitUploadController() {
        loadAllUnits();
    }

    @FXML
    private ListView<String> unitList;

    public void loadAllUnits() {
        UnitDAO unitDAO = new UnitDAOImpl();
        List<Unit> unitsList = unitDAO.getAllUnits();
        List<String> unitNames = new ArrayList<>();
        for (Unit unit : unitsList) {
            unitNames.add(unit.getName() + " (" + unit.getShortName() + ")");
            System.out.println(unitNames);
        }
        ObservableList<String> units = FXCollections.observableArrayList(unitNames);
        System.out.println(unitList.getItems());
        unitList.getItems();
    }

}
