/**
 * Class name: OwnerShelfTest
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.ibookit.Database.Book;
import com.example.ibookit.Database.OwnerShelf;
import com.google.firebase.FirebaseApp;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
/**
 * @author zisen zhou
 *
 * @version 1.0
 */

@RunWith(AndroidJUnit4.class)
public class OwnerShelfTest {
    private Context instrumentationCtx;
    private OwnerShelf testShelf = new OwnerShelf();

    @Test
    public void test(){
        instrumentationCtx = InstrumentationRegistry.getContext();
        FirebaseApp.initializeApp(instrumentationCtx);

        //test All_Books method in ownershelf
        ArrayList<Book> expected= new ArrayList<>();
        assertEquals(expected, testShelf.All_books());

    }

}
