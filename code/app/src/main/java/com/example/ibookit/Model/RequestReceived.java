package com.example.ibookit.Model;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.ibookit.Functionality.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestReceived {
    private static final String TAG = "RequestReceived";
    private ArrayList<Request> requestReceived = new ArrayList<>();
    private DatabaseReference mDatabase;
    private String username;



    public RequestReceived(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("RequestReceived");
    }

    public void RetriveRequest(final ArrayAdapter<Request> adapter) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requestReceived.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Request request = d.getValue(Request.class);
                    requestReceived.add(request);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

    }
}
