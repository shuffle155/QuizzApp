/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.bts.quizzapp;

import com.bts.pojo.Question;
import com.bts.services.exam.ExamTypes;
import com.bts.services.exam.FixedExam;
import com.bts.services.exam.IExamStrategy;
import com.bts.services.exam.SpecificExam;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ExamController implements Initializable {

    @FXML
    private ComboBox<ExamTypes> cbExamTypes;
    @FXML
    private TextField txtNum;
    @FXML
    private ListView<Question> lvQuestion;
    private List<Question> questions;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
    }

    public void start(ActionEvent e) {
        switch (this.cbExamTypes.getSelectionModel().getSelectedItem()) {
            case SPECIFIC:
                IExamStrategy sExam = new SpecificExam(this.txtNum.getText());
                this.questions = sExam.getQuestions();
                break;
            default:
                IExamStrategy fExam = new FixedExam();
                this.questions = fExam.getQuestions();
        }

        this.lvQuestion.setItems(FXCollections.observableArrayList(this.questions));
    }
}
