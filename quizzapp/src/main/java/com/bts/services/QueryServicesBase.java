/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class QueryServicesBase<T> {

    public List<T> getT() throws SQLException {
        PreparedStatement stm = this.getStm();
        ResultSet res = stm.executeQuery();
        List<T> listT = new ArrayList<>();

        while (res.next()) {
            listT.add(this.getObject(res));
        }
        return listT;

    }

    public abstract PreparedStatement getStm() throws SQLException;

    public abstract T getObject(ResultSet res) throws SQLException;
}
