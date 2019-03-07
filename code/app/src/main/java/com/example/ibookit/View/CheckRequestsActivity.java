package com.example.ibookit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ibookit.ListAdapter.RequestListAdapter;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestSent;
import com.example.ibookit.R;

import java.util.ArrayList;

public class CheckRequestsActivity extends AppCompatActivity {

    private ListView Sent;
    private ListView received;
    private ArrayList<Request> RSent=  new ArrayList<>();
    private ArrayList<Request> Rreceived = new ArrayList<>();
    private ArrayAdapter<Request> adapterS;
    private ArrayAdapter<Request> adapterR;
    private RequestSent requestSent = new RequestSent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Sent = findViewById(R.id.sent_list);
        received = findViewById(R.id.received_list);


        //crash if add this one but use for get data
        //requestSent.RetriveRequest(adapterS);
        //ArrayList<Request> exam = new ArrayList<>();


        //test for adapter ok
        //Book book = new Book();
        //Request a= new Request(book);
        //a.setReceiver("xiaoxiao");
        //exam.add(a);
//        RSent = requestSent2.RetriveRequest(RSent);

        adapterS = new RequestListAdapter(this,R.layout.adapter_request, RSent);
        Sent.setAdapter(adapterS);
        requestSent.RetriveRequest(RSent, adapterS);

        //requestSent.RetriveRequest();



        setBottomNavigationView();
    }

    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
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