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

import com.google.android.gms.maps.model.LatLng;

/**
 * @author joe
 *
 * @version 1.0
 */
public class Request {

    private String rid;
    // 0: not decided;   1: accepted;    2:declined
    private int isAccept;
    private String sender;
    private String receiver;
    private String bookId;
    private Double lat;
    private Double lon;

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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
