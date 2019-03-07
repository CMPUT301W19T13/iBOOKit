package com.example.ibookit.Functionality;

import android.support.annotation.NonNull;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RequestsList {


    private Book book;
    private ArrayList<User> BorrowUsers;
    private DatabaseReference mDatabase;


    

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ArrayList<User> getBorrowUsers() {

        return BorrowUsers;
    }

    public void setBorrowUsers(ArrayList<User> borrowUsers) {
        BorrowUsers = borrowUsers;
    }



    public void ShowBook(){};
    public void ShowRequester(){};
    public void AcceptRequest(){};
    public void DeclineReqeust(){};










}
