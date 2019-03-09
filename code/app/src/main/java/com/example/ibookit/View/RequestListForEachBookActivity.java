package com.example.ibookit.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ibookit.ListAdapter.RequestForEachBookListAdapter;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.R;

import java.util.ArrayList;

public class RequestListForEachBookActivity extends AppCompatActivity {

    private ArrayList<Request> Rreceived = new ArrayList<>();
    private ArrayAdapter<Request> adapterR;
    private RequestReceived requestReceived = new RequestReceived();
    private ListView Userlist;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_userlist);


        Userlist = findViewById(R.id.userlist);
        String bookname = getIntent().getStringExtra("bookname");
        //Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();

        adapterR = new RequestForEachBookListAdapter(this,R.layout.adapter_request,Rreceived);
        Userlist.setAdapter(adapterR);
        requestReceived.RequestInBook(Rreceived,adapterR,bookname);





        //adapterR = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Rreceived);
        //Userlist.setAdapter(adapterR);
        //requestReceived.RequestInBook(Rreceived,adapterR,bookname);
        if(Rreceived.isEmpty()){
            Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();
        }

    }
}
