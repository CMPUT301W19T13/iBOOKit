package com.example.ibookit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ibookit.Functionality.RecommendationHandler;
import com.example.ibookit.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectPreferenceActivity extends AppCompatActivity {

    private ImageButton sportsButton, horrorButton, comicButton, romanceButton, sciFiButton, businessButton, classicButton, thrillerButton, otherButton;
    private Button skip, confirm;
    private Boolean[] selected = new Boolean[9];
    int count = 0;
    private String[] categories = {"Sports", "Horror", "Comics", "Romance", "SciFi", "Business", "Classics", "Thriller", "Others"};

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

        skip = findViewById(R.id.skip_preferences);
        confirm = findViewById(R.id.confirm_preferences);

        sportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[0]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected sports", Toast.LENGTH_SHORT).show();
                        selected[0]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[0]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected sports", Toast.LENGTH_SHORT).show();
                    selected[0] = false;
                    count = count - 1;


                }

            }
        });

        horrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[1]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected horror", Toast.LENGTH_SHORT).show();
                        selected[1]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[1]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected horror", Toast.LENGTH_SHORT).show();
                    selected[1] = false;
                    count = count - 1;


                }
            }
        });

        comicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[2]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected comics", Toast.LENGTH_SHORT).show();
                        selected[2]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[2]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected comics", Toast.LENGTH_SHORT).show();
                    selected[2] = false;
                    count = count - 1;


                }
            }
        });

        romanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[3]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected romance", Toast.LENGTH_SHORT).show();
                        selected[3]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[3]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected romance", Toast.LENGTH_SHORT).show();
                    selected[3] = false;
                    count = count - 1;


                }
            }
        });

        sciFiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[4]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected Sci-Fi", Toast.LENGTH_SHORT).show();
                        selected[4]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[4]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected Sci-Fi", Toast.LENGTH_SHORT).show();
                    selected[4] = false;
                    count = count - 1;


                }
            }
        });

        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[5]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected business", Toast.LENGTH_SHORT).show();
                        selected[5]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[5]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected business", Toast.LENGTH_SHORT).show();
                    selected[5] = false;
                    count = count - 1;


                }
            }
        });

        classicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[6]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected classics", Toast.LENGTH_SHORT).show();
                        selected[6]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[6]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected classics", Toast.LENGTH_SHORT).show();
                    selected[6] = false;
                    count = count - 1;


                }
            }
        });

        thrillerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[7]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected thrillers", Toast.LENGTH_SHORT).show();
                        selected[7]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[7]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected thrillers", Toast.LENGTH_SHORT).show();
                    selected[7] = false;
                    count = count - 1;


                }
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected[8]==false){

                    if (count<3){

                        Toast.makeText(SelectPreferenceActivity.this, "You selected others", Toast.LENGTH_SHORT).show();
                        selected[8]=true;
                        count = count + 1;

                    }
                    else {

                        Toast.makeText(SelectPreferenceActivity.this, "Max 3 already selected (De-select first)", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (selected[8]==true){

                    Toast.makeText(SelectPreferenceActivity.this, "You de-selected others", Toast.LENGTH_SHORT).show();
                    selected[8] = false;
                    count = count - 1;


                }

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectPreferenceActivity.this, "Skipped", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SelectPreferenceActivity.this, HomeSearchActivity.class);
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

                Toast.makeText(SelectPreferenceActivity.this, "All set", Toast.LENGTH_SHORT).show();
                new RecommendationHandler().UpdateRecommendation(choosen.toArray(new String[0]), true);

                Intent intent = new Intent(SelectPreferenceActivity.this, HomeSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
