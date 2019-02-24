package com.example.ibookit;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.BorrowerShelf;
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

         myBooks = owner.All_books();
         owner.add_book(book);
         assertEquals( owner.All_books(),  myBooks.add(book));

         myBooks.remove(book);
         assertEquals(owner.All_books(), myBooks);



     }

     BorrowerShelf borrower = new BorrowerShelf();

     @Test
     public void BorrowShelf(){

         myBooks = borrower.All_books();
         borrower.add_book(book);
         assertEquals(borrower.All_books(), myBooks.add(book));

         borrower.remove_book(book);
         assertEquals(borrower.All_books(), myBooks.remove(book));
         

     }










}