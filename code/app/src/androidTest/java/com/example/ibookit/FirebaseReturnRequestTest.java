/**
 * Class name: SearchTests
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Request;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Joe Xu
 *
 * @version 1.0
 */
@RunWith(AndroidJUnit4.class)
public class FirebaseReturnRequestTest {

    private int semaphore = 1;
    private int guard = 1;
    private Context instrumentationCtx;
    public ArrayList<Request> result = new ArrayList<Request>();
    public int totalSize;

    @Test
    public void test() {

        instrumentationCtx = InstrumentationRegistry.getContext();
        FirebaseApp.initializeApp(instrumentationCtx);

        DatabaseReference mref;
        mref = FirebaseDatabase.getInstance().getReference();

        String UserNameString = "TestUser";
        Request AtLeastOne = new Request();
        AtLeastOne.setBookId("r245787jtlq");
        AtLeastOne.setReceiver("Reciever");
        AtLeastOne.setSender("Sender");
        AtLeastOne.setRid("Rid");

        String UUid = UUID.randomUUID().toString();

        mref = mref.child("users").child(UserNameString).child("requestSent");
        mref.child(UUid).setValue(AtLeastOne);

        //semaphore this critial area and serch the database
        // semaphore used for syncronization.
        while (semaphore == 1) {
            if (++guard == 2) {
                MockRequestReturnSearch(mref, UserNameString);

            }

        }
        System.out.println("EEEEEEE");
        System.out.println(result);

        int exists = 0;
        for (Request i : result){
                if (i.getBookId().equals("r245787jtlq")){
                    exists = 1;
                }
        }

        assertEquals(exists,1);

    }

    ///  Same algorithm used for getting User objects

    public void MockRequestReturnSearch(DatabaseReference reference, String user) {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    //Request request = new Request(d.getValue(Request.class).getBook());
                    //request.setReceiver(d.getValue(Request.class).getReceiver());
                    Request request = d.getValue(Request.class);

                    //System.out.println("requestsent " + request.getRid());
                    result.add(request);}
                    semaphore = 2;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                semaphore = 2;
            }
        });


    }
}