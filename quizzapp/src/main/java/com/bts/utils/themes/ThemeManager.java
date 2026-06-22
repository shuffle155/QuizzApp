/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.utils.themes;

import com.bts.quizzapp.App;
import javafx.scene.Scene;

/**
 *
 * @author Thanh Son
 */
public class ThemeManager {

    /**
     * @param aF the f to set
     */
    public static void setF(ThemeAbstractFactory aF) {
        f = aF;
    }
    private static ThemeAbstractFactory f = new DefaultFactory();

    public static void applyTheme(Scene s) {
        s.getRoot().getStylesheets().clear();
        s.getRoot().getStylesheets().add(f.getStyleSheet());
    }
}
