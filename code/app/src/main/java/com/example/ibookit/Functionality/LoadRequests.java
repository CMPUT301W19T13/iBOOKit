package com.example.ibookit.Functionality;

import android.support.annotation.NonNull;

import com.example.ibookit.Model.Request;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LoadRequests {


    private ArrayList<ArrayList<String>> Users;
    private DatabaseReference mDatabase;
    private String CurrentUser;
    private Map<String, Request> RequestPerBook;


    public LoadRequests(String user) {

        CurrentUser = "masiwei";

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // get all data from firebase


        ValueEventListener requestListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                System.out.println(dataSnapshot);

                RequestPerBook = (Map<String,Request>) dataSnapshot.getValue();
                System.out.println("************************************");

                ArrayList<Request> here = new ArrayList<Request>();

                for (String key: RequestPerBook.keySet()){
                    here.add( RequestPerBook.get(key));

                }

                System.out.println(here);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        DatabaseReference allRequests = mDatabase.child("users").child("masiwei").child("requestReceived");
        mDatabase.addValueEventListener(requestListener);

    }

   // public ArrayList<ArrayList<String>> loadRequests (){


    //}




}
