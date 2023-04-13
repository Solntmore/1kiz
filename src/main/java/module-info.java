module com.example.demo {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires org.apache.poi.ooxml;

    opens com.example.oneKiz to javafx.fxml;
    exports com.example.oneKiz;
    exports com.example.oneKiz.service;
    opens com.example.oneKiz.service to javafx.fxml;
}