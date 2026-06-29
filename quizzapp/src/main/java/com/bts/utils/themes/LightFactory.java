/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.utils.themes;

import com.bts.quizzapp.App;

/**
 *
 * @author Thanh Son
 */
public class LightFactory extends ThemeAbstractFactory {

    @Override
    public String getStyleSheet() {
        return App.class.getResource("lighttheme.css").toExternalForm();
    }

}
