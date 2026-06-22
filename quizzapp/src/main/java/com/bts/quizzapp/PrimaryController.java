package com.bts.quizzapp;

import com.bts.utils.MyAlertSingleton;
import com.bts.utils.themes.LightFactory;
import com.bts.utils.themes.ThemeManager;
import com.bts.utils.themes.ThemeTypes;
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
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
    }
}
