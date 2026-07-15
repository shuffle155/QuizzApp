/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.question;

import com.bts.pojo.Question;
import com.bts.pojo.QuestionQueryBuilder;
import com.bts.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionsFacade {

    public static List<Question> getQuestions(QuestionQueryBuilder question) throws SQLException {
        Configs.q.setQuery(question);
        return Configs.q.getT();
    }

    public static List<Question> getLazyQuestions(QuestionQueryBuilder question) throws SQLException {
        Configs.q.setQuery(question);
        return new QuestionServicesDecorator(Configs.q).getT();
    }
}
