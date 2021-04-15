module hu.cookerybook.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires hu.cookerybook.core;

    opens hu.cookerybook.desktop to javafx.fxml;
    exports hu.cookerybook.desktop;
    exports hu.cookerybook.desktop.controller;
}