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

public class BorrowerShelf implements BookShelf{

    private static final String TAG = "BorrowerShelf";
    private ArrayList<Book> borrowBooks = new ArrayList<>();
    private String username;
    private DatabaseReference mDatabase;

    public BorrowerShelf() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("borrowerShelf");
    }


    @Override
    public ArrayList<Book> All_books() {
        return borrowBooks;
    }

    @Override
    public void SyncBookShelf(final ArrayList<Book> books, final ArrayAdapter<Book> adapter, final Integer status) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Book book = d.getValue(Book.class);
                    books.add(book);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    @Override
    public void add_book(Book book) {

        //set current borrower to login user
        book.setCurrentBorrower(username);
        // set book status be borrowed
        book.setStatus(3);

        // put this book to login user's borrower shelf
        mDatabase.child(book.getId()).setValue(book);

        // update book info in books
        FirebaseDatabase.getInstance().getReference().child("books").child(book.getId()).setValue(book);

        // update book info in owner's shelf
        FirebaseDatabase.getInstance().getReference().child("users").child(book.getOwner()).child("ownerShelf").child(book.getId()).setValue(book);

    }

    @Override
    public void remove_book(Book book) {

        // remove current borrower
        book.setCurrentBorrower("");
        // set book status available
        book.setStatus(0);

        // remove this book from borrower shelf
        mDatabase.child(book.getId()).removeValue();

        // update book info in books
        FirebaseDatabase.getInstance().getReference().child("books").child(book.getId()).setValue(book);

        // update book info in owner's shelf
        FirebaseDatabase.getInstance().getReference().child("users").child(book.getOwner()).child("ownerShelf").child(book.getId()).setValue(book);

    }
}
