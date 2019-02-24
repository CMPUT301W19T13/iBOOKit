package com.example.ibookit.Model;

import java.util.ArrayList;

public class OwnerShelf {



    private ArrayList<Book> myBooks;

    public ArrayList<Book> All_books(){
        //returns all books that you own

        return myBooks;
    }



    public void add_book(Book aBook){
        myBooks.add(aBook);
    }
    public void remove_book(Book dBook){
        myBooks.remove(dBook);
    }


}
