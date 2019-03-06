package com.example.ibookit.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibookit.Functionality.SearchForBook;
import com.example.ibookit.Functionality.SearchForUser;
import com.example.ibookit.ListAdapter.BookListAdapter;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;
import com.example.ibookit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EditSearchActivity extends AppCompatActivity {

    private static final String TAG = "EditSearchActivity";
    private ListView searchResultListView;
    private ArrayAdapter<Book> bookArrayAdapter;
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
        Log.d(TAG, "onCreate: " + type);
        searchValue = intent.getStringExtra("SearchValue");

        if (type.equals("SearchUser")) {

            SearchForUser userSearch = new SearchForUser();
            ArrayList<Book> searchResult= userSearch.searchByKeyword(searchValue);



        } else if (type.equals("SearchCategory")) {

        } else if (type.equals("SearchTitle")) {

            Log.d(TAG, "onCreate: " + searchValue);
            SearchForBook bookSearch = new SearchForBook();
            ArrayList<Book> searchResult = new ArrayList<>();

            Log.d(TAG, "onCreate: " + searchResult);

            bookArrayAdapter = new BookListAdapter(this, R.layout.customadapter, searchResult);
            searchResultListView.setAdapter(bookArrayAdapter);
            searchResultListView.setClickable(true);
            bookSearch.searchByTitle(searchValue, searchResult, bookArrayAdapter);

        } else {
            Log.d(TAG, "onCreate: Unexpected type: " + type);
        }



        ListViewClickHandler();

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
}
