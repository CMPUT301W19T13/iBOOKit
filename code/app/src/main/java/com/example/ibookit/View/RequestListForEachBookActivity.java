package com.example.ibookit.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.R;

import java.util.ArrayList;

public class RequestListForEachBookActivity extends AppCompatActivity {

    private ArrayList<String> Rreceived = new ArrayList<>();
    private ArrayAdapter<String> adapterR;
    private RequestReceived requestReceived = new RequestReceived();
    private ListView Userlist;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_userlist);


        Userlist = findViewById(R.id.userlist);
        //Bundle extras = getIntent().getExtras();
        String bookname = getIntent().getStringExtra("bookname");
        //Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();
        //String bookname= getIntent().getStringExtra("bookname");
        adapterR = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Rreceived);
        Userlist.setAdapter(adapterR);
        requestReceived.RequestInBook(Rreceived,adapterR,bookname);
        if(Rreceived.isEmpty()){
            Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();
        }

    }
}
