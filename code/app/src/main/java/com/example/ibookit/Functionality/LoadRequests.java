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
    public final ArrayList<Request> Request_list = new ArrayList<Request>();


    public void LoadRequests(){

    }

    public ArrayList<Request> return_request() {



        CurrentUser = "masiwei";

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // get all data from firebase

        ValueEventListener requestListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                System.out.println(dataSnapshot);

                RequestPerBook = (Map<String,Request>) dataSnapshot.getValue();
                System.out.println("************************************1");



                for (String key: RequestPerBook.keySet()){
                    Request_list.add( RequestPerBook.get(key));

                }

                System.out.println(Request_list);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        DatabaseReference allRequests = mDatabase.child("users").child("masiwei").child("requestReceived");
        mDatabase.addValueEventListener(requestListener);




        //
        //if (semaphore == 1){
        //    semaphore += 1;

       //     LoadRequests load = new LoadRequests("somename");
        //    ArrayList<Request> Rreceived = load.getlist();
        //    System.out.println(Rreceived);
       //     semaphore = 1;
        //}







        return Request_list;
    }



   // public ArrayList<ArrayList<String>> loadRequests (){


    //}




}
