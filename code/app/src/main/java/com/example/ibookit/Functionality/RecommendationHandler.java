package com.example.ibookit.Functionality;

import android.support.annotation.NonNull;

import com.example.ibookit.Model.Recommendation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecommendationHandler {

    private DatabaseReference mDatabase;
    private Double MaximumPoints = 125.00;

    public RecommendationHandler(String username) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username);
    }

    public void CreateNewRecommendation() {
        Recommendation recommendation = new Recommendation();
        mDatabase.child("recommendation").setValue(recommendation);
    }

    public void UpdateRecommendation(String[] categories) {
        mDatabase.child("recommendation").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Recommendation recommendation = dataSnapshot.getValue(Recommendation.class);
                HashMap<String, Double> map = recommendation.getCategory();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private HashMap<String, Double> ProcessCategory(HashMap<String, Double> map, String[] categories) {
        for (Map.Entry<String, Double> kv: map.entrySet()) {
            if (Arrays.asList(categories).contains(kv.getKey())) {
                Double value = kv.getValue();
            }
        }

        return map;
    }

//    private HashMap<String, Double> CheckThePoints(HashMap<String, Double> map) {
//        int count = 0;
//        for (Double d: map.values()) {
//
//        }
//    }


}
