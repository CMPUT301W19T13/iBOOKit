package com.example.ibookit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;
import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddBookAsOwnerActivity extends AppCompatActivity {

    private TextView mTitle, mAuthor, mIsbn, mCategory;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        mTitle = findViewById(R.id.bookTitleAdd);
        mAuthor = findViewById(R.id.bookAuthorAdd);
        mIsbn = findViewById(R.id.bookISBNAdd);
        mCategory = findViewById(R.id.bookCategoryAdd);

        confirm = findViewById(R.id.confirmAddBook);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString();
                String author = mAuthor.getText().toString();
                String isbn = mIsbn.getText().toString();
                String category = mCategory.getText().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String username = user.getDisplayName();

                Book book = new Book(isbn, title, author, category, username);

                OwnerShelf ownerShelf = new OwnerShelf();
                ownerShelf.add_book(book);

                Toast.makeText(AddBookAsOwnerActivity.this, "Add a book successful",
                        Toast.LENGTH_SHORT).show();
            }
        });

        setBottomNavigationView();



    }

    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(AddBookAsOwnerActivity.this, HomeSearchActivity.class);
                        home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(home);

                        break;

                    case R.id.action_myshelf:
<<<<<<< HEAD
                        Intent intent3 = new Intent(AddBookAsOwnerActivity.this, MyShelfOwnerActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;

                    case R.id.action_profile:
                        Intent intent4 = new Intent(AddBookAsOwnerActivity.this, UserProfileActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
=======
                        Intent myshelf = new Intent(AddBookAsOwnerActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(AddBookAsOwnerActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
>>>>>>> master
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
