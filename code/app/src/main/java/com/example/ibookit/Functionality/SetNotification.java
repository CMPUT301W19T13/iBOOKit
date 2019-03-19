package com.example.ibookit.Functionality;

import com.example.ibookit.Model.MessageIBOOKit;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNotification {
    private DatabaseReference mDatabase;
    private String sender;
    private String receiver;

    public SetNotification(String sender, String receiver){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(receiver).child("send");
        this.sender = sender;
        this.receiver = receiver;
    }

    public void sendNewMessage(String title, String content) {
        MessageIBOOKit message = new MessageIBOOKit(title, content);
        String mid = createMessageKey();
        message.setMid(mid);
        mDatabase.child(mid).setValue(message);
    }

    private String createMessageKey() {
        return mDatabase.child("send").push().getKey();
    }
}
