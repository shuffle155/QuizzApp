/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.utils;

import com.bts.services.CategoryServices;
import com.bts.services.LevelServices;
import com.bts.services.question.QuestionServices;
import com.bts.services.question.UpdateQuestionServices;

/**
 *
 * @author admin
 */
public class Configs {

    public static final CategoryServices c = new CategoryServices();
    public static final LevelServices l = new LevelServices();
    public static final QuestionServices q = new QuestionServices();
    public static final UpdateQuestionServices u = new UpdateQuestionServices();
}
