/**
 * Class name: ReccomendationTest
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit;

import com.example.ibookit.Model.Recommendation;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
/**
 * @author Joe Xu
 *
 * @version 1.0
 */
public class ReccomendationTest {
    private double Classics;
    private double SciFi;
    private double Horror;
    private double Thriller;
    private double Sports;
    private double Comics;
    private double Romance;
    private double Buisness;
    private double other;

    Recommendation r = new Recommendation();

    @Test
    public void testGetAndSet(){


        HashMap<String, Integer> categoryCount = new HashMap<>();


        categoryCount.put("Horror", 1);
        r.setCategoryCount(categoryCount);
        int place = r.getCategoryCount().get("Horror");
        assertEquals(1 , place);


        categoryCount.put("Classic", 1);
        r.setCategoryCount(categoryCount);
        place = r.getCategoryCount().get("Classic");
        assertEquals(1 , place);


        categoryCount.put("History", 1);
        r.setCategoryCount(categoryCount);
        place = r.getCategoryCount().get("History");
        assertEquals(1 , place);



        categoryCount.put("Sci-fi", 1);
        r.setCategoryCount(categoryCount);
        place = r.getCategoryCount().get("Sci-fi");
        assertEquals(1 , place);

        categoryCount.put("Thriller", 1);
        r.setCategoryCount(categoryCount);
        place = r.getCategoryCount().get("Thriller");
        assertEquals(1 , place);

        categoryCount.put("Sport", 1);
        r.setCategoryCount(categoryCount);
        place = r.getCategoryCount().get("Sport");
        assertEquals(1 , place);









    }



}



