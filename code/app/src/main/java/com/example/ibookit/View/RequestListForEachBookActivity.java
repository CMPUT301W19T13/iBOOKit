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
import android.support.v7.app.AppCompatActivity;
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

    private ArrayList<Request> Rreceived = new ArrayList<>();
    private ArrayAdapter<Request> adapterR;
    private RequestReceived requestReceived = new RequestReceived();
    private ListView Userlist;
    public int positionpoint;
    private DatabaseReference ReqDatabase;
    private Request tempquest;
    private String myname;

    /**
     * Showing user who request a particular book in UI
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_userlist);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        myname = user.getDisplayName();


        Userlist = findViewById(R.id.userlist);
        final String bookname = getIntent().getStringExtra("bookname");
        //Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();

        adapterR = new RequestForEachBookListAdapter(this,R.layout.adapter_request,Rreceived);
        Userlist.setAdapter(adapterR);
        requestReceived.RequestInBook(Rreceived,adapterR,bookname);


        ReqDatabase = FirebaseDatabase.getInstance().getReference();

        Userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                positionpoint = position;

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RequestListForEachBookActivity.this);
                builder.setCancelable(true);
                builder.setTitle("This user wants to borrow the book");



                builder.setNegativeButton("Reject ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        tempquest = Rreceived.get(positionpoint);

                        ReqDatabase.child("users").child(tempquest.getSender()).child("requestSent").child(tempquest.getRid()).removeValue();
                        ReqDatabase.child("users").child(myname).child("requestReceived").child(tempquest.getRid()).removeValue();
                        ReqDatabase.child("users").child(tempquest.getSender()).child("Replies").child(myname).child(bookname).setValue("Rejected");


                        Rreceived.remove(positionpoint);
                        adapterR.notifyDataSetChanged();

                    }
                });


                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String Rid = Rreceived.get(positionpoint).getRid();
                        tempquest = Rreceived.get(positionpoint);

                        for(Request r : Rreceived){

                            ReqDatabase.child("users").child(myname).child("requestReceived").child(r.getRid()).removeValue();
                            ReqDatabase.child("users").child(r.getSender()).child("requestReceived").child(r.getRid()).removeValue();
                            //adapterR.notifyDataSetChanged();

                        }
                        ReqDatabase.child("books").child(Rid).child("status").setValue(1);
                        ReqDatabase.child("users").child(tempquest.getSender()).child("Replies").child(myname).child(bookname).setValue("Accepted");
                        Rreceived.clear();


                        finish();
                    }
                });

                builder.show();


            }
        });

        if(Rreceived.isEmpty()){
            Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();
        }

    }
}

