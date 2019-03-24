/**
 * Class name: BookShelf Interface
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Database;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public interface BookShelf {

    public ArrayList<Book> All_books();

    public void SyncBookShelf(ArrayList<Book> books, ArrayAdapter<Book> adapter, Integer status);

    public void add_book(Book book);

    public void remove_book(Book book);

}
