/**
 * Class name: ShowSearchResultActivity
 *
 * version 1.2
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ibookit.Functionality.BookStatusHandler;
import com.example.ibookit.Functionality.CreateRequestHandler;
import com.example.ibookit.Functionality.SearchForBook;
import com.example.ibookit.Functionality.SearchForUser;
import com.example.ibookit.ListAdapter.BookListAdapter;
import com.example.ibookit.ListAdapter.UserListAdapter;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Request;
import com.example.ibookit.Model.User;
import com.example.ibookit.R;
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
 * @author zisen
 *
 * @version 1.2
 */

public class ShowSearchResultActivity extends AppCompatActivity {

    private static final String TAG = "ShowSearchResult";
    private ListView searchResultListView;
    private ArrayAdapter<Book> bookArrayAdapter;
    private ArrayAdapter<User> userArrayAdapter;
    private ArrayList<Book> bookResult;
    private ArrayList<User> userResult;
    private String type, searchValue;

    private SearchView sv;
    private Intent intent;

    /**
     * Show search result after user typed keywords for user or book in search bar
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        searchResultListView = findViewById(R.id.search_result_list);

        intent = getIntent();
        type = intent.getStringExtra("type");
        searchValue = intent.getStringExtra("SearchValue");

        sv = findViewById(R.id.search_bar);

        configure_SearchButtonsAndSearchBar();
        load_resultList();
        setBottomNavigationView();

    }

    /**
     * load search result
     */
    private void load_resultList(){
        if (type.equals("SearchUser")) {

            ListViewClickHandler();

            Log.d(TAG, "onCreate: " + searchValue);
            SearchForUser userSearch = new SearchForUser();
            ArrayList<User> searchResult= new ArrayList<>();

            Log.d(TAG, "onCreate: " + searchResult);

            userArrayAdapter = new UserListAdapter(this, R.layout.adapter_user, searchResult);
            searchResultListView.setAdapter(userArrayAdapter);
            searchResultListView.setClickable(true);
            userSearch.searchByKeyword(searchValue, searchResult, userArrayAdapter);

        } else if (type.equals("SearchCategory")) {

            ListViewClickHandler();

            Log.d(TAG, "onCreate: " + searchValue);
            SearchForBook bookSearch = new SearchForBook();
            ArrayList<Book> searchResult = new ArrayList<>();

            Log.d(TAG, "onCreate: " + searchResult);

            bookArrayAdapter = new BookListAdapter(this, R.layout.adapter_book, searchResult);
            searchResultListView.setAdapter(bookArrayAdapter);
            searchResultListView.setClickable(true);
            bookSearch.searchByCategory(searchValue, searchResult, bookArrayAdapter);


        } else if (type.equals("SearchBook")) {

            ListViewClickHandler();

            Log.d(TAG, "onCreate: " + searchValue);
            SearchForBook bookSearch = new SearchForBook();
            ArrayList<Book> searchResult = new ArrayList<>();
            Log.d(TAG, "onCreate: " + searchResult);

            bookArrayAdapter = new BookListAdapter(this, R.layout.adapter_book, searchResult);
            searchResultListView.setAdapter(bookArrayAdapter);
            searchResultListView.setClickable(true);
            //extra step for search book by keyword: prepare list of keyword
            String[] searchValueList = searchValue.split("\\s+");

            bookSearch.searchByKeyword(searchValueList, searchResult, bookArrayAdapter);

        } else {
            Log.d(TAG, "onCreate: Unexpected type: " + type);
        }
        sv.setQuery("", false);
        sv.clearFocus();

    }

    /**
     * Handle user click item on listView
     */
    private void ListViewClickHandler () {
        final ListView finalList = searchResultListView;
        if (this.type.equals("SearchCategory" ) || this.type.equals("SearchBook")) {
            searchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Book book = (Book) finalList.getItemAtPosition(position);
                        setDialog(book);
                    } catch (Exception e){

                    }
                }
            });
        }
        else if (this.type.equals("SearchUser")){
            searchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        User user = (User) finalList.getItemAtPosition(position);

                        Intent resultProfile = new Intent(ShowSearchResultActivity.this, UserProfileActivity.class);
                        Gson gson = new Gson();
                        String userResult = gson.toJson(user);
                        resultProfile.putExtra("UserResult", userResult);
                        startActivity(resultProfile);
                    } catch (Exception e) {

                    }
                }
            });
        }
    }

    /**
     * handle the condition for different search type (user, book or category)
     */
    private void configure_SearchButtonsAndSearchBar(){
        Button searchUser = findViewById(R.id.re_search_user);
        Button viewCategory = findViewById(R.id.re_search_category);
        Button searchBook = findViewById(R.id.re_search_book);
        sv = findViewById(R.id.re_search_bar);

        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "SearchUser";
                searchValue = sv.getQuery().toString();
                load_resultList();
                sv.clearFocus();

            }
        });
        viewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               setCategoryDialog();

            }
        });
        searchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "SearchBook";
                searchValue = sv.getQuery().toString();
                load_resultList();
                sv.clearFocus();
            }
        });

    }

    /**
     * NavigationBar enabled
     */
    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent add = new Intent(ShowSearchResultActivity.this, AddBookAsOwnerActivity.class);
                        add.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        finish();
                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(ShowSearchResultActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(ShowSearchResultActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        Intent request = new Intent(ShowSearchResultActivity.this, CheckRequestsActivity.class);
                        request.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(request);

                        break;
                }

                return false;
            }
        });
    }


    /**
     * Show a dialog when user click the item.
     * @param book
     */
    private void setDialog(final Book book) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Send request to owner?");
        builder.setCancelable(true);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                DatabaseReference allRequestSent = FirebaseDatabase.getInstance().getReference().child("users").child(user.getDisplayName()).child("requestSent");
                allRequestSent.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren()) {
                            Request r = d.getValue(Request.class);
                            if (r.getBookId().equals(book.getId())) {
                                Toast.makeText(ShowSearchResultActivity.this, "Already requested",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        // Not requested yet
                        Request request = new Request(book);
                        CreateRequestHandler createRequest = new CreateRequestHandler();
                        if (createRequest.SendRequestToOwner(request)) {
                            // send notification to owner
                            createRequest.setNotificationToOwner(book.getTitle());
                            // change status to 1
                            new BookStatusHandler().setBookStatusFirebase(book, 1);

                            // make toast
                            Toast.makeText(ShowSearchResultActivity.this, "send request successful",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(ShowSearchResultActivity.this, "Cannot request your own book",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.setNeutralButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ShowSearchResultActivity.this, SendRequestActivity.class);
                Gson gson = new Gson();
                String out = gson.toJson(book);
                intent.putExtra("book", out);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    /**
     * Show category of the book the system have
     * let user choose the category in UI
     */
    private void setCategoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final CharSequence[] options  = getResources().getStringArray(R.array.category);
        builder.setTitle("Choose a category").setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                type = "SearchCategory";
                searchValue = options[which].toString();
                load_resultList();
                sv.clearFocus();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
