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
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.ibookit.Mockobject.TestObj;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;



@RunWith(AndroidJUnit4.class)
public class FirebaseTest {

    private int semaphore = 1;
    private int guard = 1;
    private Context instrumentationCtx;
    private Set<String> testResult = new HashSet<>();
    private Set<String> expectedResult = new HashSet<>();

    @Test
    public void test(){

        instrumentationCtx = InstrumentationRegistry.getContext();
        FirebaseApp.initializeApp(instrumentationCtx);

        DatabaseReference mref;
        mref = FirebaseDatabase.getInstance().getReference();

        DatabaseReference compare = mref.child("users");

        ArrayList<String> longlist = new ArrayList<String>();


//        if (longlist.isEmpty()){
//            System.out.println("Notexist");
//        }
//
//        longlist.add("1");
//
//        if (longlist.isEmpty()){
//            System.out.println("Notexist");
//        }
//        else{
//            System.out.println("exist");
//        }


        while (longlist.isEmpty()) {

            if (++guard == 2) {
                longlist = searchDatabase(compare);

            }
            System.out.println("Notexist");

        }






    }




//    mDatabase.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            last.clear();
//            bookList.clear();
//            adapter.notifyDataSetChanged();
//            for (DataSnapshot d: dataSnapshot.getChildren()) {
//
//                Request request = d.getValue(Request.class);


    public ArrayList<String> searchDatabase(DatabaseReference compare){
        final String TAG = "ffff";
        System.out.println("dsssss");
        final ArrayList<String> list = new ArrayList<String>();

        compare.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                System.out.println("Notexist");

                for (DataSnapshot d : dataSnapshot.getChildren()){

                    System.out.println("sssss");
                    Log.d(TAG, d.getValue().toString());


                }
                list.add("1");

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                System.out.println("Notexist");
            }
        });

        return list;
    }



}