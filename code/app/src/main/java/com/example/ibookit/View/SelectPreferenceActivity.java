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
                selected[0] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select sports", Toast.LENGTH_SHORT).show();
            }
        });

        horrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[1] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select horror", Toast.LENGTH_SHORT).show();
            }
        });

        comicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[2] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select comic", Toast.LENGTH_SHORT).show();
            }
        });

        romanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[3] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select romance", Toast.LENGTH_SHORT).show();
            }
        });

        sciFiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[4] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select sci-fi", Toast.LENGTH_SHORT).show();
            }
        });

        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[5] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select business", Toast.LENGTH_SHORT).show();
            }
        });

        classicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[6] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select classic", Toast.LENGTH_SHORT).show();
            }
        });

        thrillerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[7] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select thriller", Toast.LENGTH_SHORT).show();
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected[8] = true;
                Toast.makeText(SelectPreferenceActivity.this, "You select others", Toast.LENGTH_SHORT).show();
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
