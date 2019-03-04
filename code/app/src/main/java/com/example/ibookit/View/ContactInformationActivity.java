package com.example.ibookit.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ibookit.R;

public class ContactInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);

        Button edit =  findViewById(R.id.saveChanges_userprofile);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_change_userproflie();
                Intent intent = new Intent(ContactInformationActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });


    }

    private void save_change_userproflie() {
        // write code --call firebase data
    }
}
