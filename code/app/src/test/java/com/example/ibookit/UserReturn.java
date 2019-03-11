/**
 * Class name: UserReturn
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit;

import com.example.ibookit.Model.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Joe Xu
 *
 * @version 1.0
 */

public class UserReturn {


    /*

    private String id;
    private String imageURL;*/

    User user = new User();



    @Test
    public void return_user(){

        user.setEmail("testemail");
        assertEquals("testemail",user.getEmail());

        user.setId("testid");
        assertEquals(user.getId(), "testid");

        user.setImageURL("imagetest");
        assertEquals(user.getImageURL(),"imagetest");

        user.setUsername("test");
        assertEquals("test", user.getUsername());

        user.setPhoneNumber("test");
        assertEquals("test", user.getPhoneNumber());


    }









}