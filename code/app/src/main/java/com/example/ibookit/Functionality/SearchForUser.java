package com.example.ibookit.Functionality;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.ibookit.Model.User;
import com.example.ibookit.View.MainActivity;
import com.example.ibookit.View.MyShelfOwnerActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchForUser implements Search {
    private String keyword;
    private ArrayList<User> result = new ArrayList<User>();
    public SearchForUser(String keyword){
        this.keyword = keyword;
    }
    public SearchForUser(){}
    @Override
    public ArrayList searchByKeyword(String keyword) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users");

        Query listUser = userRef.orderByChild("username").equalTo(keyword);
        listUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()){
                    String email = d.child("email").getValue().toString();
                    String username = d.child("username").getValue().toString();
                    Toast.makeText(MyShelfOwnerActivity.sContext, username+":"+email,
                            Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return result;
    }



    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<User> getResult() {
        return result;
    }

}
