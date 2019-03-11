package com.example.ibookit;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
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