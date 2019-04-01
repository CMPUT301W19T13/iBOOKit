package com.example.ibookit.Functionality;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author zijun wu
 *
 * @version 1.1
 */
public class Singleton {

    private String username;
    private DatabaseReference userDatabase;
    private DatabaseReference allDatabase;

    /**
     * Constructor
     */
    public Singleton(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        this.allDatabase = FirebaseDatabase.getInstance().getReference();
        if (user != null) {
            this.username = user.getDisplayName();
            this.userDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DatabaseReference getUserDatabase() {
        return userDatabase;
    }

    public void setUserDatabase(DatabaseReference userDatabase) {
        this.userDatabase = userDatabase;
    }

    public DatabaseReference getAllDatabase() {
        return allDatabase;
    }

    public void setAllDatabase(DatabaseReference allDatabase) {
        this.allDatabase = allDatabase;
    }
}
