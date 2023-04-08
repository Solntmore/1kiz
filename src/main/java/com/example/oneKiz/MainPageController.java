package com.example.oneKiz;

import com.example.oneKiz.AlertsFactory;
import com.example.oneKiz.Converter;
import com.example.oneKiz.KizApplication;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.oneKiz.Constants.*;

public class MainPageController implements Initializable {

    private final FileChooser fil_chooser = new FileChooser();
    private final DirectoryChooser dir_chooser = new DirectoryChooser();
    private final Converter converter = new Converter();

    /*
    Обрабатывает клик по кнопке "Посмотреть результат"
     */
    EventHandler<ActionEvent> openEvent =
            e -> {
                if (converter.getOutDirectory() == null) {
                    AlertsFactory.getWarningAlert(CHOOSE_DIRECTORY_MESSAGE);
                } else {
                    String path = converter.getOutDirectory() + "\\output.txt";
                    try {
                        File file = new File(path);
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(file);
                    } catch (IllegalArgumentException | IOException ex) {
                        AlertsFactory.getWarningAlert(NO_FILE_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            };

    /*
    Обрабатывает клик по кнопке "Выбрать папку сохранения"
     */
    EventHandler<ActionEvent> saveEvent =
            e -> {

                File file = dir_chooser.showDialog(KizApplication.getPrimaryStage());

                if (file != null) {
                    AlertsFactory.getInfoAlert(SAVE_DIRECTORY_MESSAGE + file.getAbsolutePath());
                    converter.setOutDirectory(file.getAbsolutePath());
                }
            };

    /*
    Обрабатывает клик по кнопке "Выбрать файл для форматирования"
     */
    EventHandler<ActionEvent> chooseEvent = e -> {
        if (converter.getOutDirectory() == null) {
            AlertsFactory.getWarningAlert(CHOOSE_DIRECTORY_MESSAGE);
        } else {
            File file = fil_chooser.showOpenDialog(KizApplication.getPrimaryStage());

            if (file != null) {
                AlertsFactory.getInfoAlert(SUCCESS_SAVE_MESSAGE);
                converter.setInDirectory(file.getAbsolutePath());
                converter.convertXlsToCsv();
            }
        }
    };

    EventHandler<ActionEvent> guideEvent = e -> {
        try {
            Parent guideParent = FXMLLoader.load(getClass().getResource("guideFirst.fxml"));
            Scene guideScene = new Scene(guideParent);
            Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            appStage.setScene(guideScene);
            appStage.show();
        } catch (IOException ex) {
            AlertsFactory.getWarningAlert("Упс, кажется что-то пошло не так! Не удалось открыть инструкцию:(");
            throw new RuntimeException(ex);
        }
    };

    EventHandler<ActionEvent> reportEvent = e -> {
            AlertsFactory.getInfoAlert("Буду рад узнать ваши впечатления и пожелания:\nEmail: " +
                    "solntsev1099@yandex.ru\nTelegram: https://t.me/solntmore");
    };

    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane subMain;
    @FXML
    private AnchorPane slider;
    @FXML
    private Button chooseButton;
    @FXML
    private Button openButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button guideButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button menu;
    @FXML
    private Button menuBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseButton.setOnAction(chooseEvent);
        saveButton.setOnAction(saveEvent);
        openButton.setOnAction(openEvent);
        reportButton.setOnAction(reportEvent);
        guideButton.setOnAction(guideEvent);
        slider.setTranslateX(-205);
        menu.setOnAction(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-205);

            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(false);
                menuBack.setVisible(true);
            });
        });

        menuBack.setOnAction(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-205);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent x) -> {
                menu.setVisible(true);
                menuBack.setVisible(false);
            });
        });
    }
}
