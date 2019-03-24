/**
 * Class name: SearchForBook
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Functionality;

import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.ibookit.Model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zisen
 *
 * @version 1.0
 *
 */
public class SearchForBook {
    public ArrayList<Book> mResult;
    public SearchForBook(){}

    /**
     * Search the book by keywords
     * and put the result into ListView
     *
     * reference: https://firebase.google.com/docs/database/admin/retrieve-data
     *
     * @param mListKeyword
     * @param result
     * @param adapter
     */
    public void searchByKeyword(final String[] mListKeyword, final ArrayList<Book> result, final ArrayAdapter<Book> adapter)  {
        final Set<String> nonDupID = new HashSet<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listBook = bookRef.orderByChild("status").startAt(0).endAt(1);

        listBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (String mKeyword: mListKeyword ){
                        for (DataSnapshot d : dataSnapshot.getChildren()){
                            if (d.child("title").getValue().toString().toLowerCase().contains
                                    (mKeyword)){
                                nonDupID.add(d.getKey());
                            }else if (d.child("author").getValue().toString().toLowerCase().contains
                                    (mKeyword)){
                                nonDupID.add(d.getKey());
                            }else if (d.child("isbn").getValue().toString().toLowerCase().contains
                                    (mKeyword)){
                                nonDupID.add(d.getKey());
                            }
                            else if (d.child("description").getValue().toString().toLowerCase().contains
                                    (mKeyword)){
                                nonDupID.add(d.getKey());
                            }
                        }
                    }
                    result.clear();
                    adapter.notifyDataSetChanged();
                    for (String id: nonDupID){
                       Book temp =  dataSnapshot.child(id).getValue(Book.class);
                       result.add(temp);
                    }
                    adapter.notifyDataSetChanged();

                    // update recommendation
                    UpdateRecommedationKeywords(result);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /**
     * Search the book by categories
     * and put the result into ListView
     *
     * @param mCategory
     * @param result
     * @param adapter
     */
    public void searchByCategory(final String mCategory, final ArrayList<Book> result, final ArrayAdapter<Book> adapter){
        // update recommendation
        UpdateRecommendationCategory(mCategory);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listBook = bookRef.orderByChild("category").equalTo(mCategory);
        listBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    result.clear();
                    adapter.notifyDataSetChanged();
                    for (DataSnapshot d : dataSnapshot.getChildren()){
                        if (d.child("status").getValue().toString().equals("0")){
                            Book temp = d.getValue(Book.class);
                            result.add(temp);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void UpdateRecommedationKeywords(ArrayList<Book> books) {
        ArrayList<String> categories = new ArrayList<>();
        for (Book book: books) {
            if (!categories.contains(book.getCategory())) {
                categories.add(book.getCategory());
            }
        }

        new RecommendationHandler().UpdateRecommendation(categories.toArray(new String[0]), false);
    }

    private void UpdateRecommendationCategory(String category) {
        String[] categories = new String[1];
        categories[0] = category;
        new RecommendationHandler().UpdateRecommendation(categories, false);
    }

}
