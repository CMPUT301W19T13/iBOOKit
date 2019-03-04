package com.example.ibookit.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ibookit.R;

public class CheckRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        setBottomNavigationView();
    }

    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent add = new Intent(CheckRequestsActivity.this, AddBookAsOwnerActivity.class);
                        add.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(CheckRequestsActivity.this, HomeSearchActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(CheckRequestsActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(CheckRequestsActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        break;
                }

                return false;
            }
        });
    }
}