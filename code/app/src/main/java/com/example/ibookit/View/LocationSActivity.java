/**
 * Class name: LocationSActivity
 *
 * version 1.1
 *
 * Date: March 31, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ibookit.Model.GeoLocation;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *
 * @author jiazhen
 *
 * @version 1.1
 *
 */
public class LocationSActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * get permission
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case 2: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                }
            }
        }
    }

    /**
     * on map ready
     * @param map
     */
    @Override
    public void onMapReady(GoogleMap map) {

        //initialize the map and set the marker
        mMap = map;
        LatLng Your_Location = new LatLng(53.527503, -113.529492);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions()
                .position(Your_Location)
                .title("Place to fetch book")
                .draggable(true)
                .snippet("Here!")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Your_Location, 15));
        Toast.makeText(
                LocationSActivity.this,
                "Drag to the location you want! ",
                Toast.LENGTH_LONG).show();

        setUpMap();

    }

    /**
     * set up map
     */
    private void setUpMap() {
        // set a listener on the marker when it changed
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
                Toast.makeText(LocationSActivity.this, "Dragging Start",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng position = marker.getPosition();

                Toast.makeText(
                        LocationSActivity.this,
                        "You have set the location!",
                        Toast.LENGTH_LONG).show();
                String rid = getIntent().getStringExtra("rid");
                GeoLocation geoLocation = new GeoLocation(position.latitude, position.longitude);
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("locations").child(rid).setValue(geoLocation);
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                System.out.println("Draagging");
            }
        });

    }

}