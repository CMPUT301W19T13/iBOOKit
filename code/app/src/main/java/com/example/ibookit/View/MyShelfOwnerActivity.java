/**
 * Class name: MyShelfOwnerActivity
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Toast;

import android.widget.ListView;

import com.example.ibookit.ListAdapter.BookListAdapter;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;

import com.example.ibookit.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;


/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class MyShelfOwnerActivity extends AppCompatActivity {
    public static Context sContext;

    private static final String TAG = "MyShelfOwnerActivity";
    private ListView mListView;
    private Button chooseAvailable, chooseRequested, chooseAccepted, chooseBorrowed, myshelf, scanButton;
    private ArrayAdapter<Book> adapter;
    private ArrayList<Book> mBooks = new ArrayList<>();
    private OwnerShelf ownerShelf = new OwnerShelf();
    private Integer status;
    private final Integer scanRequestCode = 1000;
    private Book CurrentProcessLending;

    /**
     * Showing owner shelf in a listView
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshelf_mybook);

        chooseAvailable = findViewById(R.id.myshlf_available);
        chooseRequested = findViewById(R.id.myshlf_requested);
        chooseAccepted = findViewById(R.id.myshelf_accepted);
        chooseBorrowed = findViewById(R.id.myshelf_borrowed);
        myshelf = findViewById(R.id.my_book);
        scanButton = findViewById(R.id.lendOrReceiveReturn);

        mListView = findViewById(R.id.bookListView);
        Button changeShelf = findViewById(R.id.borrowed);

        changeShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyShelfOwnerActivity.this, MyShelfBorrowerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        // Book status selector
        chooseAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 0;
                ownerShelf.SyncBookShelf(mBooks, adapter, status);
            }
        });

        chooseRequested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 1;
                ownerShelf.SyncBookShelf(mBooks, adapter, status);
            }
        });

        chooseAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 2;
                ownerShelf.SyncBookShelf(mBooks, adapter, status);
            }
        });

        chooseBorrowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 3;
                ownerShelf.SyncBookShelf(mBooks, adapter, status);
            }
        });

        myshelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = -1;
                ownerShelf.SyncBookShelf(mBooks, adapter, status);
            }
        });
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  //get book information and compare it with isbn scanned
                  // if not match, return "this isn't the book"
                status = -1;
                Intent scan = new Intent(MyShelfOwnerActivity.this, ScannerActivity.class);
                startActivityForResult(scan, scanRequestCode);

            }
        });

        setBottomNavigationView();

        ListViewClickHandler();


    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new BookListAdapter(this, R.layout.adapter_book, mBooks);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
        ownerShelf.SyncBookShelf(mBooks, adapter, -1); // -1 means let listView showing all books

    }

    /**
     * Navigation bar enabled
     */
    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent add = new Intent(MyShelfOwnerActivity.this, AddBookAsOwnerActivity.class);
                        add.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(MyShelfOwnerActivity.this, HomeSearchActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(MyShelfOwnerActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        Intent request = new Intent(MyShelfOwnerActivity.this, CheckRequestsActivity.class);
                        request.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(request);

                        break;
                }

                return false;
            }
        });
    }

    /**
     * Handle user clicking item on the list
     */
    private void ListViewClickHandler () {
        final ListView finalList = mListView;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) finalList.getItemAtPosition(position);


                // todo: update or delete if this book is not in available status, need to update this info under all requests

                setDialog(book);

            }
        });
    }

    /**
     * set a dialog when clicking item
     * choosing if user want to cancel or view book information
     * @param book
     */
    private void setDialog(final Book book) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Choose an action");
        builder.setCancelable(true);

        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MyShelfOwnerActivity.this, ViewBookInfoAsOwnerActivity.class);
                Gson gson = new Gson();

                String out = gson.toJson(book);

                intent.putExtra("book", out);
                startActivity(intent);
            }
        });

//        builder.setNeutralButton("lend", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //get book information and compare it with isbn scanned
//                // if not match, return "this isn't the book"
//                Intent scan = new Intent(MyShelfOwnerActivity.this, ScannerActivity.class);
//                CurrentProcessLending = book;
//                startActivityForResult(scan, scanRequestCode);
//
//
//            }
//        });

        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //if book is not borrowed
                if (book.getStatus() != 3 ) {
                    ownerShelf.remove_book(book);
                    Toast.makeText(MyShelfOwnerActivity.this, "Book deleted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyShelfOwnerActivity.this, "Can't delete this book",
                            Toast.LENGTH_SHORT).show();
                }
                
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (requestCode == scanRequestCode && resultCode == RESULT_OK ){

            final String scannedISBN = data.getStringExtra("scanned_ISBN");
            final String bookID = "";






            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final String username = user.getDisplayName();
            final DatabaseReference ownShelfRef = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("ownerShelf");
            final DatabaseReference requestRef = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("requestReceived");



            ownShelfRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d:dataSnapshot.getChildren()){
                        // else the book does not exit in owner shelf or request is not accepted
                        // book status of 2 stands for accepted
                        if (d.child("isbn").getValue().toString().equals(scannedISBN)
                        && d.child("status").getValue().toString().equals("2")){

                            //todo: what if user have two different book with same isbn, and both of
                            //them are requested and accepted, this will set both of them to lend pending
                            final String bookID = d.getKey();
                            requestRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot d:dataSnapshot.getChildren()){
                                        if (d.child("bookId").getValue().toString().equals(bookID)){
//                                            Toast.makeText(MyShelfOwnerActivity.this, "request found",
//                                                Toast.LENGTH_SHORT).show();
                                            // update book status on book directory and on owner shelf directory
                                            DatabaseReference shelfBookRef = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("ownerShelf").child(bookID);
                                            DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference().child("books").child("ownerShelf").child(bookID);
                                            shelfBookRef.child("status").setValue(3);
                                            bookRef.child("status").setValue(3);
                                            ownerShelf.SyncBookShelf(mBooks, adapter, status);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
//
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
//            if (scannedISBN.equals(CurrentProcessLending.getIsbn())){
//                Toast.makeText(MyShelfOwnerActivity.this, "Book lend out",
//                        Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(MyShelfOwnerActivity.this, "Un-matching Book",
//                        Toast.LENGTH_SHORT).show();
//            }


        }
        else{
            Toast.makeText(MyShelfOwnerActivity.this, "Unexpected error occurred",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
