/**
 * Class name: BookReturnTest
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;

import org.junit.Test;
import org.junit.Before;

import java.security.acl.Owner;

import static org.junit.Assert.*;

/**
 * @author Joe Xu
 *
 * @version 1.0
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