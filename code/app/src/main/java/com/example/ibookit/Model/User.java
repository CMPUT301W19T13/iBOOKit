package com.example.ibookit.Model;

import com.example.ibookit.Functionality.Request;

public class User {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String id;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public String requestBook(){

        Request request = new Request();
        String otherUser = request.requestBorrow(this.id);

        return otherUser;

    }
}
