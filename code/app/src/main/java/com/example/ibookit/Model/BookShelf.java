package com.example.ibookit.Model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public interface BookShelf {

    public ArrayList<Book> All_books();

    public void SyncBookShelf(ArrayList<Book> books, ArrayAdapter<Book> adapter, Integer status);

    public void add_book(Book book);

    public void remove_book(Book book);

}
