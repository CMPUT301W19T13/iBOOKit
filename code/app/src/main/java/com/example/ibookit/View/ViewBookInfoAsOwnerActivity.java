package com.example.ibookit.View;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibookit.Functionality.BookStatusHandler;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;
import com.example.ibookit.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


public class ViewBookInfoAsOwnerActivity extends AppCompatActivity {

    private static final String TAG = "ViewBookInfoActivity";
    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView mTitle, mAuthor, mIsbn, mStatus, mBorrower, mCategory, mDescription;
    private Button submit;
    private OwnerShelf ownerShelf = new OwnerShelf();
    private Book book;
    private ImageButton imageButton;
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        mTitle = findViewById(R.id.bookTitleAdd);
        mAuthor = findViewById(R.id.bookAuthorAdd);
        mIsbn = findViewById(R.id.bookISBNAdd);
        mCategory = findViewById(R.id.bookCategoryAdd);
        mStatus = findViewById(R.id.statusAdd);
        mBorrower = findViewById(R.id.borrowerAdd);
        mDescription = findViewById(R.id.descriptionView);
        imageButton = findViewById(R.id.bookImageUpdate);

        submit = findViewById(R.id.confirmChangeBook);

        Intent intent = getIntent();
        String objStr = intent.getStringExtra("book");

        if (objStr != null) {
            Gson gson = new Gson();
            book = gson.fromJson(objStr, Book.class);
        } else {
            Log.d(TAG, "onCreate: no objStr");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        mTitle.setText(book.getTitle());
        mAuthor.setText(book.getAuthor());
        mIsbn.setText(book.getIsbn());
        mCategory.setText(book.getCategory());
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

                // update a book only if it is available
                if (book.getStatus() != 0) {
                    Toast.makeText(ViewBookInfoAsOwnerActivity.this, "View Only",
                            Toast.LENGTH_SHORT).show();

                } else {

                    String title = mTitle.getText().toString();
                    String author = mAuthor.getText().toString();
                    String isbn = mIsbn.getText().toString();
                    String category = mCategory.getText().toString();
                    String description = mDescription.getText().toString();

                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setIsbn(isbn);
                    book.setCategory(category);
                    book.setDescription(description);

                    updateImage(book);

                    Toast.makeText(ViewBookInfoAsOwnerActivity.this, "Submitted",
                            Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(ViewBookInfoAsOwnerActivity.this, MyShelfOwnerActivity.class);
                startActivity(intent);
                finish();
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });

    }

    private void updateImage (final Book book) {
        if (mImageUri != null) {
            StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
            final StorageReference fileRef = mStorageRef.child("book").child(book.getId());


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

        } else {
            ownerShelf.update_book(book);
        }

    }

    private void fileChooser () {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(imageButton);

        }

    }


}
