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
import android.widget.Button;
import android.widget.ListView;

import com.example.ibookit.ListAdapter.RequestListAdapter;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.Model.RequestSent;
import com.example.ibookit.R;

import java.util.ArrayList;


/**
 * @author Jiazhen Li
 *
 * @version 1.0
 */
public class CheckRequestsActivity extends AppCompatActivity {

    private ListView Sent;
    private ListView Received;
    private Button Accept_list;
    private ArrayList<Request> RSent = new ArrayList<>();
    private ArrayList<String> Rbook = new ArrayList<>();
    private ArrayList<Book> Rsort = new ArrayList<>();
    private ArrayAdapter<Request> adapterS;
    private ArrayAdapter<String> adapterB;
    private RequestSent requestSent = new RequestSent();
    private RequestReceived requestReceived = new RequestReceived();

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
        //Accept_list = findViewById(R.id.accept_list);


        adapterS = new RequestListAdapter(this,R.layout.adapter_request,RSent);
        Sent.setAdapter(adapterS);
        requestSent.RetriveRequest(RSent,adapterS);

        adapterB = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Rbook);
        Received.setAdapter(adapterB);


        Received.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) Received.getItemAtPosition(position);

                Intent intent = new Intent(CheckRequestsActivity.this,RequestListForEachBookActivity.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        setBottomNavigationView();
    }


    protected void onResume() {
        super.onResume();

        Rbook.clear();
        adapterB.notifyDataSetChanged();
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
