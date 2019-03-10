package com.example.ibookit.Functionality;

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

public class SearchForBook {
    public ArrayList<Book> mResult;
    public SearchForBook(){}

    public void searchByKeyword(final String[] mListKeyword, final ArrayList<Book> result, final ArrayAdapter<Book> adapter)  {
        final Set<String> nonDupID = new HashSet<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listBook = bookRef.orderByChild("status").equalTo(0);

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
                    for (String id: nonDupID){
                       Book temp =  dataSnapshot.child(id).getValue(Book.class);
                       result.add(temp);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void searchByCategory(final String mCategory, final ArrayList<Book> result, final ArrayAdapter<Book> adapter){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listBook = bookRef.orderByChild("category").equalTo(mCategory);
        listBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
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
    public ArrayList<Book> getKeyword() {
        return mResult;
    }

}
