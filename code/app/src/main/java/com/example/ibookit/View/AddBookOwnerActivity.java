/**
 * Class name: AddBookOwnerActivity
 *
 * version 1.1
 *
 * Date: March 31, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
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
 * @version 1.1
 */
public class AddBookOwnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
                    Toast.makeText(AddBookOwnerActivity.this, "Select a category",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Book book = new Book(isbn, title, author, description, category, username);

                OwnerShelf ownerShelf = new OwnerShelf();
                ownerShelf.add_book_with_image(book, mImageUri);


                Toast.makeText(AddBookOwnerActivity.this, "Add a book successful",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddBookOwnerActivity.this, MyShelfOwnerActivity.class);
                startActivity(intent);
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scan = new Intent(AddBookOwnerActivity.this, ScannerActivity.class);
                startActivityForResult(scan, scanRequestCode);
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

        setBottomNavigationView();

    }

    /**
     * select category
     */
    private void categorySelector() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddBookOwnerActivity.this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory.setAdapter(adapter);
        mCategory.setOnItemSelectedListener(AddBookOwnerActivity.this);

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
                Toast.makeText(AddBookOwnerActivity.this, "scan code type inappropriate",
                        Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(AddBookOwnerActivity.this, "Unexpected error occurred",
                    Toast.LENGTH_SHORT).show();
        }

    }



    /**
     * NavigationBar enabled
     */
    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(AddBookOwnerActivity.this, HomeSearchActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(AddBookOwnerActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(AddBookOwnerActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        Intent request = new Intent(AddBookOwnerActivity.this, RequestChActivity.class);
                        request.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(request);

                        break;
                }

                return false;
            }
        });
    }

    /**
     * delete image enabled
     */
    private void setDeleteImageDiag() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete?");
        builder.setCancelable(true);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                imageButton.setImageResource(android.R.color.transparent);
                mImageUri = null;

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