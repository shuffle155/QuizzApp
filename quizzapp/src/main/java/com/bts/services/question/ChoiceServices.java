/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.question;

import com.bts.pojo.Choice;
import com.bts.utils.MyConnectionSingleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh Son
 */
public class ChoiceServices {

    public List<Choice> getChoices(int id) throws SQLException {
        String sql = "SELECT * FROM choice WHERE question_id=?";
        PreparedStatement stm = MyConnectionSingleton.getInstance().connect().prepareCall(sql);
        stm.setInt(1, id);
        ResultSet res = stm.executeQuery();
        List<Choice> choices = new ArrayList<>();

        while (res.next()) {
            choices.add(new Choice(res.getInt("id"), res.getString("content"), res.getBoolean("is_correct")));
        }

        return choices;
    }
}
