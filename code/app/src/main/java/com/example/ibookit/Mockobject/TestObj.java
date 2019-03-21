package com.example.ibookit.Mockobject;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestObj extends AppCompatActivity {

    public void TestObj(){




    }

    public void function(){
        FirebaseApp.initializeApp(this);
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();


        System.out.println("hi");
    }
}
