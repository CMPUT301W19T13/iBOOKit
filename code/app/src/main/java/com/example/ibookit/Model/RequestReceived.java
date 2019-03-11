/**
 *
 * Class name: RequestReceived
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * @author Joe, Jiazhen Li
 *
 * @version 1.0
 */
public class RequestReceived {
    private static final String TAG = "RequestReceived";
    private ArrayList<Request> requestSent = new ArrayList<>();
    private ArrayList<Request> requestReceived;
    private DatabaseReference mDatabase;
    private String username;
    private ArrayList<String> last = new ArrayList<>();
    private String bookTitle;
    private static Request request1;

    /**
     * Constructor
     */
    public RequestReceived(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("requestReceived");
    }

    /**
     * Retrive Book from requestReceived
     * @param bookList
     * @param adapter
     */
    public void RetriveBook(final ArrayList<String> bookList,final ArrayAdapter<String> adapter) {
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
                        bDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Book book = dataSnapshot.getValue(Book.class);
                                bookList.add(book.getTitle());
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

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                adapter.notifyDataSetChanged();
                for (final DataSnapshot d: dataSnapshot.getChildren()) {
                    request1 = d.getValue(Request.class);
                    final DatabaseReference cDatabase = FirebaseDatabase.getInstance().getReference().child("books").child(request1.getBookId());
                    cDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Book book1 = dataSnapshot.getValue(Book.class);
                                bookTitle = book1.getTitle();
                                if (bookTitle.equals(bookname)) {
                                    Request rew = d.getValue(Request.class);
                                    users.add(rew);
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
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference("users")
                .child(request.getSender()).child("requestSent");
        database.child(request.getRid()).child("isAccept").setValue(2);

    }

    /**
     * Owner accept request from sender
     * @param Rlist
     * @param request
     */
    public void accept_request(final ArrayList<Request> Rlist, final Request request) {
        mDatabase.child(request.getRid()).child("isAccept").setValue(1);
        final DatabaseReference dDatabase = FirebaseDatabase.getInstance().getReference();
        dDatabase.child("books").child(request.getBookId()).child("status").setValue(2);
        dDatabase.child("users").child(request.getSender()).child("requestSent").child(request.getRid()).child("isAccept").setValue(1);
        dDatabase.child("users").child(request.getSender()).child("send").child("ss").setValue("2");
        ArrayList<Request> newlist = Rlist;
        newlist.remove(request);
        for(Request r :newlist){
            decline_request(r);
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

    public static Request getRequest1() {
        return request1;
    }

    public static void setRequest1(Request request1) {
        RequestReceived.request1 = request1;
    }

    public RequestReceived(String name, ArrayList<Request> requested){
        this.username = name;
        this.requestReceived = requested;

    }

    public ArrayList<Request> requestReceiveList(){
        return this.requestReceived;
    }



}
