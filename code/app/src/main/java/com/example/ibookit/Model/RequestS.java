/**
 *
 * Class name: RequestS
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 *
 */
package com.example.ibookit.Model;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * @author Joe, Jiazhen Li
 *
 * @version 1.0
 */

public class RequestS {
    private static final String TAG = "RequestS";
    private ArrayList<Request> requestSent = new ArrayList<>();
    private DatabaseReference mDatabase;
    private String username;

    /**
     * Constructor
     */
    public RequestS(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(username).child("requestSent");
    }

    /**
     * Get all the requests user has sent
     */
    public RequestS(ArrayList<Request> allRequest){
        this.requestSent = allRequest;
    }

    /**
     * Set and sync requestSent to ListView
     * @param requestSent2
     * @param adapter
     */
    public void RetriveRequest(final ArrayList<Request> requestSent2, final ArrayAdapter<Request> adapter) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requestSent.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                   //Request request = new Request(d.getValue(Request.class).getBook());
                   //request.setReceiver(d.getValue(Request.class).getReceiver());
                    Request request = d.getValue(Request.class);

                    //System.out.println("requestsent " + request.getRid());
                    requestSent2.add(request);
                    adapter.notifyDataSetChanged();
                //Show(dataSnapshot);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    /**
     * methods below are for testing
     * @return
     */
    public ArrayList<Request> getRequestSent() {
        return requestSent;
    }

    public ArrayList<Request> sentRequests(){
        return requestSent;
    }

    public void setRequestSent(ArrayList<Request> requestSent) {
        this.requestSent = requestSent;
    }


}
