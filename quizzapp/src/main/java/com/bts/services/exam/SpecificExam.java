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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class SpecificExam implements IExamStrategy {

    private int num;

    public SpecificExam(int num) {
        this.num = num;
    }

    public SpecificExam(String num) {
        this.num = Integer.parseInt(num);
    }

    @Override
    public List<Question> getQuestions() {
        try {
            QuestionQueryBuilder question = new QuestionQueryBuilder().setLimit(this.num).setOrderBy("rand()");
            return QuestionsFacade.getLazyQuestions(question);
        } catch (SQLException ex) {
            Logger.getLogger(SpecificExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
