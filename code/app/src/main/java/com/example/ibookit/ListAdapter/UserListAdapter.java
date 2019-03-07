package com.example.ibookit.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ibookit.Model.User;
import com.example.ibookit.R;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<User> {

    private Context mContext;
    private int mResource;
    private TextView mUsername, mEmail, mPhone;

    public UserListAdapter(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        User user = getItem(position);

        mUsername = convertView.findViewById(R.id.listUsername);
        mEmail = convertView.findViewById(R.id.listEmail);
        mPhone = convertView.findViewById(R.id.listPhoneNumber);

        mUsername.setText(user.getUsername());
        mEmail.setText(user.getEmail());
        mPhone.setText(user.getPhoneNumber());


        return convertView;
    }
}
