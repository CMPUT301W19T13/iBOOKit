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
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.ibookit.Model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(AndroidJUnit4.class)
public class FirebaseUserSearchTest {

    private int semaphore = 1;
    private int guard = 1;
    private Context instrumentationCtx;
    private ArrayList<User> testResult = new ArrayList<User>();
    private Set<String> expectedResult = new HashSet<>();
    ArrayList<User> list = new ArrayList<User>();
    ArrayList<User> longlist = new ArrayList<User>();


    @Test
    public void test(){

        instrumentationCtx = InstrumentationRegistry.getContext();
        FirebaseApp.initializeApp(instrumentationCtx);

        DatabaseReference mref;
        mref = FirebaseDatabase.getInstance().getReference();

        User testuser = new User();
        User testuser2 = new User();
        User testuser3 = new User();
        User nameNotInKey = new User();

        testuser.setUsername("username");
        testuser.setId("123456");
        testuser.setEmail("index");

        testuser2.setUsername("username2456");
        testuser2.setId("123456");
        testuser2.setEmail("mail");

        testuser3.setUsername("BLOCKERusernameBLOCKER");
        nameNotInKey.setUsername("uDDDsername");

        DatabaseReference compare = mref.child("users");

        // TESTS IF OUR SEARCH FUNCTION CAN SEARCH NAMES EVEN IF THE BEGINNING AND END STINGS DO NOT MATCH THE STRINGS
        // THAT IS IN OUR SEARCH KEYWORD.
        // ALSO MAKES SURE OUR SEARCH DOES NOT RETURN VALUES THAT HAVE THE SAME STRING BUT IN WRONG ORDER.
        // THIS TEST CONTAINS THE SAME ALGORITHM AS THE ONE USED IN APPLICATION WITH THE ADAPTER TAKEN OUT

        compare.child("uDDDsername").setValue(nameNotInKey);
        compare.child("username2456").setValue(testuser2);
        compare.child("username").setValue(testuser);
        compare.child("BLOCKERusernameBLOCKER").setValue(testuser3);

        String lookupname = "username";

        //semaphore
        while (semaphore == 1) {

            if (++guard == 2) {
                MockUserSearch(compare, lookupname);
                System.out.println("Only1canbe");

            }


        }


        /// Test if all objects appended to the database and retracted using our algorithm actually contains
        /// the keyword given

        int fail;
        for (int num = 0; num < longlist.size(); num ++){

                System.out.println(longlist.get(num).getUsername());
                fail = 1;
                if(longlist.get(num).getUsername().contains(lookupname)){
                    fail = 0;
                }

                assertEquals(0,fail);

        }

    }


    ///  Same algorithm used for getting User objects

    public void MockUserSearch(DatabaseReference compare, final String mKeyword){
        final String TAG = "ffff";

        //ArrayList<User> list = new ArrayList<User>();

        compare.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){
                    //list.clear();
                    //adapter.notifyDataSetChanged();


                    for (DataSnapshot d : dataSnapshot.getChildren()) {


                        if (d.getKey().toLowerCase().contains
                                (mKeyword.replaceAll("\\s+","").toLowerCase())){


                            User temp = d.getValue(User.class);
                            longlist.add(temp);
                            setResult(testResult);

                           // adapter.notifyDataSetChanged();
                        }

                    }
                    semaphore = 2;
                }

            }
            @Override
            public void onCancelled( DatabaseError databaseError) {
                System.out.println("Notexist");
            }
        });

        System.out.println("TAG");


    }
    public void setResult(ArrayList<User> result) {
        this.testResult = result;
    }

}

