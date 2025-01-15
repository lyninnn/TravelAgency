module org.example.travelagency {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.persistence;

    opens org.example.travelagency to javafx.fxml;
    opens org.example.travelagency.Controllers to javafx.fxml;
    exports org.example.travelagency;
    exports org.example.travelagency.Controllers;
}