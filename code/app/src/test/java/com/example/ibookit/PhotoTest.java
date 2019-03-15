/**
 * Class name: PhotoTest
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit;
import com.example.ibookit.Model.Photo;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Joe Xu
 *
 * @version 1.0
 */
public class PhotoTest {

/*private String path;
    private Integer height;
    private Integer weight;*/


   Photo picture = new Photo();

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