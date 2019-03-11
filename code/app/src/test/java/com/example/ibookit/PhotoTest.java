package com.example.ibookit;
import com.example.ibookit.Model.photo;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Joe Xu
 *
 * @versi
 */
public class PhotoTest {

/*private String path;
    private Integer height;
    private Integer weight;*/


   photo picture = new photo();

    @Test
    public void Phototest(){

        Integer number = 55;

        picture.setHeight(number);
        assertEquals(picture.getHeight(), number);

        Integer secondnumber = 77;

        picture.setWeight(secondnumber);
        assertEquals(picture.getWeight(),secondnumber);

        String path = "doc/code/main/src/javapic/jpeg.photo34";

        picture.setPath(path);
        assertEquals(picture.getPath(),path);







}}