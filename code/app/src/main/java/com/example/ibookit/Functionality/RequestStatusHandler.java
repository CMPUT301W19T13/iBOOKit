/**
 * Class name: RequestStatusHandler
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Functionality;

import android.util.Log;

/**
 * @author Jiazhen Li
 *
 * @version 1.0
 */
public class RequestStatusHandler {
    private static final String TAG = "RequestStatusHandler";

    /**
     * Constructor
     */
    public RequestStatusHandler(){

    }

    /**
     * transfer status int to string
     * @param status
     * @return
     */
    public String StatusIntegerToString (Integer status) {
        if (status == 0) {
            return "Pending";
        } else if (status == 1) {
            return "Accepted";
        } else if (status == 2) {
            return "Declined";
        } else {
            Log.d(TAG, "StatusString: Out of range");
            return "";
        }
    }
}
