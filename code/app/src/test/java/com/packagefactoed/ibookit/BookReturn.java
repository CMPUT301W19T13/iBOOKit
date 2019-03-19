package com.packagefactoed.ibookit;

import com.packagefactoed.ibookit.Model.Book;

import org.junit.Test;

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

        book.setStatus(1);
        assertEquals(1,book.getStatus());

}}