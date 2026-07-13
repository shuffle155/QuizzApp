/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services;

import com.bts.pojo.Category;
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
public class CategoryServices {

    public List<Category> getCates() throws SQLException {
        String sql = "SELECT * FROM category";
        PreparedStatement stm = MyConnectionSingleton.getInstance().connect().prepareCall(sql);
        ResultSet res = stm.executeQuery();
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
