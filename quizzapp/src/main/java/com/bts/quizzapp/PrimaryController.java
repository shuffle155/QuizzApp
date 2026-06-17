package com.bts.quizzapp;

import com.bts.utils.MyAlertSingleton;
import com.bts.utils.ThemeTypes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<ThemeTypes> cbThemes;

    public void manageQuestion(ActionEvent act) {
        MyAlertSingleton.getInstance().showMessage("1 Coming soon...");
    }

    public void practice(ActionEvent act) {
        MyAlertSingleton.getInstance().showMessage("2 Coming soon...");
    }

    public void exam(ActionEvent act) {
        MyAlertSingleton.getInstance().showMessage("3 Coming soon...");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(ThemeTypes.values()));
    }

    public void changeTheme(ActionEvent act) {
        switch (this.cbThemes.getSelectionModel().getSelectedItem()) {
            case LIGHT:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("lighttheme.css").toExternalForm());
                break;
            case DARK:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("darktheme.css").toExternalForm());
                break;
            default:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("style.css").toExternalForm());
        }
    }
}
