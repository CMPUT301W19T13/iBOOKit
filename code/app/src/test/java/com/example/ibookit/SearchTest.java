package com.example.ibookit;

import com.example.ibookit.Functionality.SearchUser;
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

    public ArrayList<User> results;

    SearchUser search = new SearchUser();



    @Test
    public void testSearch(){
         String test = "testID";
        results = search.getUserId(test);
        assertEquals(results, "NULL");

    }












}