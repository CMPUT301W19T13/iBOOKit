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

        r.setClassics(1.0);

        assertEquals(1.0 , r.getClassics());

        r.setSciFi(1.0);
        assertEquals(1.0, r.getSciFi());

        r.setHorror(1.0);
        assertEquals(1.0, r.getHorror());

        r.setThriller(1.0);
        assertEquals(1.0, r.getThriller());

        r.setSports(1.0);
        assertEquals(1.0, r.getSports());

        r.setComics(1.0);
        assertEquals(1.0, r.getComics());

        r.setRomance(1.0);
        assertEquals(1.0, r.getRomance());

        r.setBuisness(1.0);
        assertEquals(1.0, r.getBuisness());

        r.setOther(1.0);
        assertEquals(1.0, r.getOther());


    }



}



