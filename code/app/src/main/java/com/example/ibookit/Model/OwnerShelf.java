/**
 * Class name: OwnerShelf
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Model;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class OwnerShelf implements BookShelf {


    private static final String TAG = "OwnerShelf";
    private DatabaseReference mDatabase;
    private ArrayList<Book> myBooks = new ArrayList<>();
    private String username;

    // Careful
    private String key;

    // init FireBase
    public OwnerShelf() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username = user.getDisplayName();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("ownerShelf");
    }

    @Override
    public ArrayList<Book> All_books(){
        //returns all books that you own
        return myBooks;
    }

    /**
     * Sync Owner bookshelf with the FireBase
     *
     * @param books
     * @param adapter
     * @param status
     */
    @Override
    public void SyncBookShelf(final ArrayList<Book> books, final ArrayAdapter<Book> adapter, final Integer status) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                books.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Book book = d.getValue(Book.class);
                    if (status == -1) {
                        books.add(book);
                    } else if (book.getStatus() == status) {
                        books.add(book);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    @Override
    public void add_book(Book book){

    }

    /**
     * Owner add a book with book image (image can be empty)
     * @param book
     * @param mImageUri
     */
    public void add_book_with_image(final Book book, Uri mImageUri) {
        key = createBookKey();

        book.setId(key);

        if (mImageUri != null) {
            StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
            final StorageReference fileRef = mStorageRef.child("books").child(key);


            fileRef.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        book.setImageURL(downloadUri.toString());
                    }

                    mDatabase.child(key).setValue(book);

                    // Add this book on child books
                    FirebaseDatabase.getInstance().getReference().child("books").child(key).setValue(book);
                }
            });


        } else {
            mDatabase.child(key).setValue(book);

            // Add this book on child books
            FirebaseDatabase.getInstance().getReference().child("books").child(key).setValue(book);
        }



    }

    /**
     * Owner remove a book on FireBase
     * @param book
     */
    @Override
    public void remove_book(Book book) {
        String key = book.getId();

        mDatabase.child(key).removeValue();

        FirebaseDatabase.getInstance().getReference().child("books").child(key).removeValue();
    }

    /**
     * Owner update a book information
     *
     * @param book
     */
    public void update_book(Book book) {


        // update book info in owner's shelf
        mDatabase.child(book.getId()).setValue(book);

        // update book info in books
        FirebaseDatabase.getInstance().getReference().child("books").child(book.getId()).setValue(book);


    }


    /**
     * create a unique key for a book
     * @return
     */
    private String createBookKey() {
        return mDatabase.push().getKey();
    }



}
