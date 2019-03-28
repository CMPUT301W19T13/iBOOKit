/**
 * Class name: BookRequestListActivity
 *
 * version 1.0
 *
 * Date: March 9, 2019
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
 * @version 1.0
 */
public class BookRequestListActivity extends AppCompatActivity {

    private static final String TAG = "RequestListForEachBook";
    private ArrayList<Request> Received = new ArrayList<>();
    private ArrayAdapter<Request> adapterR;
    private RequestR requestR = new RequestR();
    private ListView Userlist;
    private DatabaseReference mDatabase;


    /**
     * Showing user who request a particular book in UI
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_userlist);

        Userlist = findViewById(R.id.userlist);
        final String bookname = getIntent().getStringExtra("bookname");

        adapterR = new BookRequestListAdapter(this,R.layout.adapter_request,Received);
        Userlist.setAdapter(adapterR);
        requestR.RequestInBook(Received, adapterR, bookname);

        Userlist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Request request = (Request) Userlist.getItemAtPosition(position);

                new AlertDialog.Builder(BookRequestListActivity.this)
                        .setTitle("Accept Request?")
                        .setCancelable(true)
                        .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (request.getIsAccept() == 0) {
                                    Intent intent = new Intent(BookRequestListActivity.this, LocationSActivity.class);
                                    intent.putExtra("rid",request.getRid());
                                    startActivity(intent);

                                    //if set lat lon accept
                                    //if not, toast user and not change status
                                    mDatabase = FirebaseDatabase.getInstance().getReference().child("requests").child(request.getRid());
                                    mDatabase.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.hasChild("lat")) {
                                                requestR.accept_request(Received, request);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                } else {
                                    Toast.makeText(BookRequestListActivity.this, "Already make decision", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        })
                        .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Toast.makeText(BookRequestListActivity.this,"NO"+item,Toast.LENGTH_SHORT).show();
                                if (request.getIsAccept() == 0) {
                                    requestR.decline_request(request);
                                } else {
                                    Toast.makeText(BookRequestListActivity.this, "Already make decision", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();

            }
        });

    }

    protected void onResume() {
        super.onResume();
        //finish();
    }

}
