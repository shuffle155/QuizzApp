/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.bts.quizzapp;

import com.bts.pojo.Choice;
import com.bts.pojo.Question;
import com.bts.services.exam.ExamTypes;
import com.bts.services.exam.FixedExam;
import com.bts.services.exam.IExamStrategy;
import com.bts.services.exam.SpecificExam;
import com.bts.utils.MyAlertSingleton;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

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
    private Map<Integer, Choice> answers = new HashMap<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
        this.txtNum.setVisible(false);
        this.cbExamTypes.getSelectionModel().selectedItemProperty().addListener(e -> {

            if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC) {
                this.txtNum.setVisible(true);
            } else {
                this.txtNum.setVisible(false);
            }

            this.lvQuestion.setCellFactory(clbck -> new ListCell<>() {
                @Override
                protected void updateItem(Question t, boolean bln) {
                    super.updateItem(t, bln);

                    if (t == null || bln == true) {
                        setGraphic(null);
                    } else {
                        VBox vb = new VBox(5);
                        vb.setStyle("-fx-border-width: 2;"
                                + "-fx-border-color: green;"
                                + "-fx-padding: 5");
                        Text txt = new Text(t.getContent());
                        vb.getChildren().add(txt);
                        ToggleGroup toggle = new ToggleGroup();

                        for (var choice : t.getChoices()) {
                            RadioButton rd = new RadioButton(choice.getContent());
                            rd.setToggleGroup(toggle);

                            if (answers.get(t.getId()) == choice) {
                                rd.setSelected(true);
                            }

                            rd.setOnAction(eh -> {

                                if (rd.isSelected() == true) {
                                    answers.put(t.getId(), choice);
                                }

                            });

                            vb.getChildren().add(rd);
                        }

                        setGraphic(vb);
                    }
                }
            });
        });
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

    public void grading(ActionEvent e) {
        int cnt = 0;

        for (var answer : answers.values()) {

            if (answer.isCorrect() == true) {
                cnt++;
            }
        }

        MyAlertSingleton.getInstance().showMessage(String.format("%d/%d", cnt, this.questions.size()));
    }

    public void saveExam(ActionEvent e) {
        if (this.questions == null || this.questions.isEmpty()) {
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu đề thi");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {

            try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {

                for (int i = 0; i < questions.size(); i++) {
                    Question q = questions.get(i);
                    writer.println(q.getContent());

                    for (Choice choice : q.getChoices()) {
                        writer.println("   [ ] " + choice.getContent());
                    }

                    writer.println();
                }
                MyAlertSingleton.getInstance().showMessage("Lưu đề thi thành công");
            } catch (IOException ex) {
                MyAlertSingleton.getInstance().showMessage("Có lỗi xảy ra khi lưu file!");
                ex.printStackTrace();
            }
        }
    }
}
