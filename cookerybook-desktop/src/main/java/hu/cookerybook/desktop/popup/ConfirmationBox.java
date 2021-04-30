package hu.cookerybook.desktop.popup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationBox {

    private boolean returnValue;

    public void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button acceptButton = new Button("Igen");
        Button denyButton = new Button("Nem");
        acceptButton.setOnAction(e -> {
            this.returnValue = true;
            window.close();
        });
        denyButton.setOnAction(e -> {
            this.returnValue = false;
            window.close();
        });

        VBox layout = new VBox(10);
        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.getChildren().addAll(acceptButton, denyButton);
        layout.getChildren().addAll(label, buttonRow);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 50, 20, 50));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public boolean confirmProcess(String title, String message) {
        display(title, message);
        return this.returnValue;
    }

}
