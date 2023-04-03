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

public class MainPageController {

    private final FileChooser fil_chooser = new FileChooser();
    private final DirectoryChooser dir_chooser = new DirectoryChooser();
    private final Converter converter = new Converter();

    @FXML
    private Button chooseButton;
    @FXML
    private Button openButton;
    @FXML
    private Button saveButton;
   /* @FXML
    private Label centralLabel;
*/
    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane subMain;



    EventHandler<ActionEvent> openEvent =
            e -> {
                if (converter.getOutDirectory() == null) {
                   /* centralLabel.setText("Сначала выберите папку сохранения, только потом можно выбрать файл, который вы " +
                            "хотите конвертировать!");*/
                } else {
                    String path = converter.getOutDirectory() + "\\output.txt";
                    try {
                        File file = new File(path);
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(file);
                    } catch (IOException ex) {
                       /* centralLabel.setText("Сначала выберите папку сохранения, только потом можно выбрать файл, который вы " +
                                "хотите конвертировать!");*/
                        throw new RuntimeException(ex);
                    }
                }
            };

    EventHandler<ActionEvent> saveEvent =
            e -> {

                File file = dir_chooser.showDialog(KizApplication.getPrimaryStage());

                if (file != null) {
                  /*  centralLabel.setText("Файлы будут сохраняться в папку по адресу " + file.getAbsolutePath()
                    );*/
                    converter.setOutDirectory(file.getAbsolutePath());
                }
            };
    EventHandler<ActionEvent> chooseEvent = e -> {
        if (converter.getOutDirectory() == null) {
           /* centralLabel.setText("Сначала выберите папку сохранения, только потом можно выбрать файл, который вы " +
                    "хотите конвертировать!");*/
        } else {
            File file = fil_chooser.showOpenDialog(KizApplication.getPrimaryStage());

            if (file != null) {
               /* centralLabel.setText("Кажется получилось, проверьте результат в папке сохранения, которую вы выбрали " +
                        "ранее");*/
                converter.setInDirectory(file.getAbsolutePath());
                converter.convertXlsToCsv();
            }
        }
    };

    @FXML
    private void initialize() {
        chooseButton.setOnAction(chooseEvent);
        saveButton.setOnAction(saveEvent);
        openButton.setOnAction(openEvent);
    }
}
