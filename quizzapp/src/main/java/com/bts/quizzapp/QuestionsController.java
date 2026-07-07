/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.bts.quizzapp;

import com.bts.pojo.Category;
import com.bts.pojo.Choice;
import com.bts.pojo.Question;
import com.bts.pojo.Level;
import com.bts.pojo.QuestionQueryBuilder;
import com.bts.utils.Configs;
import com.bts.utils.MyAlertSingleton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private ComboBox<Category> cbSearchCates;
    @FXML
    private ComboBox<Level> cbSearchLevels;
    @FXML
    private TableView<Question> tvQuestions;
    @FXML
    private VBox vChoices;
    @FXML
    private TextArea txtContent;
    @FXML
    private ToggleGroup toggle;
    @FXML
    private TextField txtKeyWord;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadColumns();
        this.loadTableQuestions();
        try {
            this.cbCates.setItems(FXCollections.observableArrayList(Configs.c.getCates()));
            this.cbLevels.setItems(FXCollections.observableArrayList(Configs.l.getLevels()));
            this.cbSearchCates.setItems(FXCollections.observableArrayList(Configs.c.getCates()));
            this.cbSearchLevels.setItems(FXCollections.observableArrayList(Configs.l.getLevels()));
        } catch (SQLException ex) {

        }

        this.txtKeyWord.textProperty().addListener(e -> {
            this.loadTableQuestions();
        });

        this.cbSearchCates.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadTableQuestions();
        });

        this.cbSearchLevels.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadTableQuestions();
        });
    }

    private void loadColumns() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);
        TableColumn colContent = new TableColumn("Content");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(300);
        this.tvQuestions.getColumns().addAll(colId, colContent);
    }

    public void addChoices(ActionEvent e) {
        HBox h = new HBox();
        h.getStyleClass().add("Container");
        RadioButton r = new RadioButton();
        r.setToggleGroup(toggle);
        TextField t = new TextField();
        h.getChildren().addAll(r, t);
        this.vChoices.getChildren().add(h);
    }

    public void addQuestions(ActionEvent e) {
        Question q = new Question.Builder().setCategory(this.cbCates.getSelectionModel().getSelectedItem())
                .setContent(this.txtContent.getText())
                .setLevel(this.cbLevels.getSelectionModel().getSelectedItem())
                .build();
        List<Choice> choices = new ArrayList<>();

        for (var hbox : this.vChoices.getChildren()) {
            HBox h = (HBox) hbox;
            RadioButton r = (RadioButton) h.getChildren().get(0);
            TextField t = (TextField) h.getChildren().get(1);
            choices.add(new Choice(t.getText(), r.isSelected()));
        }

        try {
            Optional<ButtonType> b = MyAlertSingleton.getInstance().showMessage("Bạn có chắn chắn muốn thêm không?",
                    Alert.AlertType.CONFIRMATION);

            if (b.isPresent() && b.get() == ButtonType.OK) {
                Configs.u.addQuestion(q, choices);
                MyAlertSingleton.getInstance().showMessage("Thêm câu hỏi thành công");
            }
        } catch (SQLException ex) {
            MyAlertSingleton.getInstance().showMessage("Thêm câu hỏi thất bại do: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void loadTableQuestions() {
        try {
            QuestionQueryBuilder query = new QuestionQueryBuilder().withKeyWords(this.txtKeyWord.getText())
                    .withCategory(this.cbSearchCates.getSelectionModel().getSelectedItem())
                    .withLevel(this.cbSearchLevels.getSelectionModel().getSelectedItem());
            Configs.q.setQuery(query);
            this.tvQuestions.setItems(FXCollections.observableArrayList(Configs.q.getQuestion()));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
