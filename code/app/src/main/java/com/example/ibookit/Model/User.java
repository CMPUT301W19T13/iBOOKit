package com.example.ibookit.Model;

import com.example.ibookit.Functionality.Request;

public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String id;

    public User(String id, String username, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

<<<<<<< HEAD
    public User() {}

=======
>>>>>>> master
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

<<<<<<< HEAD
=======

>>>>>>> master
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
