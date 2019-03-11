package com.example.ibookit.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.ibookit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeSearchActivity extends AppCompatActivity {
    private static final String TAG = "HomeSearchActivity";
    public static Context sContext;
    private SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sContext = HomeSearchActivity.this;
        setContentView(R.layout.activity_home_search);

        configure_SearchButtonsAndSearchBar();
        setBottomNavigationView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sv.setQuery("", false);
        sv.clearFocus();
    }


    private void configure_SearchButtonsAndSearchBar(){
        Button searchUser = findViewById(R.id.search_user);
        Button viewCategory = findViewById(R.id.search_category);
        Button searchBook = findViewById(R.id.search_book);
        sv = findViewById(R.id.search_bar);


        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent request = new Intent(HomeSearchActivity.this, EditSearchActivity.class);

                request.putExtra("type", "SearchUser");
                request.putExtra("SearchValue", sv.getQuery().toString());

                startActivity(request);

            }
        });
        viewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setCategoryDialog();

            }
        });
        searchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent request = new Intent(HomeSearchActivity.this, EditSearchActivity.class);
                request.putExtra("type", "SearchTitle");
                request.putExtra("SearchValue", sv.getQuery().toString());
                startActivity(request);
            }
        });


    }



    private void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent add = new Intent(HomeSearchActivity.this, AddBookAsOwnerActivity.class);
                        add.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(add);
                        break;

                    case R.id.action_home:
                        break;

                    case R.id.action_myshelf:
                        Intent myshelf = new Intent(HomeSearchActivity.this, MyShelfOwnerActivity.class);
                        myshelf.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(myshelf);
                        break;

                    case R.id.action_profile:
                        Intent profile = new Intent(HomeSearchActivity.this, UserProfileActivity.class);
                        profile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(profile);
                        break;

                    case R.id.action_request:
                        Intent request = new Intent(HomeSearchActivity.this, CheckRequestsActivity.class);
                        request.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(request);

                        break;
                }

                return false;
            }
        });
    }
    private void setCategoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //some of these options will be changed later, this is just for test
        final CharSequence[] options  = {"fine", "fivestar", "KKK", "Westeast", "thrilling"};
        builder.setTitle("Choose a category").setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent request = new Intent(HomeSearchActivity.this, EditSearchActivity.class);
                request.putExtra("type", "SearchCategory");
                request.putExtra("SearchValue", options[which]);
                startActivity(request);

            }
        });




        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void sendNotification(int situation) {

        //Get an instance of NotificationManager//


        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "This is my channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        mChannel.setDescription(Description);
        mChannel.enableLights(true);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mChannel.setShowBadge(false);

        if(situation ==1) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(HomeSearchActivity.this)
                            .setSmallIcon(android.R.drawable.sym_def_app_icon)
                            .setContentTitle("New notification")
                            .setContentText("You received a new Request!")
                            .setChannelId(CHANNEL_ID);

        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.createNotificationChannel(mChannel);

        //NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(001, mBuilder.build());}
        else{
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(HomeSearchActivity.this)
                            .setSmallIcon(android.R.drawable.sym_def_app_icon)
                            .setContentTitle("New notification")
                            .setContentText("Your request has been accepted!")
                            .setChannelId(CHANNEL_ID);


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(mChannel);

            //NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(001, mBuilder.build());
        }
    }
}