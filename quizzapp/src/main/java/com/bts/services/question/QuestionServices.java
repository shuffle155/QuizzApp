/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.question;

import com.bts.pojo.Question;
import com.bts.pojo.QuestionQueryBuilder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh Son
 */
public class QuestionServices extends QuestionServicesBase {

    private QuestionQueryBuilder query;

    public QuestionServices(QuestionQueryBuilder query) {
        this.query = query;
    }

    public QuestionServices() {
    }

    @Override
    public List<Question> getQuestion() throws SQLException {
        PreparedStatement stm = this.query.build();
        ResultSet res = stm.executeQuery();
        List<Question> questions = new ArrayList<>();

        while (res.next()) {
            questions.add(new Question.Builder().setContent(res.getString("content")).setId(res.getInt("id")).build());
        }

        return questions;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(QuestionQueryBuilder query) {
        this.query = query;
    }
}
