package com.example.ibookit.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerShelf {

    private static final String TAG = "OwnerShelf";
    private DatabaseReference mDatabase;
    private ArrayList<Book> myBooks;
    private String username;

    // init FireBase
    private void init() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("ownerShelf");
    }


    public ArrayList<Book> All_books(){
        //returns all books that you own
        init();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Book book = d.getValue(Book.class);
                    myBooks.add(book);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

        return myBooks;
    }


    public void add_book(Book aBook){
        init();
        String key = createBookKey();
        mDatabase.child(key).setValue(aBook);

        // Add this book on child books
        FirebaseDatabase.getInstance().getReference().child("books").child(key).setValue(aBook);

    }

    public void remove_book(Book dBook){
        myBooks.remove(dBook);
    }


    private String createBookKey() {
        return mDatabase.push().getKey();
    }




}
