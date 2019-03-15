/**
 * Class name: RequestListAdapter
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.ListAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibookit.Functionality.RequestStatusHandler;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Request;
import com.example.ibookit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

/**
 * @author joe
 *
 * @version 1.0
 */
public class RequestListAdapter extends ArrayAdapter<Request> {
    private Context mContext;

    private int mResource;
    private DatabaseReference mDatabase;
    private TextView mTitle, mReceiver, mIs_accpected;
    private Book mBook;
    private ImageView imageView;

    public RequestListAdapter(Context context, int resource, ArrayList<Request> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    /**
     * reference: https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        Request request = getItem(position);

        if (request != null) {
            mTitle = convertView.findViewById(R.id.listTitle);
            mReceiver = convertView.findViewById(R.id.listReceiver);
            mIs_accpected = convertView.findViewById(R.id.listIs_accepted);
            imageView = convertView.findViewById(R.id.imageRequest);

            try {
                getBook(request.getBookId(), mTitle, imageView);
            } catch (Exception e) {
                Log.d(TAG, "getView: Error");
            }


            mReceiver.setText("Owner:  " + request.getReceiver());

            RequestStatusHandler handler = new RequestStatusHandler();

            mIs_accpected.setText("Status:  " + handler.StatusIntegerToString(request.getIsAccept()));

        }

        return convertView;
    }

    private void getBook(final String bookID, final TextView mTitle, final ImageView imageView) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("books").child(bookID);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);
                mTitle.setText("Title: " + book.getTitle());
                setImage(book.getImageURL(), imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /**
     * set image for request in custom listView
     * @param path
     * @param imageView
     */
    private void setImage(String path, ImageView imageView) {
        Picasso.get().load(path).fit().centerCrop().into(imageView);
    }



}
