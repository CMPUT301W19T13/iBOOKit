package com.example.ibookit.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RequestListForEachBookActivity extends AppCompatActivity {

    private ArrayList<String> Rreceived = new ArrayList<>();
    private ArrayAdapter<String> adapterR;
    private RequestReceived requestReceived = new RequestReceived();
    private ListView Userlist;
    public int positionpoint;
    private DatabaseReference ReqDatabase;
    private Request tempquest;
    private String myname;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_userlist);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        myname = user.getDisplayName();


        Userlist = findViewById(R.id.userlist);
        Bundle extras = getIntent().getExtras();
        String bookname = extras.getString("bookname");
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
