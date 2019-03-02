package com.example.ibookit.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OwnerShelf {

    private DatabaseReference mDatabase;


    private ArrayList<Book> myBooks;

    public ArrayList<Book> All_books(){
        //returns all books that you own

        return myBooks;
    }



    public void add_book(Book aBook){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        myBooks.add(aBook);
    }
    public void remove_book(Book dBook){
        myBooks.remove(dBook);
    }


}
