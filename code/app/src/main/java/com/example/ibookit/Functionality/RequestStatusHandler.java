package com.example.ibookit.Functionality;

import android.util.Log;

public class RequestStatusHandler {
    private static final String TAG = "RequestStatusHandler";

    public RequestStatusHandler(){

    }

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
