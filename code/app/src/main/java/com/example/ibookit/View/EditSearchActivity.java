package com.example.ibookit.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ibookit.Functionality.SearchForBook;
import com.example.ibookit.Functionality.SearchForUser;
import com.example.ibookit.ListAdapter.BookListAdapter;
import com.example.ibookit.ListAdapter.UserListAdapter;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;
import com.example.ibookit.R;

import java.util.ArrayList;

public class EditSearchActivity extends AppCompatActivity {

    private static final String TAG = "EditSearchActivity";
    private ListView searchResultListView;
    private ArrayAdapter<Book> bookArrayAdapter;
    private ArrayAdapter<User> userArrayAdapter;
    private ArrayList<Book> bookResult;
    private ArrayList<User> uerResult;
    private String type, searchValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        searchResultListView = findViewById(R.id.search_result_list);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        searchValue = intent.getStringExtra("SearchValue");

        if (type.equals("SearchUser")) {

            SearchForUser userSearch = new SearchForUser();
            ArrayList<User> searchResult= new ArrayList<>();

            userArrayAdapter = new UserListAdapter(this, R.layout.adapter_user, searchResult);
            searchResultListView.setAdapter(bookArrayAdapter);
            searchResultListView.setClickable(true);
            // same way as below



        } else if (type.equals("SearchCategory")) {

            Log.d(TAG, "onCreate: " + searchValue);
            SearchForBook bookSearch = new SearchForBook();
            ArrayList<Book> searchResult = new ArrayList<>();

            Log.d(TAG, "onCreate: " + searchResult);

            bookArrayAdapter = new BookListAdapter(this, R.layout.adapter_book, searchResult);
            searchResultListView.setAdapter(bookArrayAdapter);
            searchResultListView.setClickable(true);
            bookSearch.searchByCategory(searchValue, searchResult, bookArrayAdapter);


        } else if (type.equals("SearchTitle")) {

            Log.d(TAG, "onCreate: " + searchValue);
            SearchForBook bookSearch = new SearchForBook();
            ArrayList<Book> searchResult = new ArrayList<>();

            Log.d(TAG, "onCreate: " + searchResult);

            bookArrayAdapter = new BookListAdapter(this, R.layout.adapter_book, searchResult);
            searchResultListView.setAdapter(bookArrayAdapter);
            searchResultListView.setClickable(true);
            bookSearch.searchByTitle(searchValue, searchResult, bookArrayAdapter);

        } else {
            Log.d(TAG, "onCreate: Unexpected type: " + type);
        }



        ListViewClickHandler();
        setBottomNavigationView();

    }
    private void ListViewClickHandler () {
        final ListView finalList = searchResultListView;
        searchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) finalList.getItemAtPosition(position);
                
            }
        });
    }
    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent add = new Intent(EditSearchActivity.this, AddBookAsOwnerActivity.class);
                        add.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(EditSearchActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(EditSearchActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        Intent request = new Intent(EditSearchActivity.this, CheckRequestsActivity.class);
                        request.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(request);

                        break;
                }

                return false;
            }
        });
    }

}
