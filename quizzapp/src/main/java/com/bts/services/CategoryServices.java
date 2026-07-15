/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services;

import com.bts.pojo.Category;
import com.bts.utils.MyConnectionSingleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class CategoryServices extends QueryServicesBase<Category> {

    @Override
    public PreparedStatement getStm() throws SQLException {
        return MyConnectionSingleton.getInstance().connect().prepareCall("SELECT * FROM category");
    }

    @Override
    public Category getObject(ResultSet res) throws SQLException {
        return new Category(res.getInt("id"), res.getString("name"));
    }
}
