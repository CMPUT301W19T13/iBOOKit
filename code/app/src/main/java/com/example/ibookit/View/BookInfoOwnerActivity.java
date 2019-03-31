/**
 * Class name: BookInfoOwnerActivity
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibookit.Functionality.BookStatusHandler;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;
import com.example.ibookit.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * @author zijun wu
 *
 * @version 1.0
 */

public class BookInfoOwnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "ViewBookInfoActivity";
    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView mTitle, mAuthor, mIsbn, mStatus, mBorrower, mDescription;
    private Spinner mCategory;
    private String category;
    private Button submit;
    private OwnerShelf ownerShelf = new OwnerShelf();
    private Book book;
    private ImageButton imageButton;
    private Uri mImageUri;

    /**
     * View book information for a particular book
     * get the book from owner bookShelf
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        mTitle = findViewById(R.id.bookTitleAdd);
        mAuthor = findViewById(R.id.bookAuthorAdd);
        mIsbn = findViewById(R.id.bookISBNAdd);
        mCategory = findViewById(R.id.spinner_view_book);
        mStatus = findViewById(R.id.statusAdd);
        mBorrower = findViewById(R.id.borrowerAdd);
        mDescription = findViewById(R.id.descriptionView);
        imageButton = findViewById(R.id.bookImageUpdate);

        submit = findViewById(R.id.confirmSendRequest);

        Intent intent = getIntent();
        String objStr = intent.getStringExtra("book");

        if (objStr != null) {
            Gson gson = new Gson();
            book = gson.fromJson(objStr, Book.class);
        } else {
            Log.d(TAG, "onCreate: no objStr");
        }

        // load image once
        setImage(book.getImageURL(), imageButton);

    }

    /**
     * OnStart: Set info for book into TextView
     */
    @Override
    protected void onStart() {
        super.onStart();

        mTitle.setText(book.getTitle());
        mAuthor.setText(book.getAuthor());
        mIsbn.setText(book.getIsbn());

        category = book.getCategory();
        categorySelector();

        mDescription.setText(book.getDescription());

        BookStatusHandler handler = new BookStatusHandler();
        mStatus.setText(handler.StatusString(book));

        if (book.getCurrentBorrower().length() == 0) {
            mBorrower.setText("No borrower");
            mBorrower.setTextColor(Color.RED);
        } else {
            mBorrower.setText(book.getCurrentBorrower());
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // cannot update a book if it is borrowed
                if (book.getStatus() == 3) {
                    Toast.makeText(BookInfoOwnerActivity.this, "View Only",
                            Toast.LENGTH_SHORT).show();

                } else {

                    String title = mTitle.getText().toString();
                    String author = mAuthor.getText().toString();
                    String isbn = mIsbn.getText().toString();
                    String description = mDescription.getText().toString();

                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setIsbn(isbn);
                    book.setCategory(category);
                    book.setDescription(description);

                    updateImage(book);

                    Toast.makeText(BookInfoOwnerActivity.this, "Submitted",
                            Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });

        imageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setDeleteImageDiag();
                return true;
            }
        });

    }

    private void categorySelector(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BookInfoOwnerActivity.this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory.setAdapter(adapter);
        mCategory.setOnItemSelectedListener(BookInfoOwnerActivity.this);
        mCategory.setSelection(adapter.getPosition(category));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * update the image for book if user change image
     *
     * @param book
     */
    private void updateImage (final Book book) {
        if (mImageUri != null) {
            StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
            final StorageReference fileRef = mStorageRef.child("books").child(book.getId());


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
                        ownerShelf.update_book(book);
                    }

                }
            });

            return;

        }

        ownerShelf.update_book(book);

    }

    /**
     * pick a image for the book in system
     *
     * reference: https://codinginflow.com/tutorials/android/firebase-storage-upload-and-retrieve-images/part-2-image-chooser
     */
    private void fileChooser () {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).fit().centerCrop().into(imageButton);

        }

    }

    /**
     * Set image into imageButton
     *
     * @param path
     * @param image
     */
    private void setImage(String path, ImageButton image) {
        Picasso.get().load(path).fit().centerCrop().into(image);
    }

    private void setDeleteImageDiag() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete?");
        builder.setCancelable(true);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                imageButton.setImageResource(android.R.color.transparent);
                dialog.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

}
