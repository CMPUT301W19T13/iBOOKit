package com.example.ibookit.View;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.ibookit.Model.Request;
import com.example.ibookit.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "ViewLocationActivity";
    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location2);
        GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;

        setUpMap();

    }

    private void setUpMap() {
        String ridS = getIntent().getStringExtra("ridS");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("requests").child(ridS);
        mDatabase.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    lat = (double) dataSnapshot.child("lat").getValue();
                    lon = (double) dataSnapshot.child("lon").getValue();
                    LatLng qwe = new LatLng(lat, lon);
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    mMap.addMarker(new MarkerOptions()
                            .position(qwe)
                            .title("Place to fetch book")
                            //.draggable(true)
                            .snippet("Here!")
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(qwe, 15));

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }

        });

    }

}

