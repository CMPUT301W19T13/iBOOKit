package com.example.ibookit.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ibookit.R;

public class SelectPreferenceActivity extends AppCompatActivity {

    private ImageButton sportsButton, horrorButton, comicButton, romanceButton, sciFiButton, businessButton, classicButton, thrillerButton, otherButton;
    private Button skip, confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

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
    }
}
