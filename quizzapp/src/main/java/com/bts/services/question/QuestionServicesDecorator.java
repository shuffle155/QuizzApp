/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.question;

import com.bts.pojo.Question;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Thanh Son
 */
public class QuestionServicesDecorator implements IQuestionServicesBase {

    private final IQuestionServicesBase services;

    public QuestionServicesDecorator(IQuestionServicesBase services) {
        this.services = services;
    }

    @Override
    public List<Question> getT() throws SQLException {
        List<Question> questions = this.services.getT();
        ChoiceServices cs = new ChoiceServices();

        for (var q : questions) {
            q.setChoices(cs.getChoices(q.getId()));
        }

        return questions;
    }
}
