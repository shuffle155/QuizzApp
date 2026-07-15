/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.bts.quizzapp;

import com.bts.pojo.Category;
import com.bts.pojo.Level;
import com.bts.pojo.Question;
import com.bts.pojo.QuestionQueryBuilder;
import com.bts.services.FlyweightFactory;
import com.bts.services.question.QuestionServicesDecorator;
import com.bts.utils.Configs;
import com.bts.utils.MyAlertSingleton;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {

    @FXML
    private ComboBox<Category> cbSearchCates;
    @FXML
    private ComboBox<Level> cbSearchLevels;
    @FXML
    private TextField txtNum;
    @FXML
    private Label lbContent;
    @FXML
    private VBox vChoices;
    private List<Question> questions;
    private int currIdx = -1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbSearchCates.setItems(FXCollections.observableArrayList(FlyweightFactory.getData(Configs.c, Configs.CATEGORY_KEY)));
            this.cbSearchLevels.setItems(FXCollections.observableArrayList(FlyweightFactory.getData(Configs.l, Configs.LEVEL_KEY)));
        } catch (SQLException ex) {
            Logger.getLogger(PracticeController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void start(ActionEvent e) throws SQLException {
        QuestionQueryBuilder b = new QuestionQueryBuilder().setLimit(this.txtNum.getText())
                .withCategory(this.cbSearchCates.getSelectionModel().getSelectedItem())
                .withLevel(this.cbSearchLevels.getSelectionModel().getSelectedItem());
        Configs.q.setQuery(b);
        this.questions = new QuestionServicesDecorator(Configs.q).getT();
        this.loadQuestion(1);
    }

    public void next(ActionEvent e) {
        this.loadQuestion(1);
    }

    public void checkAns(ActionEvent e) {
        Question q = this.questions.get(this.currIdx);

        for (int i = 0; i < this.vChoices.getChildren().size(); i++) {
            RadioButton r = (RadioButton) this.vChoices.getChildren().get(i);

            if (r.isSelected()) {
                if (q.getChoices().get(i).isCorrect() == true) {
                    MyAlertSingleton.getInstance().showMessage("CHINH XAC", Alert.AlertType.CONFIRMATION);
                } else {
                    MyAlertSingleton.getInstance().showMessage("SAI ROI", Alert.AlertType.ERROR);
                }
                break;
            }
        }
    }

    public void previous(ActionEvent e) {
        this.loadQuestion(-1);
    }

    private void loadQuestion(int step) {
        int nextIdx = this.currIdx + step;

        if (nextIdx >= 0 && nextIdx < this.questions.size()) {
            this.currIdx = nextIdx;
            Question q = this.questions.get(this.currIdx);
            this.lbContent.setText(q.getContent());
            ToggleGroup toggle = new ToggleGroup();
            this.vChoices.getChildren().clear();

            for (var c : q.getChoices()) {
                RadioButton r = new RadioButton(c.getContent());
                r.setToggleGroup(toggle);
                this.vChoices.getChildren().add(r);
            }
        }
    }
}
