package com.example.ibookit;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;
import com.example.ibookit.Model.User;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ShelfTests {

     private ArrayList<Book> myBooks;
     OwnerShelf owner = new OwnerShelf();
     Book book = new Book();


     @Test
     public void OwnerShelf(){

         owner.putBook(book);

         myBooks = owner.All_books();
         assertEquals( myBooks,  book);



     }










}