/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.utils;

import com.bts.quizzapp.App;
import com.bts.utils.themes.ThemeManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class MyStageSingleton {

    private static MyStageSingleton instance;
    private Stage stage;

    private MyStageSingleton() {
        this.stage = new Stage();
        this.stage.setTitle("Quizz App");
    }

    public static MyStageSingleton getInstance() {
        if (instance == null) {
            instance = new MyStageSingleton();
        }
        return instance;
    }

    public void showStage(String fxml) throws IOException {
        Scene scene = new Scene(new FXMLLoader(App.class.getResource(fxml + ".fxml")).load());
        ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.show();
    }
}
