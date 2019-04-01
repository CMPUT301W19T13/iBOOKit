/**
 * Class name: NotificationHandler
 *
 * version 1.1
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Functionality;

import com.example.ibookit.Model.MessageIBOOKit;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author zijun wu
 *
 * @version 1.1
 */
public class NotificationHandler {
    private DatabaseReference mDatabase;
    private String sender;
    private String receiver;

    /**
     * Constructor: set database reference
     */
    public NotificationHandler(String sender, String receiver){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(receiver).child("send");
        this.sender = sender;
        this.receiver = receiver;
    }

    public NotificationHandler(){}

    /**
     * sends new message
     * @param title
     * @param content
     */
    public void sendNewMessage(String title, String content) {
        MessageIBOOKit message = new MessageIBOOKit(title, content);
        String mid = createMessageKey();
        message.setMid(mid);
        mDatabase.child(mid).setValue(message);
    }

    /**
     * create a key for message
     */
    private String createMessageKey() {
        return mDatabase.child("send").push().getKey();
    }
}
