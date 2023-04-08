package com.example.oneKiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuideThirdController implements Initializable {

    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;

    EventHandler<ActionEvent> backEvent = e -> {
        try {
            Parent guideParent = FXMLLoader.load(getClass().getResource("guideSecond.fxml"));
            Scene guideScene = new Scene(guideParent);
            Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            appStage.setScene(guideScene);
            appStage.show();
        } catch (IOException ex) {
            AlertsFactory.getWarningAlert("Упс, кажется что-то пошло не так! Не удалось открыть инструкцию:(");
            throw new RuntimeException(ex);
        }
    };

    EventHandler<ActionEvent> nextEvent = e -> {
        try {
            Parent guideParent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene guideScene = new Scene(guideParent);
            Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            appStage.setScene(guideScene);
            appStage.show();
        } catch (IOException ex) {
            AlertsFactory.getWarningAlert("Упс, кажется что-то пошло не так! Не удалось открыть инструкцию:(");
            throw new RuntimeException(ex);
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nextButton.setOnAction(nextEvent);
        backButton.setOnAction(backEvent);
    }

}
