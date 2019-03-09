package com.example.ibookit.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

<<<<<<< HEAD
=======
import com.example.ibookit.ListAdapter.RequestForEachBookListAdapter;
>>>>>>> fd398f1ad0126d5f4ec00c266c1dbe59a0280ae3
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.RequestReceived;
import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class RequestListForEachBookActivity extends AppCompatActivity {

    private ArrayList<Request> Rreceived = new ArrayList<>();
    private ArrayAdapter<Request> adapterR;
    private RequestReceived requestReceived = new RequestReceived();
    private ListView Userlist;
    private DatabaseReference requestRefrence;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_userlist);





        Userlist = findViewById(R.id.userlist);
<<<<<<< HEAD
        Bundle extras = getIntent().getExtras();
        final String bookname = extras.getString("bookname");
=======
        String bookname = getIntent().getStringExtra("bookname");
>>>>>>> fd398f1ad0126d5f4ec00c266c1dbe59a0280ae3
        //Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();

        adapterR = new RequestForEachBookListAdapter(this,R.layout.adapter_request,Rreceived);
        Userlist.setAdapter(adapterR);
        requestReceived.RequestInBook(Rreceived,adapterR,bookname);

<<<<<<< HEAD
        //while(requestReceived.getIds() != null);

        //System.out.println(requestReceived.getIds());


        requestRefrence = FirebaseDatabase.getInstance().getReference();


                Userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String username = Rreceived.get(position);
                       // String reqId = Rreceived.get()

                        //requestRefrence.child("users").child(username).child("requestSent").child();


                        final ArrayList<String> allIds= requestReceived.getIds();
                        final String deleteId = allIds.get(position);

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        final String myname = user.getDisplayName();

                        final int temp = position;


                        AlertDialog.Builder builder = new AlertDialog.Builder(RequestListForEachBookActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("This user wants to borrow your book");

                        builder.setNegativeButton("reject", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                requestRefrence.child("users").child(myname).child("requestReceived").child(deleteId).removeValue();
                                requestRefrence.child("users").child(Rreceived.get(temp)).child("requestSent").child(deleteId).removeValue();
                                requestRefrence.child("requests").child(deleteId).removeValue();



                                requestRefrence.child("users").child(Rreceived.get(temp)).child("Replies")
                                        .child(myname).child(bookname).setValue("Declined");


                            }
                        });

                        builder.setPositiveButton("Accept",new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String saved = Rreceived.get(temp);



                                int e = 0;
                                for( String i : Rreceived){
                                    requestRefrence.child("users").child(myname).child("requestReceived").child(allIds.get(e)).removeValue();
                                    requestRefrence.child("users").child(i).child("requestSent").child(allIds.get(e)).removeValue();
                                    requestRefrence.child("requests").child(allIds.get(e)).removeValue();
                                    requestRefrence.child("users").child(i).child("Replies")
                                            .child(myname).child(bookname).setValue("Declined");
                                    System.out.println(myname + allIds.get(e));
                                    System.out.println(i + allIds.get(e));
                                    e ++;

                                }

                                requestRefrence.child("users").child(saved).child("Replies")
                                        .child(myname).child(bookname).setValue("Accepted");





                            }
                        });

                        builder.show();





                    }
                });




=======
>>>>>>> fd398f1ad0126d5f4ec00c266c1dbe59a0280ae3




<<<<<<< HEAD
=======
        //adapterR = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Rreceived);
        //Userlist.setAdapter(adapterR);
        //requestReceived.RequestInBook(Rreceived,adapterR,bookname);
>>>>>>> fd398f1ad0126d5f4ec00c266c1dbe59a0280ae3
        if(Rreceived.isEmpty()){
            Toast.makeText(RequestListForEachBookActivity.this,"You selected : "+ bookname,Toast.LENGTH_LONG).show();
        }

    }
}
