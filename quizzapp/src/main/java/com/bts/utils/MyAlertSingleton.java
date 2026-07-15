/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author admin
 */
public class MyAlertSingleton {

    private static MyAlertSingleton instance;
    private final Alert alert;

    private MyAlertSingleton() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("quizzapp");
        alert.setContentText("Quizz App");
    }

    public static MyAlertSingleton getInstance() {
        if (instance == null) {
            instance = new MyAlertSingleton();
        }
        return instance;
    }

    public void showMessage(String content) {
        this.alert.setContentText(content);
        this.alert.show();
    }

    public Optional<ButtonType> showMessage(String content, Alert.AlertType alertType) {
        this.alert.setContentText(content);
        this.alert.setAlertType(alertType);
        return this.alert.showAndWait();
    }
}
