package com.example.ibookit.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
        final String bookname = getIntent().getStringExtra("bookname");
        //Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();

        adapterR = new RequestForEachBookListAdapter(this,R.layout.adapter_request,Rreceived);
        Userlist.setAdapter(adapterR);
        requestReceived.RequestInBook(Rreceived,adapterR,bookname);

        Userlist.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Request request = (Request) Userlist.getItemAtPosition(position);
                final String item = request.getSender();
                new AlertDialog.Builder(RequestListForEachBookActivity.this)
                        .setTitle("Accept Request?")
                        .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Toast.makeText(RequestListForEachBookActivity.this, "YES", Toast.LENGTH_LONG).show();

                                requestReceived.accept_request(Rreceived,request);
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Toast.makeText(RequestListForEachBookActivity.this,"NO"+item,Toast.LENGTH_SHORT).show();
                                requestReceived.decline_request(request);
                                dialogInterface.dismiss();
                            }
                        })
                        .show();

            }


//            public void onClick(View v) {
//                new AlertDialog.Builder( RequestListForEachBookActivity.this )
//                        .setTitle( "Cast Recording" )
//                        .setMessage( "Now recording your message" )
//                        .setPositiveButton( "Save", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Log.d( "AlertDialog", "Positive" );
//                            }
//                        })
//                        .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Log.d( "AlertDialog", "Negative" );
//                            }
//                        } )
//                        .show();
//            }
        });





        //adapterR = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Rreceived);
        //Userlist.setAdapter(adapterR);
        //requestReceived.RequestInBook(Rreceived,adapterR,bookname);
        if(Rreceived.isEmpty()){
            Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();
        }

    }
}
