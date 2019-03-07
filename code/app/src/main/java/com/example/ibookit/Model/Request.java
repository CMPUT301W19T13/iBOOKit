package com.example.ibookit.Model;

import android.location.Location;


// If a request is declined then delete the corresponding request object

public class Request {

    private String rid;
    // 0: not accepted;   1: accepted
    private int isAccept;
    private String sender;
    private String receiver;
    private Book book;
    private Location geolocation;

    public Request(Book book) {
        this.receiver = book.getOwner();
        this.isAccept = 0;
        this.book = book;

    }


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(int isAccept) {
        this.isAccept = isAccept;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Location getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Location geolocation) {
        this.geolocation = geolocation;
    }



}
