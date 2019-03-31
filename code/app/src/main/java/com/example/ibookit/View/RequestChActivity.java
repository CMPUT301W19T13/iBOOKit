/**
 * Class name: AddBookOwnerActivity
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ibookit.ListAdapter.BookListAdapter;
import com.example.ibookit.ListAdapter.RequestBookListAdapter;
import com.example.ibookit.ListAdapter.RequestListAdapter;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestR;
import com.example.ibookit.Model.RequestS;
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
public class RequestChActivity extends AppCompatActivity {

    private ListView Sent;
    private ListView Received;
    private ArrayList<Request> RSent = new ArrayList<>();
    private ArrayList<Book> Rbook = new ArrayList<>();
    private ArrayAdapter<Request> adapterS;
    private ArrayAdapter<Book> adapterB;
    private RequestS requestS = new RequestS();
    private RequestR requestR = new RequestR();
    private DatabaseReference mDatabase;

    /**
     * let user check requestS and RequestR in UI
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
        requestS.RetriveRequest(RSent,adapterS);

        adapterB = new RequestBookListAdapter(this,R.layout.adapter_book,Rbook);
        Received.setAdapter(adapterB);

        Sent.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Request item = (Request) Sent.getItemAtPosition(position);
                if(item.getIsAccept() == 1){
                    // if, check lat lon in request from databse
                    //if no, toast and do nothing
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("locations");
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(item.getRid())) {
                                Intent intent1 = new Intent(RequestChActivity.this, LocationVActivity.class);
                                intent1.putExtra("ridS", item.getRid());
                                startActivity(intent1);
                            } else {
                                Toast.makeText(RequestChActivity.this, "No location has been set by owner", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(RequestChActivity.this,"TEST"+item, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RequestChActivity.this, BookRequestListActivity.class);
                intent.putExtra("bookname",item.getTitle());
                startActivity(intent);

            }
        });

        setBottomNavigationView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestR.RetriveBook(Rbook,adapterB);

    }


    /**
     * NavigationBar enabled
     */
    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        Intent add = new Intent(RequestChActivity.this, AddBookOwnerActivity.class);
                        add.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(RequestChActivity.this, HomeSearchActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(RequestChActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(RequestChActivity.this, UserProfileActivity.class);
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
