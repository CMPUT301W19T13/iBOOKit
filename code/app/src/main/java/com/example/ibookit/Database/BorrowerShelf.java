/**
 * Class name: BorrowerShelf
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Database;

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

/**
 * @author zijun wu
 *
 * @version 1.0
 */
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

    /**
     * Return All book on the shelf
     *
     * @return ArrayList<Book>
     */
    @Override
    public ArrayList<Book> All_books() {
        return borrowBooks;
    }

    /**
     * Sync Borrowed bookshelf with the FireBase
     *
     * @param books
     * @param adapter
     * @param status
     */
    @Override
    public void SyncBookShelf(final ArrayList<Book> books, final ArrayAdapter<Book> adapter, final Integer status) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Book book = d.getValue(Book.class);
                    if (book.getStatus() == 3) {
                        books.add(book);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "onDataChange: This book is not supposed to show here: " + book.getId());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    /**
     * Borrower add a book to the bookshelf on FireBase
     *
     * @param book
     */
    @Override
    public void add_book(Book book) {

        //set current borrower to login user
        book.setCurrentBorrower(username);
        // set book status be borrowed
        book.setStatus(3);
        // ser book transit status to "owner to borrower"
        book.setTransitStatus(0);

        // put this book to login user's borrower shelf
        mDatabase.child(book.getId()).setValue(book);

        // update book info in books
        FirebaseDatabase.getInstance().getReference().child("books").child(book.getId()).setValue(book);

        // update book info in owner's shelf
        FirebaseDatabase.getInstance().getReference().child("users").child(book.getOwner()).child("ownerShelf").child(book.getId()).setValue(book);

    }

    /**
     * Borrower delete a book on FireBase
     *
     * @param book
     */
    @Override
    public void remove_book(Book book) {

        // set borrower to borrower returning
        String borrowerReturning = book.getCurrentBorrower() + " returning";
        book.setCurrentBorrower(borrowerReturning);
        // set book status available
        book.setStatus(0);

        // ser book transit status to "borrower to owner"
        book.setTransitStatus(2);

        // remove this book from borrower shelf
        mDatabase.child(book.getId()).removeValue();

        // update book info in books
        FirebaseDatabase.getInstance().getReference().child("books").child(book.getId()).setValue(book);

        // update book info in owner's shelf
        FirebaseDatabase.getInstance().getReference().child("users").child(book.getOwner()).child("ownerShelf").child(book.getId()).setValue(book);

    }

}
