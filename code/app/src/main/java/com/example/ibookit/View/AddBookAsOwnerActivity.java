/**
 * Class name: AddBookAsOwnerActivity
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibookit.Functionality.FetchUrlData;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;
import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class AddBookAsOwnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static TextView mTitle, mAuthor, mIsbn, mDescription;
    public Spinner mCategory;
    private String category;
    private Button confirm;
    private ImageButton imageButton;
    private Uri mImageUri;
    private Button scanButton;
    private String googleBookAPIURL = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    private final Integer scanRequestCode = 1000;

    private static final int PICK_IMAGE_REQUEST = 1;
    public static String ScanBookJson;


    /**
     * This activity let owner add a book in UI
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        mTitle = findViewById(R.id.bookTitleAdd);
        mAuthor = findViewById(R.id.bookAuthorAdd);
        mIsbn = findViewById(R.id.bookISBNAdd);

        mCategory = findViewById(R.id.spinner_add_book);
        categorySelector();

        mDescription = findViewById(R.id.descriptionAdd);

        confirm = findViewById(R.id.confirmSendRequest);
        imageButton = findViewById(R.id.bookImageUpdate);

        scanButton = findViewById(R.id.scan_add_book);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString();
                String author = mAuthor.getText().toString();
                String isbn = mIsbn.getText().toString();

                String description = mDescription.getText().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String username = user.getDisplayName();

                if (category.length() == 0) {
                    Toast.makeText(AddBookAsOwnerActivity.this, "Select a category",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Book book = new Book(isbn, title, author, description, category, username);

                OwnerShelf ownerShelf = new OwnerShelf();
                ownerShelf.add_book_with_image(book, mImageUri);


                Toast.makeText(AddBookAsOwnerActivity.this, "Add a book successful",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddBookAsOwnerActivity.this, MyShelfOwnerActivity.class);
                startActivity(intent);
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scan = new Intent(AddBookAsOwnerActivity.this, ScannerActivity.class);
                startActivityForResult(scan, scanRequestCode);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });

        setBottomNavigationView();

    }

    private void categorySelector() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddBookAsOwnerActivity.this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory.setAdapter(adapter);
        mCategory.setOnItemSelectedListener(AddBookAsOwnerActivity.this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**
     * pick a image for the book in system
     * <p>
     * reference: https://codinginflow.com/tutorials/android/firebase-storage-upload-and-retrieve-images/part-2-image-chooser
     */
    private void fileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    /**
     * Put image selected to imageButton
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
        else if (requestCode == scanRequestCode && resultCode == RESULT_OK ){
            String scannedISBN = data.getStringExtra("scanned_ISBN");

            if (scannedISBN.length()<=13){
                String myUrl = googleBookAPIURL + scannedISBN;

                FetchUrlData fetchBook = new FetchUrlData();
                fetchBook.execute(myUrl);

                mIsbn.setText(scannedISBN);
            }else{
                Toast.makeText(AddBookAsOwnerActivity.this, "scan code type inappropriate",
                        Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(AddBookAsOwnerActivity.this, "Unexpected error occurred",
                    Toast.LENGTH_SHORT).show();
        }

    }



    /**
     * NavigationBar enabled
     */
    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(AddBookAsOwnerActivity.this, HomeSearchActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(AddBookAsOwnerActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(AddBookAsOwnerActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        Intent request = new Intent(AddBookAsOwnerActivity.this, CheckRequestsActivity.class);
                        request.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(request);

                        break;
                }

                return false;
            }
        });
    }
}