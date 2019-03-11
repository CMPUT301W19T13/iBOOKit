package com.example.ibookit;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.ibookit.Functionality.SearchForUser;
import com.example.ibookit.ListAdapter.UserListAdapter;
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
    private ArrayAdapter<Book> bookArrayAdapter;
    private ArrayAdapter<User> userArrayAdapter;



    Book book = new Book();

    public ArrayList<User> resultsUser;
    public ArrayList<User> testUser;
    public ArrayList<Book> resultsBook;
    public ArrayList<User> testBook;

    // set testbook and testUser to the correct arraylist

    //Search search = new Search();


    @Test
    public void testSearch(){
        //user search test
        SearchForUser userSearch = new SearchForUser();
        ArrayList<User> searchResult= new ArrayList<>();

//        userArrayAdapter = new UserListAdapter(this, R.layout.adapter_user, searchResult);
        //userSearch.searchByKeyword(searchValue, searchResult, userArrayAdapter);
        ArrayList<User> testResult = userSearch.getResult();

       // assertEquals(testResult, owner.getId());


    }














}