package com.bts.quizzapp;

import com.bts.utils.MyStageSingleton;
import com.bts.utils.themes.ThemeTypes;
import java.io.IOException;
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

    public void manageQuestion(ActionEvent act) throws IOException {
        MyStageSingleton.getInstance().showStage("questions");
    }

    public void practice(ActionEvent act) throws IOException {
        MyStageSingleton.getInstance().showStage("practice");
    }

    public void exam(ActionEvent act) throws IOException {
        MyStageSingleton.getInstance().showStage("exam");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(ThemeTypes.values()));
    }

    public void changeTheme(ActionEvent act) {
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
    }
}
