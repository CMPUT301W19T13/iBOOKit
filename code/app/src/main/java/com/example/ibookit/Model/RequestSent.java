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

public class RequestSent {
    private static final String TAG = "RequestSent";
    private ArrayList<Request> requestSent = new ArrayList<>();
    private DatabaseReference mDatabase;
    private String username;

    public ArrayList<Request> getRequestSent() {
        return requestSent;
    }

    public RequestSent(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("RequestSent");
    }

    public void RetriveRequest(final ArrayAdapter<Request> adapter) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requestSent.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Request request = d.getValue(Request.class);
                    requestSent.add(request);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

    }

    //Query query = FirebaseDatabase.getInstance().getReference().child("requests").orderByChild("sender").equalTo(username);

}
