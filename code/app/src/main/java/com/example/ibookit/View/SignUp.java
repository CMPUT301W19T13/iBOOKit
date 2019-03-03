package com.example.ibookit.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD

=======
>>>>>>> master
import com.example.ibookit.Model.User;
import com.example.ibookit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
<<<<<<< HEAD
    private TextView mName, mEmail, mPassword, mPhone, mUsername;
=======
    private TextView mEmail, mPassword, mPhone, mUsername;
>>>>>>> master
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //get R.id
<<<<<<< HEAD
        mName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email_SignUp);
        mPassword = findViewById(R.id.password_SignUp);
        mPhone = findViewById(R.id.phone_SignUp);
        mUsername = findViewById(R.id.username_SignUp);
=======
        mEmail = findViewById(R.id.email_SignUp);
        mPassword = findViewById(R.id.password_SignUp);
        mPhone = findViewById(R.id.phone_SignUp);
        mUsername = findViewById(R.id.userName_SignUp);
>>>>>>> master


        Button signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                String name = mName.getText().toString();
=======
>>>>>>> master
                final String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                final String phone = mPhone.getText().toString();
                final String username = mUsername.getText().toString();


                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
<<<<<<< HEAD
                            Log.d(TAG, "createUserWithEmail:success");
=======
                            Toast.makeText(SignUp.this, "SignUp successful.",
                                    Toast.LENGTH_SHORT).show();

                            Log.d(TAG, "createUserWithEmail:success");

>>>>>>> master
                            FirebaseUser user = mAuth.getCurrentUser();
                            setUserInfo(user.getUid(), username, email, phone);

                            Intent intent = new Intent(SignUp.this, userProfile.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }


    // Create new user into FireBase
    private void setUserInfo(String id, String username, String email, String phone) {
        User user = new User(id, username, email, phone);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(username).setValue(user);
    }


<<<<<<< HEAD

=======
>>>>>>> master
}
