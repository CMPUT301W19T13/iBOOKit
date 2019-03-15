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

import android.support.annotation.NonNull;

import com.example.ibookit.Model.MessageIBOOKit;
import com.example.ibookit.Model.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * @author zijun wu
 *
 * @version 1.1
 */
public class CreateRequestHandler {

    private String sender;
    private DatabaseReference mDatabase;
    private String receiver;

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
    public Boolean SendRequestToOwner(Request request){
        String key = createRequestKey();

        request.setRid(key);
        request.setSender(sender);
        receiver = request.getReceiver();

        if (sender.equals(receiver)) {
            return false;
        }

        mDatabase.child("requests").child(key).setValue(request);

        DatabaseReference receiverRequestReceived = mDatabase.child("users").child(request.getReceiver()).child("requestReceived");

        DatabaseReference senderRequestSent = mDatabase.child("users").child(sender).child("requestSent");

        receiverRequestReceived.child(key).setValue(request);

        senderRequestSent.child(key).setValue(request);

        return true;

    }


    /**
     * Set Notification to book's owner
     * @param title
     */
    public void setNotificationToOwner(String title){
        // Set Message to receiver
        MessageIBOOKit message = new MessageIBOOKit("New request", title, "1");
        String mid = createMessageKey();
        message.setMid(mid);
        mDatabase.child("users").child(receiver).child("send").child(mid).setValue(message);
    }

    /**
     * Create key for request
     * @return
     */
    private String createRequestKey () {
        return mDatabase.child("requests").push().getKey();
    }

    private String createMessageKey() {
        return mDatabase.child("send").push().getKey();
    }

}
