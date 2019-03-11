package com.example.ibookit;

import android.location.Location;

import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestReceived;
import com.google.firebase.database.DatabaseReference;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RequestStatic {




    Request request = new Request();
    ArrayList<Request> requestlist = new ArrayList<Request>();

    RequestReceived Received = new RequestReceived("ownername", requestlist);

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