/**
 * Class name: CreateRequestHandler
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Functionality;

import com.example.ibookit.Model.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class CreateRequestHandler {

    private String sender;
    private DatabaseReference mDatabase;

    /**
     * Constructor: set login user
     */
    public CreateRequestHandler () {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        this.sender = user.getDisplayName();
    }

    /**
     * Send request to owner
     * @param request
     */
    public void SendRequestToOwner(Request request){
        String key = createKey();

        request.setRid(key);
        request.setSender(sender);

        mDatabase.child("requests").child(key).setValue(request);

        DatabaseReference receiverRequestReceived = mDatabase.child("users").child(request.getReceiver()).child("requestReceived");

        DatabaseReference senderRequestSent = mDatabase.child("users").child(sender).child("requestSent");

        receiverRequestReceived.child(key).setValue(request);

        senderRequestSent.child(key).setValue(request);

    }

    /**
     * Create key for request
     * @return
     */
    private String createKey () {
        return mDatabase.child("requests").push().getKey();
    }

}
