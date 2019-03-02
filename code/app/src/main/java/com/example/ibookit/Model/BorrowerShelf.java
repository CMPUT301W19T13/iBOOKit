package com.example.ibookit.Model;

import java.util.ArrayList;

public class BorrowerShelf {
    private ArrayList<Book> BorrowBooks;

    Book book = new Book();

    public ArrayList<Book> All_books(){
        //returns all books that you are borrowing
        return BorrowBooks;
    }

    public void add_book(Book book){



    }
    public void remove_book(Book dBook){
        BorrowBooks.remove(dBook);
    }


}
