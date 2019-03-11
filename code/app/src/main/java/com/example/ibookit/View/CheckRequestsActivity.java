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

import com.example.ibookit.ListAdapter.RequestListAdapter;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.Model.RequestSent;
import com.example.ibookit.R;

import java.util.ArrayList;

public class CheckRequestsActivity extends AppCompatActivity {

    private ListView Sent;
    private ListView Received;
    private ArrayList<Request> RSent = new ArrayList<>();
    private ArrayList<String> Rbook = new ArrayList<>();
    private ArrayAdapter<Request> adapterS;
    private ArrayAdapter<String> adapterB;
    private RequestSent requestSent = new RequestSent();
    private RequestReceived requestReceived = new RequestReceived();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Sent = findViewById(R.id.sent_list);
        Received = findViewById(R.id.received_list);


        adapterS = new RequestListAdapter(this,R.layout.adapter_request,RSent);
        Sent.setAdapter(adapterS);
        requestSent.RetriveRequest(RSent,adapterS);

        adapterB = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Rbook);
        Received.setAdapter(adapterB);


        Received.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) Received.getItemAtPosition(position);
                //Toast.makeText(CheckRequestsActivity.this,"You selected : "+ item,Toast.LENGTH_LONG).show();
                Bundle b = new Bundle();
                b.putString("bookname",item);
                Intent intent = new Intent(CheckRequestsActivity.this,RequestListForEachBookActivity.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });




        setBottomNavigationView();
    }

    public void openactivity(){
        Intent intent = new Intent(CheckRequestsActivity.this,RequestListForEachBookActivity.class);
        startActivityForResult(intent, 0);
    }

    protected void onResume() {

        super.onResume();

        Rbook.clear();
        adapterB.notifyDataSetChanged();
        requestReceived.RetriveBook(Rbook,adapterB);
    }

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
