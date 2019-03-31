/**
 * Class name: RequestStatic
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit;

import android.location.Location;

import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestR;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Joe Xu
 *
 * @version 1.0
 */
public class RequestStatic {


    Request request = new Request();
    ArrayList<Request> requestlist = new ArrayList<Request>();

    RequestR Received = new RequestR("ownername", requestlist);

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




    }

    @Test
    public void ReceiveRequest(){
        Received.setBookTitle("test");
        assertEquals(Received.getBookTitle(), "test");

        ArrayList<Request> ArrayRequest = new ArrayList<Request>();

        Received.setRequestSent(ArrayRequest);
        assertEquals(Received.getRequestSent(), ArrayRequest);

        assertEquals(Received.getUsername(),"ownername");



    }






}