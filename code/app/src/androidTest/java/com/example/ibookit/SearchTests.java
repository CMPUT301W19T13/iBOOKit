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

import com.example.ibookit.Database.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author zisen zhou
 *
 * @version 1.0
 */

@RunWith(AndroidJUnit4.class)
public class SearchTests {
    private Context instrumentationCtx;
    private Set<String> testResult = new HashSet<>();
    private Set<String> expectedResult = new HashSet<>();

    @Test
    public void SearchTest() {
        // find context and initialize firebase app
        instrumentationCtx = InstrumentationRegistry.getContext();
        FirebaseApp.initializeApp(instrumentationCtx);
        /*
         * user search test
         */

        //initialize attributes that are going to be used for comparison
        expectedResult.add("zijun");
        expectedResult.add("zijunwu");

        //perform the search and compare, the method used is basically identical to the method
        //used in our actual app
        userSearchByKeyword("zijun");

        //clean up
        expectedResult.clear();
        testResult.clear();

        /*
         * book keyword search test
         */
        //initialize Attributes that are going to be used for comparison
        expectedResult.add("Top");
        expectedResult.add("Topp");
        expectedResult.add("Trump2020_lee1");

        //initialize search keyword list
        String[] keywords = {"top","trump"};

        //perform actual search and compare solution
        bookSearchByKeyword(keywords);






    }
    public void userSearchByKeyword(final String mKeyword) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        if (d.getKey().toLowerCase().contains
                                (mKeyword.replaceAll("\\s+","").toLowerCase())){
                            User temp = d.getValue(User.class);
                            testResult.add(temp.getUsername());
                        }
                    }
                }
                assertEquals(expectedResult, testResult);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void bookSearchByKeyword(final String[] mListKeyword)  {
        final Set<String> nonDupID = new HashSet<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listBook = bookRef.orderByChild("status").equalTo(0);

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

                }
                testResult = nonDupID;
                assertEquals(expectedResult, testResult);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

