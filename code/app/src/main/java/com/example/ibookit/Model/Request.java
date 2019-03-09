package com.example.ibookit.Model;

import android.location.Location;


// If a request is declined then delete the corresponding request object

public class Request {

    private String rid;
    // 0: not decided;   1: accepted;    2:declined
    private int isAccept;
    private String sender;
    private String receiver;
    private String bookId;
    private Location geolocation;

    public Request () {}

    public Request(Book book) {
        this.receiver = book.getOwner();
        this.isAccept = 0;
        this.bookId = book.getId();

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

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }



    public Location getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Location geolocation) {
        this.geolocation = geolocation;
    }



}
