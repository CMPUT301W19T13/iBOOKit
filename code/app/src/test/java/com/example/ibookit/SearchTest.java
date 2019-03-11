package com.example.ibookit;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SearchTest {


    Book book = new Book();

    public ArrayList<User> resultsUser;
    public ArrayList<User> testUser;
    public ArrayList<Book> resultsBook;
    public ArrayList<User> testBook;

    // set testbook and testUser to the correct arraylist

    Search search = new Search();



    @Test
    public void testSearch(){
         String test = "testID";
        resultsUser = search.getUserId(test);
        assertEquals(resultsUser, testUser);

        resultsBook = search.getBooks(test);
        assertEquals(resultsBook, testBook);



    }














}