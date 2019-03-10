package com.example.ibookit;

import com.example.ibookit.Functionality.RequestStatusHandler;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestSent;
import com.example.ibookit.Model.User;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RequestFunc {

    RequestStatusHandler ReqStat = new RequestStatusHandler();
    Request request = new Request();
    ArrayList<Request> requestlist = new ArrayList<Request>();

    @Test
    public void requestTest(){
        assertEquals(ReqStat.StatusIntegerToString(2), "Declined");
        assertEquals(ReqStat.StatusIntegerToString(0), "Pending");
        assertEquals(ReqStat.StatusIntegerToString(1), "Accepted");
        assertEquals(ReqStat.StatusIntegerToString(4), "StatusString: Out of range");
        RequestSent requestsender = new RequestSent(requestlist);
        assertEquals(requestsender.getRequestSent(), requestlist);


    }









}