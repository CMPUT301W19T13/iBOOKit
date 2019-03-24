/**
 * Class name: SearchForUser
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Mockobject;

import android.support.annotation.NonNull;

import com.example.ibookit.Database.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MockSearchUser {
    private ArrayList<User> mResult;

    public MockSearchUser(){}

    public ArrayList<User> searchByKeyword(final String mKeyword, final ArrayList<User> result) {

        DatabaseReference mData;
        mData = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef;
        userRef = mData.child("users");



        final ArrayList<User> testList = new ArrayList<User>();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    result.clear();

                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        if (d.getKey().toLowerCase().contains
                                (mKeyword.replaceAll("\\s+","").toLowerCase())){
                            User temp = d.getValue(User.class);
                            result.add(temp);
                            setResult(result);
                            testList.add(temp);

                        }
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        return testList;

    }

    /**
     * Get search result
     * @return
     */
    public ArrayList<User> getResult() {
        return mResult;
    }
    public void setResult(ArrayList<User> result) {
        this.mResult = result;
    }

}
