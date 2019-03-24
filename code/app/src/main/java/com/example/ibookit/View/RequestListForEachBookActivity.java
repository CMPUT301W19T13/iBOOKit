/**
 * Class name: RequestListForEachBookActivity
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ibookit.ListAdapter.RequestForEachBookListAdapter;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * @author Jiazhen Li
 *
 * @version 1.0
 */
public class RequestListForEachBookActivity extends AppCompatActivity {

    private static final String TAG = "RequestListForEachBook";
    private ArrayList<Request> Received = new ArrayList<>();
    private ArrayAdapter<Request> adapterR;
    private RequestReceived requestReceived = new RequestReceived();
    private ListView Userlist;


    /**
     * Showing user who request a particular book in UI
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_userlist);

        Userlist = findViewById(R.id.userlist);
        final String bookname = getIntent().getStringExtra("bookname");

        adapterR = new RequestForEachBookListAdapter(this,R.layout.adapter_request,Received);
        Userlist.setAdapter(adapterR);
        requestReceived.RequestInBook(Received, adapterR, bookname);

        Userlist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Request request = (Request) Userlist.getItemAtPosition(position);

                new AlertDialog.Builder(RequestListForEachBookActivity.this)
                        .setTitle("Accept Request?")
                        .setCancelable(true)
                        .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (request.getIsAccept() == 0) {
                                    requestReceived.accept_request(Received, request);
                                } else {
                                    Toast.makeText(RequestListForEachBookActivity.this, "Already make decision", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        })
                        .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Toast.makeText(RequestListForEachBookActivity.this,"NO"+item,Toast.LENGTH_SHORT).show();
                                if (request.getIsAccept() == 0) {
                                    requestReceived.decline_request(request);
                                } else {
                                    Toast.makeText(RequestListForEachBookActivity.this, "Already make decision", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();

            }
        });

    }
}

