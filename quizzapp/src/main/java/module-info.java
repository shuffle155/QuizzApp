module com.bts.quizzapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.bts.quizzapp to javafx.fxml;
    exports com.bts.quizzapp;
}
