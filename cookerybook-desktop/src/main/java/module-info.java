module hu.cookerybook {
    requires javafx.controls;
    requires javafx.fxml;

    opens hu.cookerybook to javafx.fxml;
    exports hu.cookerybook;
}