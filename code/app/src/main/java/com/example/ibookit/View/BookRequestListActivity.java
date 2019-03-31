/**
 * Class name: BookRequestListActivity
 *
 * version 1.1
 *
 * Date: March 28, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ibookit.ListAdapter.BookRequestListAdapter;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestR;
import com.example.ibookit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * @author Jiazhen Li
 *
 * @version 1.1
 */
public class BookRequestListActivity extends AppCompatActivity {

    private static final String TAG = "RequestListForEachBook";
    private ArrayList<Request> Received = new ArrayList<>();
    private ArrayAdapter<Request> adapterR;
    private RequestR requestR = new RequestR();
    private ListView Userlist;
    private DatabaseReference mDatabase;
    private Request request;
    private String bookname;

    /**
     * Showing user who request a particular book in UI
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_userlist);

        Userlist = findViewById(R.id.userlist);
        bookname = getIntent().getStringExtra("bookname");

        adapterR = new BookRequestListAdapter(this,R.layout.adapter_request,Received);
        Userlist.setAdapter(adapterR);
        // refresh listView
        requestR.RequestInBook(Received, adapterR, bookname);

        Userlist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                request = (Request) Userlist.getItemAtPosition(position);

                if (request.getIsAccept() == 0) {
                    new AlertDialog.Builder(BookRequestListActivity.this)
                            .setTitle("Accept Request?")
                            .setCancelable(true)
                            .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(BookRequestListActivity.this, LocationSActivity.class);
                                    intent.putExtra("rid", request.getRid());
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    requestR.decline_request(request);
                                    requestR.RequestInBook(Received, adapterR, bookname);

                                }
                            }).show();

                } else if (request.getIsAccept() == 1) {
                    new AlertDialog.Builder(BookRequestListActivity.this)
                            .setTitle("Withdraw your accept?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestR.reverse_accepted(Received, request);
                                    Toast.makeText(BookRequestListActivity.this, "Book is available now", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("locations");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (request != null) {
                    if (dataSnapshot.hasChild(request.getRid())) {
                        requestR.accept_request(Received, request);
                        Toast.makeText(BookRequestListActivity.this, "Request accepted", Toast.LENGTH_SHORT).show();

                        // refresh listView
                        requestR.RequestInBook(Received, adapterR, bookname);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
