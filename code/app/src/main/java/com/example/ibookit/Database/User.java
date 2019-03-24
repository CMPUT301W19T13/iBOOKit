/**
 * Class name: User
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 *
 */
package com.example.ibookit.Database;

/**
 * @author zijunwu
 *
 * @version 1.0
 */
public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String id;
    private String imageURL;

    /**
     * Constructor
     * @param id
     * @param username
     * @param email
     * @param phoneNumber
     */
    public User(String id, String username, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructor
     * @param name
     */
    public User(String name){
        this.username = name;
    }

    /**
     * Default constructor
     */
    public User() {}

    /**
     * Getter and Setter
     */
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
