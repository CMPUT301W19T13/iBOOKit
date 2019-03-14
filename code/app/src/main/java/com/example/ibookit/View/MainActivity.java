/**
 * Class name: MainActivity
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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ibookit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmail, mPassword;
    private ProgressBar progressBar;
    private Button logInButton;

    /**
     * Main page and let user for sign in
     * @param savedInstanceState
     *
     * https://firebase.google.com/docs/auth/android/start
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar_login);
        logInButton = findViewById(R.id.logInButton);

        progressBar.setVisibility(View.INVISIBLE);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmail = findViewById(R.id.emailEnter);
                mPassword = findViewById(R.id.password_enter);

                Log.d(TAG, "onClick: " + mEmail.getText().toString());
                Log.d(TAG, "onClick: " + mPassword.getText().toString());

                progressBar.setVisibility(View.VISIBLE);

                // get email and password and send it to fireBase Auth
                if ((!mEmail.getText().toString().isEmpty()) && (!mPassword.getText().toString().isEmpty())) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(MainActivity.this, "Login successful",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Login fail",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });


                } else {
                    Log.d(TAG, "onClick: Empty");
                    Toast.makeText(MainActivity.this, "Cannot leave empty",
                            Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        setupFirebaseAuth();

        final Button signUpButton = (Button) findViewById(R.id.signUp_Main);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * AddAuthStateListener when activity starts
     */
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);

    }

    /**
     * RemoveAuthStateListener when activity ends
     */
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    /**
     * Set up AuthStateListener
     */
    private void setupFirebaseAuth() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged: " + user);
                    Intent intent = new Intent(MainActivity.this, HomeSearchActivity.class);
                    startActivity(intent);
                } else {
                    Log.d(TAG, "onAuthStateChanged: Fail");
                }
            }
        };

    }




}
