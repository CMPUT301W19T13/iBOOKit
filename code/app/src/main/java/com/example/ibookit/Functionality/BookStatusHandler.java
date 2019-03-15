/**
 * Class name: BookStatusHandler
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Functionality;

import android.util.Log;

import com.example.ibookit.Model.Book;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author zijun wu
 *
 * @version 1.1
 */
public class BookStatusHandler {

    private static final String TAG = "BookStatusHandler";

    /**
     * Constructor
     */
    public BookStatusHandler(){

    }

    /**
     * Set book status on both books and owner shelf
     *
     * @param book
     * @param status
     * @return
     */
    public void setBookStatusFirebase(Book book, Integer status){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("books").child(book.getId());
        String owner = book.getOwner();
        DatabaseReference ownerShelfDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(owner).child("ownerShelf").child(book.getId());
        mDatabase.child("status").setValue(status);
        ownerShelfDatabase.child("status").setValue(status);
    }


    /**
     * Get a book status to string
     *
     * @param book
     * @return
     */
    public String StatusString (Book book) {
        Integer status = book.getStatus();
        if (status == 0) {
            return "Available";
        } else if (status == 1) {
            return "Requested";
        } else if (status == 2) {
            return "Accepted";
        } else if (status == 3){
            return "Borrowed";
        } else {
            Log.d(TAG, "StatusString: Out of range");
            return "";
        }
    }

    /**
     * transfer status int to string
     *
     * @param status
     * @return
     */
    public String StatusIntegerToString (Integer status) {
        if (status == 0) {
            return "Available";
        } else if (status == 1) {
            return "Requested";
        } else if (status == 2) {
            return "Accepted";
        } else if (status == 3){
            return "Borrowed";
        } else {
            Log.d(TAG, "StatusString: Out of range");
            return "";
        }
    }
}
