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

public class RequestReceived {
    private static final String TAG = "RequestReceived";
    private ArrayList<Request> requestReceived = new ArrayList<>();
    private DatabaseReference mDatabase;
    private String username;
    private String last;




    public RequestReceived(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("requestReceived");
    }

    public void RetriveBook(final ArrayList<String> bookList,final ArrayAdapter<Request> adapter) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bookList.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {

                    Request request = d.getValue(Request.class);

                    if (request.getBookId() != last){
                        bookList.add(request.getBookId());
                        adapter.notifyDataSetChanged();
                    }

                    last = request.getBookId();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

    }

    public void RequestInBook(final ArrayList<User> users,final ArrayAdapter<User> adapter){

    }



}
//public class LoadRequests {
//
//
//    private ArrayList<ArrayList<String>> Users;
//    private DatabaseReference mDatabase;
//    private String CurrentUser;
//    private Map<String, Request> RequestPerBook;
//
//
//    public LoadRequests(String user) {
//
//        CurrentUser = "masiwei";
//
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        // get all data from firebase
//
//
//        ValueEventListener requestListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                System.out.println(dataSnapshot);
//
//                RequestPerBook = (Map<String,Request>) dataSnapshot.getValue();
//                System.out.println("************************************");
//
//                ArrayList<Request> here = new ArrayList<Request>();
//
//                for (String key: RequestPerBook.keySet()){
//                    here.add( RequestPerBook.get(key));
//
//                }
//
//                System.out.println(here);
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//
//        DatabaseReference allRequests = mDatabase.child("users").child("masiwei").child("requestReceived");
//        mDatabase.addValueEventListener(requestListener);
//
//    }
