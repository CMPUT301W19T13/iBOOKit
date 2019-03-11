package com.example.ibookit.Functionality;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SortedRequestsList {


    private Book book;
    private ArrayList<User> BorrowUsers;
    private DatabaseReference mDatabase;
    private String username;

    public SortedRequestsList(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("RequestReceived");
    }


    public void ShowBook(){

    };
    public void ShowRequester(){};
    public void AcceptRequest(){};
    public void DeclineReqeust(){};










}
