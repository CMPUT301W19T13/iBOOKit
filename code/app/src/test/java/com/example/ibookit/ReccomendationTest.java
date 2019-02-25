package com.example.ibookit;

import com.example.ibookit.Functionality.Recommendation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
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



