package com.example.ibookit.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;

public class userProfile extends AppCompatActivity {

    private static final String TAG = "UserProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Button check = (Button) findViewById(R.id.contactInfo_user);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userProfile.this, contactInformation.class);
                startActivity(intent);
            }
        });



        final Button signout = findViewById(R.id.signOut_profile);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
                Intent intent = new Intent(userProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signout() {
        Log.d(TAG, "signout: ");
        FirebaseAuth.getInstance().signOut();
    }
}
