/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.question;

import com.bts.pojo.Choice;
import com.bts.pojo.Question;
import com.bts.utils.MyConnectionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class UpdateQuestionServices {

    public void addQuestion(Question q, List<Choice> c) throws SQLException {
        Connection conn = MyConnectionSingleton.getInstance().connect();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, category_id, level_id) VALUES(?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setInt(2, q.getCategory().getId());
        stm.setInt(3, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            ResultSet r = stm.getGeneratedKeys();

            if (r.next()) {
                int questionId = r.getInt(1);

                if (questionId > 0) {
                    sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
                    stm = conn.prepareCall(sql);

                    for (var choice : c) {
                        stm.setString(1, choice.getContent());
                        stm.setBoolean(2, choice.isCorrect());
                        stm.setInt(3, questionId);
                        stm.executeUpdate();
                    }
                    conn.commit();
                }
            }
        }
    }
}
