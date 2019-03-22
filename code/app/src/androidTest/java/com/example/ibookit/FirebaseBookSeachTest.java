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
import java.util.Random;
import java.util.UUID;


@RunWith(AndroidJUnit4.class)
public class FirebaseBookSeachTest {

    private int semaphore = 1;
    private int guard = 1;
    private Context instrumentationCtx;
    public ArrayList<Book> result = new ArrayList<Book>();
    public int totalSize;


    @Test
    public void test() {

        instrumentationCtx = InstrumentationRegistry.getContext();
        FirebaseApp.initializeApp(instrumentationCtx);

        DatabaseReference mref;
        mref = FirebaseDatabase.getInstance().getReference();

        ArrayList<String>  lookUpString = new ArrayList<String>();
        Book tester1 = new Book();
        Book tester2 = new Book();
        tester1.setTitle("testerbook");
        tester1.setAuthor("autherofbook1");
        tester1.setIsbn("21341234");
        tester1.setDescription("this is a evil evil book");

        tester2.setTitle("InSiGnIa");
        tester2.setAuthor("VladValentine");
        tester2.setIsbn("666999666999666");
        tester2.setDescription("DO NOT READ");


        mref = mref.child("books");


        //ALL INPUTS ARE CONVERTED TO LOWER IN APPLICATION

        lookUpString.add("TeStErBoOk".toLowerCase());
        lookUpString.add("666999666999666".toLowerCase());
        lookUpString.add("DO NOT READ".toLowerCase());
        lookUpString.add("insignia".toLowerCase());
        lookUpString.add("VladValentine".toLowerCase());
        lookUpString.add("21341234".toLowerCase());
        lookUpString.add("autherofbook1".toLowerCase());

        //semaphore this critial area and serch the database
        // semaphore used for syncronization.
        while (semaphore == 1) {
            if (++guard == 2) {
                MockBookSearch(mref, lookUpString);
            }

        }



        // ENSURE DUPLICATE BOOKS ARE NOT STORED MORE THAN ONCE
        assertEquals(totalSize,result.size());

        // ENSURE THAT IF A BOOK EXISTS IN RESULTS IT WAS IN OUR SEARCHED STRING
        String temp;
        int counter;
        int fail;

        for ( int num =0; num < result.size(); num++){
            fail = 1;

            for ( int num2 =0; num2 < lookUpString.size(); num2++) {


                if (result.get(num).getTitle().toLowerCase().equals(lookUpString.get(num2))) {
                    System.out.println("Reach");
                    fail = 0;
                    break;
                }
                else if(result.get(num).getAuthor().toLowerCase().equals(lookUpString.get(num2))) {
                    System.out.println("Reach");
                    fail = 0;
                    break;
                }
                else if(result.get(num).getDescription().toLowerCase().equals(lookUpString.get(num2))) {
                    System.out.println("Reach");
                    fail = 0;
                    break;
                }
                else if(result.get(num).getIsbn().toLowerCase().equals(lookUpString.get(num2))){
                    System.out.println("Reach");
                    fail = 0;
                    break;
                }
            }




            assertEquals(0,fail);

        }





    }


    ///  Same algorithm used for getting User objects

    public void MockBookSearch(DatabaseReference compare, final ArrayList<String> mListKeyword) {
        final Set<String> nonDupID = new HashSet<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listBook = bookRef.orderByChild("status").startAt(0).endAt(1);

        listBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (String mKeyword: mListKeyword ){


                        for (DataSnapshot d : dataSnapshot.getChildren()){


                            if (d.child("title").getValue().toString().toLowerCase().contains
                                    (mKeyword)){

                                nonDupID.add(d.getKey());

                            }else if (d.child("author").getValue().toString().toLowerCase().contains
                                    (mKeyword)){
                                nonDupID.add(d.getKey());
                            }else if (d.child("isbn").getValue().toString().toLowerCase().contains
                                    (mKeyword)){
                                nonDupID.add(d.getKey());
                            }
                            else if (d.child("description").getValue().toString().toLowerCase().contains
                                    (mKeyword)){
                                nonDupID.add(d.getKey());
                            }
                        }
                    }

                    //adapter.notifyDataSetChanged();
                    for (String id: nonDupID){
                        Book temp =  dataSnapshot.child(id).getValue(Book.class);
                        result.add(temp);

                    }
                    totalSize = nonDupID.size();
                    semaphore = 2;
                    //adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}