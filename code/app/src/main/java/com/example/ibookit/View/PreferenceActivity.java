package com.example.ibookit.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibookit.Functionality.RecommendationHandler;
import com.example.ibookit.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PreferenceActivity extends AppCompatActivity {

    private ImageButton sportsButton, horrorButton, comicButton, romanceButton, sciFiButton, businessButton, classicButton, thrillerButton, otherButton;
    private Button skip, confirm;
    private Boolean[] selected = new Boolean[9];
    private int count = 0;
    private String[] categories = {"Sports", "Horror", "Comics", "Romance", "SciFi", "Business", "Classics", "Thriller", "Others"};
    private TextView sportsText, horrorText, comicText, romanceText, sciFiText, businessText, classicText, thrillerText, otherText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        Arrays.fill(selected, false);

        sportsButton = findViewById(R.id.sportsImage);
        horrorButton = findViewById(R.id.horrorImage);
        comicButton = findViewById(R.id.comicsImage);
        romanceButton = findViewById(R.id.romanceImage);
        sciFiButton = findViewById(R.id.sciFiImage);
        businessButton = findViewById(R.id.businessImage);
        classicButton = findViewById(R.id.classicsImage);
        thrillerButton = findViewById(R.id.thrilerImage);
        otherButton = findViewById(R.id.otherImage);

        sportsText = findViewById(R.id.sportsText);
        horrorText = findViewById(R.id.horrorText);
        comicText = findViewById(R.id.comicText);
        romanceText = findViewById(R.id.romanceText);
        sciFiText = findViewById(R.id.scifiText);
        businessText = findViewById(R.id.businessText);
        classicText = findViewById(R.id.classicsText);
        thrillerText = findViewById(R.id.thrillerText);
        otherText = findViewById(R.id.otherText);

        skip = findViewById(R.id.skip_preferences);
        confirm = findViewById(R.id.confirm_preferences);

        sportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[0]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected sports", Toast.LENGTH_SHORT).show();
                        selected[0]=true;
                        count = count + 1;
                        sportsText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[0]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected sports", Toast.LENGTH_SHORT).show();
                    selected[0] = false;
                    count = count - 1;
                    sportsText.setBackgroundColor(Color.TRANSPARENT);

                }

            }
        });

        horrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[1]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected horror", Toast.LENGTH_SHORT).show();
                        selected[1]=true;
                        count = count + 1;
                        horrorText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[1]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected horror", Toast.LENGTH_SHORT).show();
                    selected[1] = false;
                    count = count - 1;
                    horrorText.setBackgroundColor(Color.TRANSPARENT);


                }
            }
        });

        comicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[2]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected comics", Toast.LENGTH_SHORT).show();
                        selected[2]=true;
                        count = count + 1;
                        comicText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[2]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected comics", Toast.LENGTH_SHORT).show();
                    selected[2] = false;
                    count = count - 1;
                    comicText.setBackgroundColor(Color.TRANSPARENT);


                }
            }
        });

        romanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[3]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected romance", Toast.LENGTH_SHORT).show();
                        selected[3]=true;
                        count = count + 1;
                        romanceText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[3]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected romance", Toast.LENGTH_SHORT).show();
                    selected[3] = false;
                    count = count - 1;
                    romanceText.setBackgroundColor(Color.TRANSPARENT);


                }
            }
        });

        sciFiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[4]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected Sci-Fi", Toast.LENGTH_SHORT).show();
                        selected[4]=true;
                        count = count + 1;
                        sciFiText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[4]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected Sci-Fi", Toast.LENGTH_SHORT).show();
                    selected[4] = false;
                    count = count - 1;
                    sciFiText.setBackgroundColor(Color.TRANSPARENT);


                }
            }
        });

        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[5]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected business", Toast.LENGTH_SHORT).show();
                        selected[5]=true;
                        count = count + 1;
                        businessText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[5]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected business", Toast.LENGTH_SHORT).show();
                    selected[5] = false;
                    count = count - 1;
                    businessText.setBackgroundColor(Color.TRANSPARENT);


                }
            }
        });

        classicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[6]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected classics", Toast.LENGTH_SHORT).show();
                        selected[6]=true;
                        count = count + 1;
                        classicText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[6]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected classics", Toast.LENGTH_SHORT).show();
                    selected[6] = false;
                    count = count - 1;
                    classicText.setBackgroundColor(Color.TRANSPARENT);


                }
            }
        });

        thrillerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[7]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected thrillers", Toast.LENGTH_SHORT).show();
                        selected[7]=true;
                        count = count + 1;
                        thrillerText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[7]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected thrillers", Toast.LENGTH_SHORT).show();
                    selected[7] = false;
                    count = count - 1;
                    thrillerText.setBackgroundColor(Color.TRANSPARENT);


                }
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[8]==false){

                    if (count<3){

                        Toast.makeText(PreferenceActivity.this, "You selected others", Toast.LENGTH_SHORT).show();
                        selected[8]=true;
                        count = count + 1;
                        otherText.setBackgroundColor(getResources().getColor(R.color.blacktext));

                    }
                    else {

                        Toast.makeText(PreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[8]==true){

                    Toast.makeText(PreferenceActivity.this, "You de-selected others", Toast.LENGTH_SHORT).show();
                    selected[8] = false;
                    count = count - 1;
                    otherText.setBackgroundColor(Color.TRANSPARENT);

                }

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PreferenceActivity.this, "Skipped", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PreferenceActivity.this, HomeSearchActivity.class);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> choosen = new ArrayList<>();
                for (int i=0; i<selected.length;i++) {
                    if (selected[i]) {
                        choosen.add(categories[i]);
                    }
                }

                Toast.makeText(PreferenceActivity.this, "All set", Toast.LENGTH_SHORT).show();
                new RecommendationHandler().UpdateRecommendation(choosen.toArray(new String[0]), true, false);

                Intent intent = new Intent(PreferenceActivity.this, HomeSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
