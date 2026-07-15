/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bts.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class FlyweightFactory {

    public static final Map<String, List> cacheData = new HashMap<>();

    public static <E> List<E> getData(QueryServicesBase srv, String key) throws SQLException {
        if (cacheData.containsKey(key) == false) {
            cacheData.put(key, srv.getT());
        }
        return cacheData.get(key);
    }
}
