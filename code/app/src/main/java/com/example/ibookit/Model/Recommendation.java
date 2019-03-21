/**
 * Class name: Recommendation
 *
 * version 1.1
 *
 * Date: March 20, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Model;

import java.util.HashMap;

/**
 * @author zijun wu
 *
 * @version 1.1
 */
public class Recommendation {

    private String username;
    private HashMap<String, Double> categoryPoint = new HashMap<>();
    private HashMap<String, Integer> categoryCount = new HashMap<>();

    public Recommendation(String username){
        this.username = username;
        categoryPoint.put("Classics", 100.00);
        categoryPoint.put("SciFi", 100.00);
        categoryPoint.put("Horror", 100.00);
        categoryPoint.put("Thriller", 100.00);
        categoryPoint.put("Sports", 100.00);
        categoryPoint.put("Comics", 100.00);
        categoryPoint.put("Romance", 100.00);
        categoryPoint.put("Business", 100.00);
        categoryPoint.put("Others", 100.00);

        categoryCount.put("Classics", 0);
        categoryCount.put("SciFi", 0);
        categoryCount.put("Horror", 0);
        categoryCount.put("Thriller", 0);
        categoryCount.put("Sports", 0);
        categoryCount.put("Comics", 0);
        categoryCount.put("Romance", 0);
        categoryCount.put("Business", 0);
        categoryCount.put("Others", 0);
    }

    public Recommendation(){}


    /**
     * Getter and Setter
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<String, Double> getCategoryPoint() {
        return categoryPoint;
    }

    public void setCategoryPoint(HashMap<String, Double> categoryPoint) {
        this.categoryPoint = categoryPoint;
    }

    public HashMap<String, Integer> getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(HashMap<String, Integer> categoryCount) {
        this.categoryCount = categoryCount;
    }
}
