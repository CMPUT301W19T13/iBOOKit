package com.example.ibookit.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ibookit.Model.Request;
import com.example.ibookit.R;

import java.util.ArrayList;

public class RequestListAdapter extends ArrayAdapter<Request> {
    private Context mContext;

    private int mResource;

    private TextView mTitle, mReceiver, mIs_accpected;

    public RequestListAdapter(Context context, int resource, ArrayList<Request> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        Request request = getItem(position);


        mTitle = convertView.findViewById(R.id.listTitle);
        mReceiver = convertView.findViewById(R.id.listReceiver);
        mIs_accpected = convertView.findViewById(R.id.listIs_accepted);

        mTitle.setText("Title:  " + request.getBook().getTitle());
        mReceiver.setText("Author:  " + request.getReceiver());
        mIs_accpected.setText("Isbn:  " + request.getIsAccept());


        return convertView;
    }
}

