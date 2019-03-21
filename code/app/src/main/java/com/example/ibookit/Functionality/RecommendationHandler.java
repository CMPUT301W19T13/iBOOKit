package com.example.ibookit.Functionality;

import com.example.ibookit.Model.Recommendation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecommendationHandler {

    private String username;
    private DatabaseReference mDatabase;

    public RecommendationHandler(String username) {
        this.username = username;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username);
    }

    public void CreateNewRecommendation() {
        Recommendation recommendation = new Recommendation(username);
        mDatabase.child("recommendation").setValue(recommendation);
    }

}
