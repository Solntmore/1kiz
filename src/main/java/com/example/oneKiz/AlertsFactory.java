package com.example.oneKiz;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

public class AlertsFactory {
    public static void getInfoAlert(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, title);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    public static void getWarningAlert(String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING, title);
        alert.setTitle("Warning alert");
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}
