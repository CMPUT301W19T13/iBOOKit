package com.example.ibookit.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ibookit.Functionality.SearchForBook;
import com.example.ibookit.Functionality.SearchForUser;
import com.example.ibookit.Model.User;
import com.example.ibookit.R;

import java.util.ArrayList;

public class MyShelfOwnerActivity extends AppCompatActivity {
    public static Context sContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshelf_mybook);


        Button changeShelf = findViewById(R.id.borrowed);
        changeShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyShelfOwnerActivity.this, MyShelfBorrowerActivity.class);
                startActivity(intent);
            }
        });
//        //this is used for testing search
//        sContext = MyShelfOwnerActivity.this;
//        Button testSearch = findViewById(R.id.borrowed);
//        testSearch.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                SearchForUser a = new SearchForUser("hello1");
//                SearchForBook b = new SearchForBook("BOOk1");
////                a.searchByKeyword();
//                b.searchByKeyword();
//            }
//        });



        setBottomNavigationView();


    }

    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        break;

                    case R.id.action_home:
                        break;

                    case R.id.action_myshelf:
                        break;

                    case R.id.action_profile:
                        Intent intent4 = new Intent(MyShelfOwnerActivity.this, UserProfileActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.action_request:
                        break;
                }


                return false;
            }
        });
    }
}
