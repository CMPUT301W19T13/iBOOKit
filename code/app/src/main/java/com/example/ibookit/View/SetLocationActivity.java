package com.example.ibookit.View;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ibookit.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
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

public class SetLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
                // TODO Auto-generated method stub
                // Here your code
                Toast.makeText(SetLocationActivity.this, "Dragging Start",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                // TODO Auto-generated method stub
                LatLng position = marker.getPosition();
                Toast.makeText(
                        SetLocationActivity.this,
                        "Lat " + position.latitude + " "
                                + "Long " + position.longitude,
                        Toast.LENGTH_LONG).show();
                System.out.println("yalla b2a "
                        + mMap.getMyLocation().getLatitude());
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                // TODO Auto-generated method stub
                // Toast.makeText(MainActivity.this, "Dragging",
                // Toast.LENGTH_SHORT).show();
                System.out.println("Draagging");
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;

        setUpMap();

    }

    private void setUpMap() {
        LatLng qwe = new LatLng(53.48328,-113.50672);
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions()
                .position(qwe)
                .title("Place to fetch book")
                .draggable(true)
                .snippet("Here!")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
                // TODO Auto-generated method stub
                // Here your code
                Toast.makeText(SetLocationActivity.this, "Dragging Start",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                // TODO Auto-generated method stub
                LatLng position = marker.getPosition();
                Toast.makeText(
                        SetLocationActivity.this,
                        "Lat " + position.latitude + " "
                                + "Long " + position.longitude,
                        Toast.LENGTH_LONG).show();
                String rid = getIntent().getStringExtra("rid");
                mDatabase = FirebaseDatabase.getInstance().getReference().child("requests").child(rid);
                mDatabase.child("lat").setValue(position.latitude);
                mDatabase.child("lon").setValue(position.longitude);
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                // TODO Auto-generated method stub
                // Toast.makeText(MainActivity.this, "Dragging",
                // Toast.LENGTH_SHORT).show();
                System.out.println("Draagging");
            }
        });


    }


    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }

    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

}