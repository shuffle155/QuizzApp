/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.question;

import com.bts.pojo.Question;
import com.bts.utils.MyConnectionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh Son
 */
public class QuestionServices {

    public List<Question> getQuestion() throws SQLException {
        Connection conn = MyConnectionSingleton.getInstance().connect();
        String sql = "SELECT * FROM question";
        PreparedStatement stm = conn.prepareCall(sql);
        ResultSet res = stm.executeQuery();
        List<Question> questions = new ArrayList<>();

        while (res.next()) {
            int id = res.getInt("id");
            String content = res.getString("content");
            System.out.printf("%d - %s\n", id, content);
            questions.add(new Question.Builder().setId(id).setContent(content).build());
        }
        return questions;
    }
}
