/**
 * Class name: BookListAdapter
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibookit.Functionality.BookStatusHandler;
import com.example.ibookit.Model.Book;
import com.example.ibookit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class BookListAdapter extends ArrayAdapter<Book> {

    private static final String TAG = "BookListAdapter";

    private Context mContext;
    private int mResource;

    private TextView mTitle, mAuthor, mIsbn, mStatus;
    private ImageView imageView;


    /**
     * reference: https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
     *
     * @param context
     * @param resource
     * @param objects
     */
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


        mTitle = convertView.findViewById(R.id.listName);
        mAuthor = convertView.findViewById(R.id.listAuthor);
        mIsbn = convertView.findViewById(R.id.listISBN);
        mStatus = convertView.findViewById(R.id.listStatus);
        imageView = convertView.findViewById(R.id.imageBook);

        mTitle.setText("Title:  " + book.getTitle());
        mAuthor.setText("Author:  " + book.getAuthor());
        mIsbn.setText("Isbn:  " + book.getIsbn());

        BookStatusHandler handler = new BookStatusHandler();

        mStatus.setText("Status:  " + handler.StatusString(book));

        setImage(book.getImageURL(), imageView);

        return convertView;
    }

    /**
     * set image for book in custom listView
     * @param path
     * @param imageView
     */
    private void setImage(String path, final ImageView imageView) {
        Picasso.get().load(path).fit().centerCrop().into(imageView);
    }
}
