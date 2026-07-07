/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.bts.quizzapp;

import com.bts.pojo.Category;
import com.bts.pojo.Level;
import com.bts.pojo.Question;
import com.bts.pojo.QuestionQueryBuilder;
import com.bts.utils.Configs;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    private List<Question> questions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbSearchCates.setItems(FXCollections.observableArrayList(Configs.c.getCates()));
            this.cbSearchLevels.setItems(FXCollections.observableArrayList(Configs.l.getLevels()));
        } catch (SQLException ex) {
            Logger.getLogger(PracticeController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void start(ActionEvent e) throws SQLException {
        QuestionQueryBuilder b = new QuestionQueryBuilder().setLimit(this.txtNum.getText())
                .withCategory(this.cbSearchCates.getSelectionModel().getSelectedItem())
                .withLevel(this.cbSearchLevels.getSelectionModel().getSelectedItem());
        Configs.q.setQuery(b);
        this.questions = Configs.q.getQuestion();
    }
}
