package com.example.ibookit;

import android.location.Location;

import com.example.ibookit.Model.Request;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Requests {


    /*private int isAccept;
    private String sender;
    private String receiver;
    private String bookId;
    private Location geolocation;*/

    Request request = new Request();


    Location location = new Location("test");

    // send request to user 1 for a specific book

    @Test
    public void SendRequest(){
        request.setIsAccept(1);
        assertEquals(request.getIsAccept(),1);

        request.setSender("test");
        assertEquals(request.getSender(), "test");

        request.setReceiver("test");
        assertEquals(request.getReceiver(),"test");

        request.setBookId("test");
        assertEquals(request.getBookId(),"test");

        request.setGeolocation(location);
        assertEquals(location,request.getGeolocation());
        

    }





}