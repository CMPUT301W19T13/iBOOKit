package com.example.ibookit.View;

import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setBottomNavigationView();


        Button check = (Button) findViewById(R.id.contactInfo_user);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, ContactInformationActivity.class);
                startActivity(intent);
            }
        });

        //edit button added to allow edit on user profile

        Button edit =  findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_profile();
                Intent intent = new Intent(UserProfileActivity.this, ContactInformationActivity.class);
                startActivity(intent);
            }
        });



        final Button signout = findViewById(R.id.signout);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void edit_profile() {
        //
        //
        //

    }



    private void signout() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, "signout: " + user.getDisplayName());
        FirebaseAuth.getInstance().signOut();
    }


    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent add = new Intent(UserProfileActivity.this, AddBookAsOwnerActivity.class);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(UserProfileActivity.this, HomeSearchActivity.class);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(UserProfileActivity.this, MyShelfOwnerActivity.class);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        break;

                    case R.id.action_request:
                        Intent request = new Intent(UserProfileActivity.this, CheckRequestsActivity.class);
                        startActivity(request);

                        break;
                }

                return false;
            }
        });
    }



}
