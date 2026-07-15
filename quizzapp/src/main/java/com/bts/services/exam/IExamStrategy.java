/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services.exam;

import com.bts.pojo.Question;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IExamStrategy {

    public List<Question> getQuestions();
}
