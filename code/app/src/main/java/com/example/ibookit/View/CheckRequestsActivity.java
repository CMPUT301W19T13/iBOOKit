/**
 * Class name: AddBookAsOwnerActivity
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ibookit.ListAdapter.BookListAdapter;
import com.example.ibookit.ListAdapter.RequestListAdapter;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.Model.RequestSent;
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
public class CheckRequestsActivity extends AppCompatActivity {

    private ListView Sent;
    private ListView Received;
    private ArrayList<Request> RSent = new ArrayList<>();
    private ArrayList<Book> Rbook = new ArrayList<>();
    private ArrayAdapter<Request> adapterS;
    private ArrayAdapter<Book> adapterB;
    private RequestSent requestSent = new RequestSent();
    private RequestReceived requestReceived = new RequestReceived();
    private DatabaseReference mDatabase;

    /**
     * let user check requestSent and RequestReceived in UI
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Sent = findViewById(R.id.sent_list);
        Received = findViewById(R.id.received_list);


        adapterS = new RequestListAdapter(this,R.layout.adapter_request,RSent);
        Sent.setAdapter(adapterS);
        requestSent.RetriveRequest(RSent,adapterS);

        adapterB = new BookListAdapter(this,R.layout.adapter_book,Rbook);
        Received.setAdapter(adapterB);

        Sent.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Request item = (Request) Sent.getItemAtPosition(position);
                if(item.getIsAccept() == 1){
                    // if, check lat lon in request from databse
                    //if no, toast and do nothing
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("requests").child(item.getRid());
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("lat")) {
                                Intent intent1 = new Intent(CheckRequestsActivity.this, ViewLocationActivity.class);
                                intent1.putExtra("ridS", item.getRid());
                                startActivity(intent1);
                            } else {
                                Toast.makeText(CheckRequestsActivity.this, "No location has been set by owner", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });

        Received.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book item = (Book) Received.getItemAtPosition(position);
                //Toast.makeText(CheckRequestsActivity.this,"TEST"+item, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CheckRequestsActivity.this,RequestListForEachBookActivity.class);
                intent.putExtra("bookname",item.getTitle());
                startActivity(intent);

            }
        });

        setBottomNavigationView();
    }


    protected void onResume() {
        super.onResume();
        requestReceived.RetriveBook(Rbook,adapterB);

    }


    /**
     * NavigationBar enabled
     */
    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        Intent add = new Intent(CheckRequestsActivity.this, AddBookAsOwnerActivity.class);
                        add.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(CheckRequestsActivity.this, HomeSearchActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(CheckRequestsActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(CheckRequestsActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        break;
                }

                return false;
            }
        });
    }

}
