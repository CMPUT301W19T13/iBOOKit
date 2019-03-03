package com.example.ibookit.Model;

import com.example.ibookit.Functionality.Request;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String id;
    private ArrayList<Book> ownerShelf;
    private ArrayList<Book> borrowerShelf;

    public User(String id, String username, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    // sends a request with a given user id
    public void requestBook(String borrowerId, String OwnerId, String bookId ){

        Request request = new Request(borrowerId, OwnerId, bookId);


    }
}
