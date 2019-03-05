package com.example.ibookit.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibookit.Functionality.BookStatusHandler;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.OwnerShelf;
import com.example.ibookit.R;
import com.google.gson.Gson;


public class ViewBookInfoActivity extends AppCompatActivity {

    private static final String TAG = "ViewBookInfoActivity";
    private TextView mTitle, mAuthor, mIsbn, mStatus, mBorrower;
    private Button change, delete;
    private OwnerShelf ownerShelf = new OwnerShelf();
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        mTitle = findViewById(R.id.bookTitleView);
        mAuthor = findViewById(R.id.bookAuthorView);
        mIsbn = findViewById(R.id.bookISBNView);
        mStatus = findViewById(R.id.bookStatusView);
        mBorrower = findViewById(R.id.userTypeView);

        change = findViewById(R.id.change_viewBook);
        delete = findViewById(R.id.delete_viewBook);

        Intent intent = getIntent();
        String objStr = intent.getStringExtra("book");

        if (objStr != null) {
            Gson gson = new Gson();
            book = gson.fromJson(objStr, Book.class);
        } else {
            Log.d(TAG, "onCreate: no objStr");
        }

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownerShelf.remove_book(book);

                Toast.makeText(ViewBookInfoActivity.this, "Book deleted",
                        Toast.LENGTH_SHORT).show();

                Intent myShelf = new Intent(ViewBookInfoActivity.this, MyShelfOwnerActivity.class);
                startActivity(myShelf);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mTitle.setText(book.getTitle());
        mAuthor.setText(book.getAuthor());
        mIsbn.setText(book.getIsbn());

        BookStatusHandler handler = new BookStatusHandler();
        mStatus.setText(handler.StatusString(book));


        if (book.getCurrentBorrower().length() == 0) {
            mBorrower.setText("No borrower");
            mBorrower.setTextColor(Color.RED);
        } else {
            mBorrower.setText(book.getCurrentBorrower());
        }



    }
}
