package com.example.ibookit.ListAdapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ibookit.Model.Request;

import java.util.ArrayList;

public class RequestListAdapter extends ArrayAdapter<Request> {
    private Context mContext;

    private int mResource;

    private TextView mTitle, mAuthor, mIsbn, mStatus;

    public RequestListAdapter(Context context, int resource, ArrayList<Request> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
}

