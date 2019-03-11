package com.example.ibookit.Model;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class OwnerShelf implements BookShelf{

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

    @Override
    public void remove_book(Book book) {
        String key = book.getId();

        mDatabase.child(key).removeValue();

        FirebaseDatabase.getInstance().getReference().child("books").child(key).removeValue();
    }



    public void update_book(Book book) {

        mDatabase.child(book.getId()).setValue(book);

        FirebaseDatabase.getInstance().getReference().child("books").child(book.getId()).setValue(book);

    }



    private String createBookKey() {
        return mDatabase.push().getKey();
    }



}
