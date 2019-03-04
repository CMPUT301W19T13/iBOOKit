package com.example.ibookit.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;
import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddBook extends AppCompatActivity {

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
            }
        });

    }
}
