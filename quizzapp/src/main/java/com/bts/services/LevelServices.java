/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services;

import com.bts.pojo.Level;
import com.bts.utils.MyConnectionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelServices {

    public List<Level> getLevels() throws SQLException {
        Connection conn = MyConnectionSingleton.getInstance().connect();
        String sql = "SELECT * FROM level";
        PreparedStatement stm = conn.prepareCall(sql);
        ResultSet res = stm.executeQuery();
        List<Level> levels = new ArrayList<>();

        while (res.next()) {
            int id = res.getInt("id");
            String name = res.getString("name");
            levels.add(new Level(id, name));
        }
        return levels;
    }
}
