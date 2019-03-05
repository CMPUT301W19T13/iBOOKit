package com.example.ibookit.View;

import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Toast;

import com.example.ibookit.Functionality.SearchForBook;
import com.example.ibookit.Functionality.SearchForUser;
import com.example.ibookit.Model.User;

import android.widget.ListView;

import com.example.ibookit.ListAdapter.BookListAdapter;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;

import com.example.ibookit.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MyShelfOwnerActivity extends AppCompatActivity {
    public static Context sContext;

    private static final String TAG = "MyShelfOwnerActivity";
    private ListView mListView;
    private ArrayAdapter<Book> adapter;
    private ArrayList<Book> mBooks = new ArrayList<>();
    private OwnerShelf ownerShelf = new OwnerShelf();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshelf_mybook);

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

        setBottomNavigationView();

        ListViewClickHandler();


    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new BookListAdapter(this, R.layout.customadapter, mBooks);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
        ownerShelf.SyncBookShelf(mBooks, adapter);

    }

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


    private void ListViewClickHandler () {
        final ListView finalList = mListView;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) finalList.getItemAtPosition(position);

                Intent intent = new Intent(MyShelfOwnerActivity.this, ViewBookInfoActivity.class);
                Gson gson = new Gson();

                String out = gson.toJson(book);

                intent.putExtra("book", out);
                startActivity(intent);

            }
        });
    }
}
