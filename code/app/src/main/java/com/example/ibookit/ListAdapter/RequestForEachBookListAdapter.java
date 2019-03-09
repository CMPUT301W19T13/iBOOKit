package com.example.ibookit.ListAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;

public class RequestForEachBookListAdapter extends ArrayAdapter<Request> { private Context mContext;

    private int mResource;
    private DatabaseReference mDatabase;
    private TextView mTitle, mSender, mIs_accpected;
    private Book mBook;

    public RequestForEachBookListAdapter(Context context, int resource, ArrayList<Request> objects) {
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
        mSender = convertView.findViewById(R.id.listReceiver);
        mIs_accpected = convertView.findViewById(R.id.listIs_accepted);

        getBook(request.getBookId(), mTitle);

        mSender.setText("Sender:  " + request.getSender());

        RequestStatusHandler handler = new RequestStatusHandler();

        mIs_accpected.setText("Status:  " + handler.StatusIntegerToString(request.getIsAccept()));


        return convertView;
    }

    private void getBook(final String bookID, final TextView mTitle) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("books").child(bookID);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);
                mTitle.setText("Title: " + book.getTitle());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}

