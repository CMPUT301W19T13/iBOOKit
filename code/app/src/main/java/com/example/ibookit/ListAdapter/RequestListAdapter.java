package com.example.ibookit.ListAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Request;
import com.example.ibookit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class RequestListAdapter extends ArrayAdapter<Request> {
    private Context mContext;

    private int mResource;
    private DatabaseReference mDatabase;
    private TextView mTitle, mReceiver, mIs_accpected;
    private Book mBook;

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

        getBook(request.getBookId(), mTitle);

        mReceiver.setText("Owner:  " + request.getReceiver());

        mIs_accpected.setText("Isbn:  " + request.getIsAccept());


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

