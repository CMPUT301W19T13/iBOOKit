package com.example.ibookit.Functionality;

import android.location.Location;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;


// If a request is declined then delete the corresponding request object

public class Request {

    private int Rid;
    private int isAccept;
    private String borrowerId;
    private String ownerId;
    private String bookId;
    private Location geolocation;

    public void requestBorrow(String borrower, String owner, String book ) {

        setborrowerId(borrower);
        setOwnerId(owner);
        setBookId(book);
    }


    public int getRid() {
        return Rid;
}

    public void setRid(int rid) {
        Rid = rid;
    }

    public int getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(int isAccept) {
        this.isAccept = isAccept;
    }

    public String getborrowerId() {
        return borrowerId;
    }

    public void setborrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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
