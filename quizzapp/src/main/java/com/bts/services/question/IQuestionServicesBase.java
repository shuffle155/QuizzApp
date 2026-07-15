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
public interface IQuestionServicesBase {

    public List<Question> getT() throws SQLException;
}
