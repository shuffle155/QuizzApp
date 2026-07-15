/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.exam;

import com.bts.pojo.Question;
import com.bts.pojo.QuestionQueryBuilder;
import com.bts.services.question.QuestionsFacade;
import com.bts.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FixedExam implements IExamStrategy {

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < Configs.RATE.length; i++) {
            try {
                QuestionQueryBuilder question = new QuestionQueryBuilder().
                        withLevel(i + 1).
                        setOrderBy("rand()").
                        setLimit((int) (Configs.RATE[i] * Configs.EXAM_NUM));
                questions.addAll(QuestionsFacade.getLazyQuestions(question));
            } catch (SQLException ex) {
                Logger.getLogger(FixedExam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return questions;
    }
}
