/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.question;

import com.bts.pojo.Category;
import com.bts.pojo.Level;
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

    public List<Question> getQuestion(String kw, Category cate, Level lv) throws SQLException {
        Connection conn = MyConnectionSingleton.getInstance().connect();
        String sql = "SELECT * FROM question WHERE 1=1";
        List<Question> questions = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        
        if(kw!=null&&!kw.isEmpty()) {
            sql += " content like concat('%', ?, '%')";
            params.add(kw);
        }
        
        if(cate!=null) {
            sql += " category_id = ?";
            params.add(cate);
        }
        
        if(lv!=null) {
            sql += " level_id = ?";
            params.add(lv);
        }       
        
        PreparedStatement stm = conn.prepareCall(sql);
        
        for(int i = 0; i < params.size(); i++)
            stm.setObject(i + 1, params.get(i));
                
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            int id = res.getInt("id");
            String content = res.getString("content");
            System.out.printf("%d - %s\n", id, content);
            questions.add(new Question.Builder().setId(id).setContent(content).build());
        }
        return questions;
    }
}
