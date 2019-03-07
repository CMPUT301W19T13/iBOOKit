package com.example.ibookit.Functionality;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;

import java.util.ArrayList;

public class RequestsList {


    private Book book;
    private ArrayList<User> BorrowUsers;

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
