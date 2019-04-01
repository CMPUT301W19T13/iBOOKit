/**
 * Class name: LocationVActivity
 *
 * version 1.2
 *
 * Date: March 29, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ibookit.Model.GeoLocation;
import com.example.ibookit.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * @author jiazhen
 *
 * @version 1.2
 */

public class LocationVActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "LocationVActivity";
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

    /**
     * on map ready
     *
     * @param map
     */
    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;

        setUpMap();

    }

    /**
     * set up map
     */
    private void setUpMap() {
        String ridS = getIntent().getStringExtra("ridS");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("locations").child(ridS);
        mDatabase.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    GeoLocation geoLocation = dataSnapshot.getValue(GeoLocation.class);
                    lat = geoLocation.getLatitude();
                    lon = geoLocation.getLongitude();
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

