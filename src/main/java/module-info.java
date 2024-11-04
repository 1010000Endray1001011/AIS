module com.kitisgang.aisgasstation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.kitisgang.aisgasstation to javafx.fxml;
    exports com.kitisgang.aisgasstation;
    exports com.kitisgang.aisgasstation.Controller;
    opens com.kitisgang.aisgasstation.Controller to javafx.fxml;
    exports com.kitisgang.aisgasstation.dbClass;
    opens com.kitisgang.aisgasstation.dbClass to javafx.fxml;
}