package com.example.oneKiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.example.oneKiz.Constants.*;

public class MainPageController {

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

    @FXML
    private Button chooseButton;
    @FXML
    private Button openButton;
    @FXML
    private Button saveButton;
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane subMain;
    @FXML
    private Button closeButton1;

    @FXML
    private void initialize() {
        chooseButton.setOnAction(chooseEvent);
        saveButton.setOnAction(saveEvent);
        openButton.setOnAction(openEvent);
    }
}
