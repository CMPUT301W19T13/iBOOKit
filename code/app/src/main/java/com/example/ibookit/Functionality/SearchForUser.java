package com.example.ibookit.Functionality;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ibookit.Model.Book;
import com.example.ibookit.Model.User;
import com.example.ibookit.View.HomeSearchActivity;
import com.example.ibookit.View.MainActivity;
import com.example.ibookit.View.MyShelfOwnerActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class SearchForUser implements Search {

    private String keyword;
    private ArrayList<User> result = new ArrayList<User>();
    public SearchForUser(String keyword){
        this.keyword = keyword;
    }
    public SearchForUser(){}

    //todo:searches cannot handle more than one word at present

//    @Override
    //todo:solve the problem that forces user to re-signIn to search for other user.
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //due to bug with google firebaseAuth, you have to sign out and sign in again before testing this code
    //details: https://stackoverflow.com/questions/40683510/displayname-showing-null-after-creating-user-with-firebase-auth
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void searchByKeyword(final String mKeyword, final ArrayList<User> result, final ArrayAdapter<User> adapter) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users");

        //todo: this is just a temporary solution, I will be looking for more advanced solution(like filtering),
        //I will use this solution just for demoing for part 4
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        if (d.getKey().toLowerCase().contains
                                (mKeyword.replaceAll("\\s+","").toLowerCase())){
                            User temp = d.getValue(User.class);
                            result.add(temp);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<User> getResult() {
        return result;
    }

}
