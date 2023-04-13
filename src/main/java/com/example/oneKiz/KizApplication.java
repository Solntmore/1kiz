package com.example.oneKiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class KizApplication extends Application {

    private static Stage primaryStage;

    private void setPrimaryStage(Stage stage) {
        KizApplication.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return KizApplication.primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        setPrimaryStage(primaryStage);
        primaryStage.getIcons().add(new Image("logo-romb-png.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(KizApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        primaryStage.setTitle("1,KIZ,");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}