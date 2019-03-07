package com.example.ibookit.Functionality;

import android.widget.ArrayAdapter;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;

import java.util.ArrayList;

public interface Search {

    void searchByKeyword(final String mKeyword, final ArrayList<Book> result, final ArrayAdapter<Book> adapter);
//    ArrayList searchByName();



}
