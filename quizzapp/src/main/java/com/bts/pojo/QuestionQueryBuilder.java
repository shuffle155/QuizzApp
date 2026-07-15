/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.pojo;

import com.bts.utils.MyConnectionSingleton;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionQueryBuilder {

    private final StringBuilder query;
    private final StringBuilder where;
    private String orderBy = "id DESC";
    private final List<Object> params;

    public QuestionQueryBuilder() {
        this.query = new StringBuilder("SELECT * FROM question WHERE 1=1 %s ORDER BY %s");
        this.where = new StringBuilder();
        this.params = new ArrayList<>();
    }

    public QuestionQueryBuilder withKeyWords(String kw) {

        if (kw != null && !kw.isEmpty()) {
            this.where.append(" AND content LIKE CONCAT('%', ?, '%')");
            this.params.add(kw);
        }

        return this;
    }

    public QuestionQueryBuilder withCategory(Category c) {

        if (c != null) {
            this.where.append(" AND category_id = ?");
            params.add(c.getId());
        }

        return this;
    }

    public QuestionQueryBuilder withLevel(Level l) {

        if (l != null) {
            this.where.append(" AND level_id = ?");
            params.add(l.getId());
        }

        return this;
    }

    public QuestionQueryBuilder withLevel(int levelId) {

        if (levelId > 0) {
            this.where.append(" AND level_id = ?");
            params.add(levelId);
        }

        return this;
    }

    public QuestionQueryBuilder setLimit(int limit) {

        if (!this.query.toString().toLowerCase().contains("limit")) {
            this.query.append(" LIMIT ? ");
            this.params.add(limit);
        }

        return this;
    }

    public QuestionQueryBuilder setLimit(String limit) {
        this.setLimit(Integer.parseInt(limit));
        return this;
    }

    public PreparedStatement build() throws SQLException {
        String sql = String.format(this.query.toString(), this.where.toString(), this.orderBy);
        PreparedStatement stm = MyConnectionSingleton.getInstance().connect().prepareCall(sql);

        for (int i = 0; i < this.params.size(); i++) {
            stm.setObject(i + 1, this.params.get(i));
        }

        return stm;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public QuestionQueryBuilder setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }
}
