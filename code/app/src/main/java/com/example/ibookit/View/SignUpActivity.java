/**
 * Class name: SignUpActivity
 *
 * version 1.1
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

import com.example.ibookit.Functionality.RecommendationHandler;
import com.example.ibookit.Model.User;
import com.example.ibookit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * @author zijun wu
 *
 * @version 1.1
 */

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private EditText mEmail, mPassword, mPhone, mUsername;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Boolean check_email = false;

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

        // Initialize FireBase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //get R.id
        mEmail = findViewById(R.id.email_SignUp);
        mPassword = findViewById(R.id.password_SignUp);
        mPhone = findViewById(R.id.phone_SignUp);
        mUsername = findViewById(R.id.userName_SignUp);
        progressBar = findViewById(R.id.progressBar_signUp);

        progressBar.setVisibility(View.INVISIBLE);

        mEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (mEmail.getText().toString().trim().matches(emailPattern) && mEmail.getText().toString().trim().length() > 0){
                    check_email = true;
                }
                else{
                    mEmail.setError("Invalid Email Address");
                    check_email = false;
                }

            }
        });


        Button signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                final String phone = mPhone.getText().toString();
                final String username = mUsername.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

                if (! check_email || username.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Something wrong",
                            Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }

                checkUserExist(username, email, password, phone);

            }
        });

        Button signIn = findViewById(R.id.signUp_SignIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivity = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
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

        mDatabase.child("users").child(username).setValue(user);

        new RecommendationHandler(username).CreateNewRecommendation();
    }

    /**
     * check if the username already exists in database
     *
     * reference: https://stackoverflow.com/questions/39053248/how-to-search-if-a-username-exist-in-the-given-firebase-database
     *
     * @param username
     * @return
     */
    private void checkUserExist(final String username, final String email, final String password, final String phone){
        mDatabase.child("users").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(SignUpActivity.this, "Username already exists",
                            Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    createAccount(username, email, password, phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Create an account with email and password
     *
     * @param username
     * @param email
     * @param password
     * @param phone
     */
    private void createAccount(final String username, final String email, final String password, final String phone) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Log.d(TAG, "createUserWithEmail:success");

                    final FirebaseUser user = mAuth.getCurrentUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: Username updated.");

                                setUserInfo(user.getUid(), username, email, phone);

                                Toast.makeText(SignUpActivity.this, "Sign up successful",
                                        Toast.LENGTH_SHORT).show();

                                progressBar.setVisibility(View.INVISIBLE);

                                Intent intent = new Intent(SignUpActivity.this, PreferenceActivity.class);
                                startActivity(intent);
                            }
                        }
                    });


                } else {
                    Toast.makeText(SignUpActivity.this, "Email already exists",
                            Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
