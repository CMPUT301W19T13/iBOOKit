package com.example.ibookit.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ibookit.Functionality.BookStatusHandler;
import com.example.ibookit.Model.Book;
import com.example.ibookit.R;

import java.util.ArrayList;

public class BookListAdapter extends ArrayAdapter<Book> {

    private Context mContext;
    private int mResource;

    private TextView mTitle, mAuthor, mIsbn, mStatus;

    public BookListAdapter(Context context, int resource, ArrayList<Book> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        Book book = getItem(position);

//        Log.d(TAG, "getView: " + book);

        mTitle = convertView.findViewById(R.id.listName);
        mAuthor = convertView.findViewById(R.id.listAuthor);
        mIsbn = convertView.findViewById(R.id.listISBN);
        mStatus = convertView.findViewById(R.id.listStatus);

        mTitle.setText("Title:  " + book.getTitle());
        mAuthor.setText("Author:  " + book.getAuthor());
        mIsbn.setText("Isbn:  " + book.getIsbn());


        BookStatusHandler handler = new BookStatusHandler();

        mStatus.setText("Status:  " + handler.StatusString(book));

        return convertView;
    }
}
