/**
 * Class name: SignUpActivity
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * @author zijun wu
 *
 * @version 1.0
 */

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private TextView mEmail, mPassword, mPhone, mUsername;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;

    /**
     * Let user to sign up in UI
     * @param savedInstanceState
     *
     * reference: https://firebase.google.com/docs/auth/android/start
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //get R.id
        mEmail = findViewById(R.id.email_SignUp);
        mPassword = findViewById(R.id.password_SignUp);
        mPhone = findViewById(R.id.phone_SignUp);
        mUsername = findViewById(R.id.userName_SignUp);
        progressBar = findViewById(R.id.progressBar_signUp);

        progressBar.setVisibility(View.INVISIBLE);


        Button signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                final String phone = mPhone.getText().toString();
                final String username = mUsername.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

                if (email.isEmpty() || username.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Cannot leave empty",
                            Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                // Create a account with email and password
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "createUserWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "onComplete: Username updated.");
                                    }
                                }
                            });

                            setUserInfo(user.getUid(), username, email, phone);

                            Toast.makeText(SignUpActivity.this, "SignUpActivity successful.",
                                    Toast.LENGTH_SHORT).show();
                            
                            progressBar.setVisibility(View.INVISIBLE);

                            Intent intent = new Intent(SignUpActivity.this, HomeSearchActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });

    }

    /**
     * Create new user into FireBase
     *
     * @param id
     * @param username
     * @param email
     * @param phone
     */
    private void setUserInfo(String id, String username, String email, String phone) {
        User user = new User(id, username, email, phone);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(username).setValue(user);
    }

}
