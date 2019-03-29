/**
 *
 * Class name: RequestR
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 *
 */
package com.example.ibookit.Model;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.ibookit.Functionality.BookStatusHandler;
import com.example.ibookit.Functionality.NotificationHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * @author Jiazhen Li
 *
 * @version 1.0
 */
public class RequestR {
    private static final String TAG = "RequestR";
    private ArrayList<Request> requestSent = new ArrayList<>();
    private ArrayList<Request> requestReceived;
    private DatabaseReference mDatabase;
    private String username;
    private ArrayList<String> last = new ArrayList<>();
    private String bookTitle;
//    private static Request request1;

    /**
     * Constructor
     */
    public RequestR(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("requestReceived");
    }

    /**
     * Retrive Book from requestReceived
     * @param bookList
     * @param adapter
     */
    public void RetriveBook(final ArrayList<Book> bookList,final ArrayAdapter<Book> adapter) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                last.clear();
                bookList.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {

                    Request request = d.getValue(Request.class);

                    if (!last.contains(request.getBookId())){
                        final DatabaseReference bDatabase = FirebaseDatabase.getInstance().getReference().child("books").child(request.getBookId());
                        bDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Book book = dataSnapshot.getValue(Book.class);
                                bookList.add(book);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        last.add(request.getBookId());
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

    }

    /**
     * For a particular book, retrieve requests for this book
     * @param users
     * @param adapter
     * @param bookname
     */
    public void RequestInBook(final ArrayList<Request> users,final ArrayAdapter<Request> adapter,final String bookname){

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    final Request request1 = d.getValue(Request.class);
                    final DatabaseReference cDatabase = FirebaseDatabase.getInstance().getReference().child("books").child(request1.getBookId());
                    cDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Book book1 = dataSnapshot.getValue(Book.class);
                            bookTitle = book1.getTitle();
                            if (bookTitle.equals(bookname)) {
                                users.add(request1);
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                   });

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }


    /**
     * Owner decline request from sender
     * @param request
     */
    public void decline_request(final Request request){
        mDatabase.child(request.getRid()).child("isAccept").setValue(2);
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("users").child(request.getSender()).child("requestSent");
        database.child(request.getRid()).child("isAccept").setValue(2);

        // Update message
        new NotificationHandler(username, request.getSender()).sendNewMessage("Your request has been declined", "From " + username);
    }

    /**
     * Owner accept request from sender
     * @param Rlist
     * @param request
     */
    public void accept_request(final ArrayList<Request> Rlist, final Request request) {
        mDatabase.child(request.getRid()).child("isAccept").setValue(1);

        // Update book info
        final DatabaseReference dDatabase = FirebaseDatabase.getInstance().getReference();
        dDatabase.child("books").child(request.getBookId()).child("status").setValue(2);
        dDatabase.child("users").child(username).child("ownerShelf").child(request.getBookId()).child("status").setValue(2);

        // Update requestSent
        dDatabase.child("users").child(request.getSender()).child("requestSent").child(request.getRid()).child("isAccept").setValue(1);

        // Update message
        new NotificationHandler(username, request.getSender()).sendNewMessage("Your request has been accepted", "From " + username);

        ArrayList<Request> newlist = new ArrayList<>(Rlist);
        for(Request r :newlist){
            if (r.getRid() != request.getRid()) {
                decline_request(r);
            }
        }
    }

    public void reverse_accepted(final ArrayList<Request> Rlist, final Request request) {
        // Update message
        new NotificationHandler(username, request.getSender()).sendNewMessage("Accept for book has been withdraw", "From " + username);

        final DatabaseReference dDatabase = FirebaseDatabase.getInstance().getReference();
        // Update book info
        dDatabase.child("books").child(request.getBookId()).child("status").setValue(0);
        dDatabase.child("users").child(username).child("ownerShelf").child(request.getBookId()).child("status").setValue(0);

        // remove all request for this book
        for (Request r :Rlist) {
            String sender = r.getSender();
            dDatabase.child("users").child(sender).child("requestSent").child(r.getRid()).removeValue();
            dDatabase.child("users").child(username).child("requestReceived").child(r.getRid()).removeValue();
        }

    }

    /**
     * Methods below are for testing
     * @return
     */
    public ArrayList<Request> getRequestSent() {
        return requestSent;
    }

    public void setRequestSent(ArrayList<Request> requestSent) {
        this.requestSent = requestSent;
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getLast() {
        return last;
    }

    public void setLast(ArrayList<String> last) {
        this.last = last;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public RequestR(String name, ArrayList<Request> requested){
        this.username = name;
        this.requestReceived = requested;

    }

    public ArrayList<Request> requestReceiveList(){
        return this.requestReceived;
    }



}
