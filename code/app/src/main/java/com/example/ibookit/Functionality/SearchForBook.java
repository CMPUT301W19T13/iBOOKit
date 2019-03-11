package com.example.ibookit.Functionality;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
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
    private String mTitle;
    private ArrayList<Book> result = new ArrayList<>();

    public SearchForBook(String keyword){
        this.keyword = keyword;

    }
    public SearchForBook(){}
//&& (d.child("status").getValue().toString()=="0")
    //todo:searches cannot handle more than one word at present

//    @Override
    //searchByKeyword is not updated until needed
    public void searchByKeyword(final String mKeyword, final ArrayList<Book> result, final ArrayAdapter<Book> adapter)  {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listBook = bookRef.orderByChild("status").equalTo(0);

        //todo: this is just a temporary solution, I will be looking for more advanced solution(like filtering),
        //I will use this solution just for demoing for part 4
        listBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot d : dataSnapshot.getChildren()){
                        if ((d.child("title").getValue().toString().contains
                                (mKeyword.replaceAll("\\s+","")))){
                            Book temp = d.getValue(Book.class);
                            result.add(temp);
                            adapter.notifyDataSetChanged();

                        }else if ((d.child("author").getValue().toString().contains
                                (mKeyword.replaceAll("\\s+","")))){
                            Book temp = d.getValue(Book.class);
                            result.add(temp);
                            adapter.notifyDataSetChanged();
                        }else if ((d.child("isbn").getValue().toString().contains
                                (mKeyword.replaceAll("\\s+","")))){
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

    public void searchByTitle(final String mTitle, final ArrayList<Book> result, final ArrayAdapter<Book> adapter) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference bookRef = database.getReference("books");
        Query listBook = bookRef.orderByChild("status").equalTo(0);
        //todo: this is just a temporary solution, I will be looking for more advanced solution(like filtering),
        //I will use this solution just for demoing for part 4
        listBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot d : dataSnapshot.getChildren()){
                        if (d.child("title").getValue().toString().toLowerCase().contains
                                (mTitle.replaceAll("\\s+","").toLowerCase())){
//                            String a = d.child("status").getValue().toString().getClass().getSimpleName();
//                            Toast.makeText(HomeSearchActivity.sContext, a,
//                                    Toast.LENGTH_SHORT).show();

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

    //planning to do buttons for search by category so did not add like functionality
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
//                        Toast.makeText(HomeSearchActivity.sContext, Boolean.toString(d.child("status").getValue().equals(0)),
//                                    Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //searchByAuthor is not updated until needed
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
