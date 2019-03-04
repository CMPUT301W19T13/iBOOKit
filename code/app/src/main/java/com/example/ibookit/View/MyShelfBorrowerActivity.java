package com.example.ibookit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ibookit.R;

public class MyShelfBorrowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshelf_borrowed);

        Button changeShelf = findViewById(R.id.my_book);
        changeShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyShelfBorrowerActivity.this, MyShelfOwnerActivity.class);
                startActivity(intent);
            }
        });

        setBottomNavigationView();
    }


    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent add = new Intent(MyShelfBorrowerActivity.this, AddBookAsOwnerActivity.class);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(MyShelfBorrowerActivity.this, HomeSearchActivity.class);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:

                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(MyShelfBorrowerActivity.this, UserProfileActivity.class);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        Intent request = new Intent(MyShelfBorrowerActivity.this, CheckRequestsActivity.class);
                        startActivity(request);

                        break;
                }

                return false;
            }
        });
    }
}
