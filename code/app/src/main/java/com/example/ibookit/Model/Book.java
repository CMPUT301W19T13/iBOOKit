/**
 * Class name: Book
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Model;

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class Book {
    private String id;
    private String isbn;
    private String title;
    private String author;
    private String description;
    //9 categories
    private String category;
    // 4 status: available -> 0, requested -> 1, accepted -> 2, borrowed -> 3
    private int status;
    private String owner;
    private String currentBorrower;
    private String imageURL;
    private int borrowingStatus;



    /**
     * Constructor
     *
     * @param isbn
     * @param title
     * @param author
     * @param description
     * @param category
     * @param owner
     */
    public Book(String isbn, String title, String author, String description, String category, String owner) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.status = 0;
        this.currentBorrower = "";
        this.borrowingStatus = 0;
    }

    /**
     * Default constructor
     */
    public Book() {}


    /**
     *
     * Getter and Setter
     *
     */
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getBorrowingStatus() {
        return borrowingStatus;
    }

    public void setBorrowingStatus(int borrowingStatus) {
        this.borrowingStatus = borrowingStatus;
    }
}

