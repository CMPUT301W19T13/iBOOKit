/**
 * Class name: Request
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 *
 */
package com.example.ibookit.Model;
import android.location.Location;

/**
 * @author joe
 *
 * @version 1.0
 */
public class Request {

    private String rid;
    // 0: not accepted;   1: accepted
    private int isAccept;
    private String sender;
    private String receiver;
    private String bookId;
    private Location geolocation;

    /**
     * Constructor
     */
    public Request () {}

    public Request(Book book) {
        this.receiver = book.getOwner();
        this.isAccept = 0;
        this.bookId = book.getId();

    }

    /**
     * Getter and Setter
     */
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
