package com.example.ibookit.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String id;
    private String isbn;
    private String title;
    private String author;
    //9 categories
    private String category;
    // 4 status: available -> 0, requested -> 1, accepted -> 2, borrowed -> 3
    private int status;
    private String owner;
    private String currentBorrower;

    public Book(String isbn, String title, String author, String category, String owner) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.owner = owner;
        this.status = 0;
        this.currentBorrower = "";

    }

    public Book() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCurrentBorrower() {
        return currentBorrower;
    }

    public void setCurrentBorrower(String currentBorrower) {
        this.currentBorrower = currentBorrower;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isbn);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(category);
        dest.writeString(owner);
        /*TODO: the current structure does not allow passing current borrower and status
        which can cause trouble in the future
        */
        dest.writeInt(status);
        dest.writeString(currentBorrower);
    }
    public final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {

            return new Book(source.readString(), source.readString(), source.readString(),
                    source.readString(), source.readString());
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}

