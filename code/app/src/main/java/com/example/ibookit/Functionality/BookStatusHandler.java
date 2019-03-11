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

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class BookStatusHandler {

    private static final String TAG = "BookStatusHandler";

    /**
     * Constructor
     */
    public BookStatusHandler(){

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
