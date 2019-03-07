package com.example.ibookit.Functionality;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class SortedRequestsList {


    private Book book;
    private ArrayList<User> BorrowUsers;
    private DatabaseReference mDatabase;
    

    public void setBorrowUsers(ArrayList<User> borrowUsers) {
        BorrowUsers = borrowUsers;
    }



    public void ShowBook(){};
    public void ShowRequester(){};
    public void AcceptRequest(){};
    public void DeclineReqeust(){};










}
