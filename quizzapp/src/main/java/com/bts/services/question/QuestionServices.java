
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.question;

import com.bts.pojo.Question;
import com.bts.pojo.QuestionQueryBuilder;
import com.bts.services.QueryServicesBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thanh Son
 */
public class QuestionServices extends QueryServicesBase<Question> implements IQuestionServicesBase {

    private QuestionQueryBuilder query;

    public QuestionServices(QuestionQueryBuilder query) {
        this.query = query;
    }

    public QuestionServices() {
    }

    /**
     * @param query the query to set
     */
    public void setQuery(QuestionQueryBuilder query) {
        this.query = query;
    }

    @Override
    public PreparedStatement getStm() throws SQLException {
        return this.query.build();
    }

    @Override
    public Question getObject(ResultSet res) throws SQLException {
        return new Question.Builder().setId(res.getInt("id")).setContent(res.getString("content")).build();
    }
}
