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

    private HashMap<String, Double> category = new HashMap<>();

    public Recommendation(String username){
        category.put("Classics", 100.00);
        category.put("SciFi", 100.00);
        category.put("Horror", 100.00);
        category.put("Thriller", 100.00);
        category.put("Sports", 100.00);
        category.put("Comics", 100.00);
        category.put("Romance", 100.00);
        category.put("Business", 100.00);
        category.put("Others", 100.00);

    }

    public Recommendation(){}

    /**
     * Getter and Setter
     */

    public HashMap<String, Double> getCategory() {
        return category;
    }

    public void setCategory(HashMap<String, Double> category) {
        this.category = category;
    }
}
