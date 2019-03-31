package com.example.ibookit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibookit.Functionality.BookStatusHandler;
import com.example.ibookit.Functionality.NewRequestHandler;
import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Request;
import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class SendRequestActivity extends AppCompatActivity {

    private static final String TAG = "SendRequestActivity";
    private Book book;
    private TextView mTitle, mAuthor, mIsbn, mOwner, mDescription, mCategory;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendrequest);

        mTitle = findViewById(R.id.bookTitleRequest);
        mTitle.setSelected(true);
        mAuthor = findViewById(R.id.bookAuthorRequest);
        mAuthor.setSelected(true);
        mIsbn = findViewById(R.id.bookISBNRequest);
        mOwner = findViewById(R.id.ownerRequest);
        mDescription = findViewById(R.id.descriptionViewRequest);
        mDescription.setMovementMethod(new ScrollingMovementMethod());
        mCategory = findViewById(R.id.categoryRequest);
        imageView = (ImageView) findViewById(R.id.bookImageRequest);
        button = findViewById(R.id.confirmSendRequest);

        Intent intent = getIntent();
        String objStr = intent.getStringExtra("book");

        if (objStr != null) {
            Gson gson = new Gson();
            book = gson.fromJson(objStr, Book.class);
        } else {
            Log.d(TAG, "onCreate: no objStr");
        }

        setImage(book.getImageURL(), imageView);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mTitle.setText(book.getTitle());
        mAuthor.setText(book.getAuthor());
        mIsbn.setText(book.getIsbn());
        mCategory.setText(book.getCategory());
        mDescription.setText(book.getDescription());

        mOwner.setText(book.getOwner());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                DatabaseReference allRequestSent = FirebaseDatabase.getInstance().getReference().child("users").child(user.getDisplayName()).child("requestSent");
                allRequestSent.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren()) {
                            Request r = d.getValue(Request.class);
                            if (r.getBookId().equals(book.getId())) {
                                Toast.makeText(SendRequestActivity.this, "Already requested",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        // Not requested yet
                        Request request = new Request(book);
                        NewRequestHandler createRequest = new NewRequestHandler();
                        if (createRequest.SendRequestToOwner(request)) {
                            // send notification to owner
                            createRequest.setNotificationToOwner(book.getTitle());
                            // change status to 1
                            new BookStatusHandler().setBookStatusFirebase(book, 1);

                            // make toast
                            Toast.makeText(SendRequestActivity.this, "send request successful",
                                    Toast.LENGTH_SHORT).show();
                        } else if (book.getStatus() == 2 || book.getStatus() == 3){
                            Toast.makeText(SendRequestActivity.this, "Book not available for request",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(SendRequestActivity.this, "Cannot request your own book",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                finish();
            }
        });

    }

    private void setImage(String path, ImageView image) {
        Picasso.get().load(path).fit().centerCrop().into(image);
    }
}
