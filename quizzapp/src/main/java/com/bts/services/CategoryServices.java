/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services;

import com.bts.pojo.Category;
import com.bts.utils.MyConnectionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class CategoryServices {

    public List<Category> getCates() throws SQLException {
        Connection conn = MyConnectionSingleton.getInstance().connect();
        String sql = "SELECT * FROM category";
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery(sql);
        List<Category> cates = new ArrayList<>();

        while (res.next()) {
            int id = res.getInt("id");
            String name = res.getString("name");
            System.out.printf("%d - %s\n", id, name);
            cates.add(new Category(id, name));
        }
        return cates;
    }
}
