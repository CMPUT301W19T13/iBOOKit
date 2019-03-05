package com.example.ibookit.Functionality;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.ibookit.Model.Book;
import com.example.ibookit.View.HomeSearchActivity;
import com.example.ibookit.View.MyShelfOwnerActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchForBook implements Search {
    private String keyword;
    private ArrayList<Book> result = new ArrayList<>();

    public SearchForBook(String keyword){
        this.keyword = keyword;

    }
    public SearchForBook(){}


    @Override
    public ArrayList searchByKeyword(String keyword) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");

        Query listUser = bookRef.orderByChild("title").equalTo(keyword);
        listUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        String author = d.child("author").getValue().toString();
                        String title = d.child("title").getValue().toString();
                        Toast.makeText(HomeSearchActivity.sContext, title + ":" + author,
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(HomeSearchActivity.sContext, "booknot found",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return result;
    }

    public ArrayList searchByTitle(String title) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listUser = bookRef.orderByChild("title").equalTo(title);
        listUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        String author = d.child("author").getValue().toString();
                        String title = d.child("title").getValue().toString();
                        Toast.makeText(HomeSearchActivity.sContext, title + ":" + author,
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                        Toast.makeText(HomeSearchActivity.sContext, "book not found",
                                Toast.LENGTH_SHORT).show();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return result;
    }


    public ArrayList searchByAuthor(String author) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listUser = bookRef.orderByChild("author").equalTo(author);
        listUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        String author = d.child("author").getValue().toString();
                        String title = d.child("title").getValue().toString();
                        Toast.makeText(HomeSearchActivity.sContext, title + ":" + author,
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(HomeSearchActivity.sContext, "book not found",
                            Toast.LENGTH_SHORT).show();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return result;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<Book> getResult() {
        return result;
    }

}
