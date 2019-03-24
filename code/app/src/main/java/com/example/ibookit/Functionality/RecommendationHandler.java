package com.example.ibookit.Functionality;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.Recommendation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecommendationHandler {

    private static final String TAG = "RecommendationHandler";
    private String username;
    private DatabaseReference mDatabase;
    private DatabaseReference aDatabase;
    private Double maxPoint = 125.00;
    private Singleton singleton;

    public RecommendationHandler() {
        singleton = new Singleton();
        this.username = singleton.getUsername();
        mDatabase = singleton.getUserDatabase();
        aDatabase = singleton.getAllDatabase();
    }

    public RecommendationHandler(String username) {
        this.username = username;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username);
    }

    public void CreateNewRecommendation() {
        Recommendation recommendation = new Recommendation(username);
        mDatabase.child("recommendation").setValue(recommendation);
    }

    public void syncRecommendationBookShelf(final ArrayList<Book> books, final ArrayAdapter<Book> adapter){
        mDatabase.child("recommendation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Recommendation recommendation = dataSnapshot.getValue(Recommendation.class);

                if (recommendation != null) {
                    HashMap<String, Double> points = recommendation.getCategoryPoint();

                    final ArrayList<String> category = getTopThreeCategory(points);
                    Log.d(TAG, "onDataChange: top 3 category: " + category);

                    aDatabase.child("books").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            books.clear();
                            adapter.notifyDataSetChanged();
                            for (DataSnapshot d : dataSnapshot.getChildren()) {
                                Book book = d.getValue(Book.class);
                                if (category.contains(book.getCategory())) {
                                    books.add(book);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private ArrayList<String> getTopThreeCategory(HashMap<String, Double> points) {
        ArrayList<String> result = new ArrayList<>();

        List<Map.Entry<String, Double>> list = new LinkedList<>(points.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        int count = 0;

        Log.d(TAG, "getTopThreeCategory: " + list);

        for (Map.Entry<String, Double> item: list) {
            if (count >= 6) {
                result.add(item.getKey());
            }
            count++;
        }

        return result;
    }


    public void UpdateRecommendation(final String[] categories, final Boolean is_signup) {
        mDatabase.child("recommendation").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Recommendation recommendation = dataSnapshot.getValue(Recommendation.class);
                HashMap<String, Double> points = recommendation.getCategoryPoint();
                HashMap<String, Integer> counts = recommendation.getCategoryCount();

                if (is_signup) {
                    PointCountContainer pointCountContainer =  UpdateSignUpPoints(points, counts, categories);
                    Recommendation newR = new Recommendation(username, pointCountContainer.getPoints(), pointCountContainer.getCounts());

                    mDatabase.child("recommendation").setValue(newR);
                } else {

                    PointCountContainer pointCountContainer = UpdatePoints(points, counts, categories);
                    Recommendation newR = new Recommendation(username, pointCountContainer.getPoints(), pointCountContainer.getCounts());

                    mDatabase.child("recommendation").setValue(newR);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private PointCountContainer UpdateSignUpPoints(HashMap<String, Double> points, HashMap<String, Integer> counts, String[] categories) {
        for (Map.Entry<String, Double> kv: points.entrySet()) {
            String key = kv.getKey();
            if (Arrays.asList(categories).contains(key)) {
                Double value = kv.getValue();
                points.put(key, value + 5);
                counts.put(key, 1);
            }
        }
        return new PointCountContainer(points, counts);
    }

    private PointCountContainer UpdatePoints(HashMap<String, Double> points, HashMap<String, Integer> counts, String[] categories) {
        for (Map.Entry<String, Double> kv: points.entrySet()) {
            String key = kv.getKey();
            if (Arrays.asList(categories).contains(key)) {
                Double point = kv.getValue();
                Integer count = counts.get(key);

                PointCountContainer pointCountContainer = AddPointsByCount(point, count);

                points.put(key, pointCountContainer.getPoint());
                counts.put(key, pointCountContainer.getCount());

            }
        }

        return RefreshPoints(points, counts);

    }


    private PointCountContainer AddPointsByCount(Double point, Integer count) {
        if (count >= 0 && count < 4) {
            if (point + 2 <= maxPoint) {
                return new PointCountContainer(point + 2, count + 1);
            } else {
                return new PointCountContainer(maxPoint, count + 1);
            }
        } else if (count >= 4 && count < 9){
            if (point + 1 <= maxPoint) {
                return new PointCountContainer(point + 1, count + 1);
            } else {
                return new PointCountContainer(maxPoint, count + 1);
            }
        } else if (count >= 9 && count < 15) {
            if (point + 0.5 <= maxPoint) {
                return new PointCountContainer(point + 0.5, count + 1);
            } else {
                return new PointCountContainer(maxPoint, count + 1);
            }
        } else {
            if (point + 0.25 <= maxPoint) {
                return new PointCountContainer(point + 0.25, count + 1);
            } else {
                return new PointCountContainer(maxPoint, count + 1);
            }
        }
    }

    private PointCountContainer RefreshPoints(HashMap<String, Double> points, HashMap<String, Integer> counts) {
        Integer countMax = 0;
        for (Map.Entry<String, Double> kv: points.entrySet()) {
            if (kv.getValue() == maxPoint) {
                countMax += 1;
            }
        }

        // 3 conditions
        if (countMax < 5) {
            // change nothing
            return new PointCountContainer(points, counts);
        } else if (countMax == 9) {
            // reset to initial
            for (String key: points.keySet()) {
                points.put(key, 100.00);
                counts.put(key, 0);
            }
            return new PointCountContainer(points, counts);
        } else {
            HashMap<String, Double> cats = new HashMap<>();
            HashMap<String, Double> dogs = new HashMap<>();

            for (Map.Entry<String, Double> kv: points.entrySet()) {
                String key = kv.getKey();
                Double value = kv.getValue();
                if (value != maxPoint) {
                    cats.put(key, value);
                    counts.put(key, 15);
                } else {
                    dogs.put(key, value);
                }
            }

            // calculate new max
            Double maxInMini = Collections.max(dogs.values());
            Double newMax = (maxPoint + maxInMini) / 2;

            for (Map.Entry<String, Double> kv: cats.entrySet()) {
                cats.put(kv.getKey(), newMax);
            }

            HashMap<String, Double> newPoints = new HashMap<>();
            newPoints.putAll(cats);
            newPoints.putAll(dogs);

            return new PointCountContainer(newPoints, counts);
        }

    }


}
