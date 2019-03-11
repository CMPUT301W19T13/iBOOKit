package com.example.ibookit;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.ibookit.Functionality.RequestStatusHandler;
import com.example.ibookit.ListAdapter.RequestListAdapter;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.Model.RequestSent;
import com.example.ibookit.Model.User;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Joe Xu
 *
 * @versi
 */
public class RequestFunc {

    RequestStatusHandler ReqStat = new RequestStatusHandler();
    Request request = new Request();
    ArrayList<Request> requestlist = new ArrayList<Request>();



    //Context context, int resource, ArrayList<Request> objects)
    @Test
    public void requestTest(){
        requestlist.add(request);

        RequestReceived testRequestRecieve= new RequestReceived("testname", requestlist);



        RequestSent requestsender = new RequestSent(requestlist);


        assertNotEquals(requestsender, null);
        assertEquals(ReqStat.StatusIntegerToString(2), "Declined");
        assertEquals(ReqStat.StatusIntegerToString(0), "Pending");
        assertEquals(ReqStat.StatusIntegerToString(1), "Accepted");
        assertEquals(ReqStat.StatusIntegerToString(4), "StatusString: Out of range");
        assertEquals(requestsender.sentRequests(), requestlist);

        ArrayList<Request> testlist = testRequestRecieve.requestReceiveList();
        Request testquest = testlist.get(0);


        assertEquals(testquest, request);




    }









}