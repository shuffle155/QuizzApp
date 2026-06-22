/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.bts.utils.themes;

import javafx.scene.Scene;

/**
 *
 * @author admin
 */
public enum ThemeTypes {
    DEFAULT {
        @Override
        public void updateTheme(Scene s) {
            ThemeManager.setF(new DefaultFactory());
            ThemeManager.applyTheme(s);
        }
    }, LIGHT {
        @Override
        public void updateTheme(Scene s) {
            ThemeManager.setF(new LightFactory());
            ThemeManager.applyTheme(s);
        }
    }, DARK {
        @Override
        public void updateTheme(Scene s) {
            ThemeManager.setF(new DarkFactory());
            ThemeManager.applyTheme(s);
        }
    };

    public abstract void updateTheme(Scene s);
}
