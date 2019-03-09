package com.example.ibookit.Model;

import android.support.annotation.NonNull;
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
    private ArrayList<Request> requestSent = new ArrayList<>();
    private DatabaseReference mDatabase;
    private String username;
    private ArrayList<String> last = new ArrayList<>();
    private String bookTitle;
    private Request request1;



    public RequestReceived(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("requestReceived");
    }

    public void RetriveBook(final ArrayList<String> bookList,final ArrayAdapter<String> adapter) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                last.clear();
                bookList.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {

                    Request request = d.getValue(Request.class);

                    if (!last.contains(request.getBookId())){
                        final DatabaseReference bDatabase = FirebaseDatabase.getInstance().getReference().child("books").child(request.getBookId());
                        bDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Book book = dataSnapshot.getValue(Book.class);
                                bookList.add(book.getTitle());
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        last.add(request.getBookId());
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

    }

    public void RequestInBook(final ArrayList<String> users,final ArrayAdapter<String> adapter,final String bookname){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    request1 = d.getValue(Request.class);
                    final DatabaseReference cDatabase = FirebaseDatabase.getInstance().getReference().child("books").child(request1.getBookId());
                    cDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Book book1 = dataSnapshot.getValue(Book.class);
                                bookTitle = book1.getTitle();
                                if (bookTitle.equals(bookname)) {
                                    users.add(request1.getSender());
                                    adapter.notifyDataSetChanged();
                                }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
//                    if (bookTitle.equals(bookname)) {
//                        users.add(request.getSender());
//                        adapter.notifyDataSetChanged();
//                    }



                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }



}
