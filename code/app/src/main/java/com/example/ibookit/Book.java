package com.example.ibookit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Book extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }
    MultiSelectionSpinner spinner=(MultiSelectionSpinner)findViewById(R.id.input1);

    List<String> list = new ArrayList<String>();
          list.add("List1");
          list.add("List2");
          spinner.setItems(list);
}
