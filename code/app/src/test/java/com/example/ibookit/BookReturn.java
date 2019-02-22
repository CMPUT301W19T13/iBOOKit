package com.example.ibookit;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;

import org.junit.Test;
import org.junit.Before;

import java.security.acl.Owner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BookReturn {



    Book book = new Book();

    @Test
    public void ReturnBookinfo(){

        book.setIsbn("TestIsbn");
        assertEquals("TestIsbn",book.getIsbn());


        book.setAuthor("TestAuthor");
        assertEquals("TestAuthor",book.getAuthor());

        book.setTitle("testtitle");
        assertEquals("testtitle", book.getTitle());

        book.setCategory("testcat");
        assertEquals("testcat", book.getCategory());

        book.setStatus(2);
        assertEquals(1,book.getStatus());




    }

    // User user = new User (...)
    @Test
    public void ReturnUserInfo(){
        // see if the user can returns all of its correct states
    }
    // OwnerShelf Shelf = new Shelf(...)
    // BorrowerShelf BShelf = new BShelf(...)
    @Test
    public void ReturnShelf() {
        // see if user and owner shelfs can return its states

        }


    // Scan returns Strings of name isbn
    @Test
    public void Scan(String name, String ISBN){
        //// test to see if the scanned name and ISBN correctly
    }

    // get array list of users and books in the database
    @Test
    public void Search(){
        /// search for books and users, see if the books and users returned matches the ones in
        // the database
    }






}