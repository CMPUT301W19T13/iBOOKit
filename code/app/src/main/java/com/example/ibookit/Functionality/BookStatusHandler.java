package com.example.ibookit.Functionality;

import android.util.Log;

import com.example.ibookit.Model.Book;

public class BookStatusHandler {

    private static final String TAG = "BookStatusHandler";

    public BookStatusHandler(){

    }

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
}
