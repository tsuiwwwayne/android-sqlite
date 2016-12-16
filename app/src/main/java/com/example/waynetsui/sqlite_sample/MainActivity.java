package com.example.waynetsui.sqlite_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "COMP211P";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);

        // Inserting Test Users
        Log.d(TAG, "Inserting users...");
        db.addUser(new User("Bernard", "bernard@email.com", "Host"));
        db.addUser(new User("Dolores", "dolores@email.com", "Wyatt"));
        db.addUser(new User("Teddy", "teddy@email.com", "Bear"));
        db.addUser(new User("Clementine", "clementine@email.com", "Stillstandingthere"));

        // Get total number of users
        int totalNumberOfUsers = db.getUsersCount();
        Log.d(TAG, "Total users: " + totalNumberOfUsers);

        // Reading all users
        Log.d(TAG, "Reading all users...");
        ArrayList<User> users = db.getAllUsers();

        for (User user : users) {
            String log = "Name: " + user.getUserName() + " , Email: " + user.getEmail() + " , Password: " + user.getPassword();
            // Writing users to log
            Log.d(TAG, log);
        }

        Log.d(TAG, "-----------------------------------------------");

        // Update user
        Log.d(TAG, "Updating a user...");
        User changedUser = db.getUser("Teddy");
        changedUser.setPassword("NotBear");
        db.updateUser(changedUser);

        // Delete a user
        Log.d(TAG, "Deleting a user...");
        db.deleteUser(db.getUser("Clementine"));

        // Re-read all users
        Log.d(TAG, "Re-reading all users...");
        ArrayList<User> users2 = db.getAllUsers();

        for (User user : users2) {
            String log = "Name: " + user.getUserName() + " , Email: " + user.getEmail() + " , Password: " + user.getPassword();
            // Writing users to log
            Log.d(TAG, log);
        }

        Log.d(TAG, "-----------------------------------------------");

        // Get stored users in database
        User retrievedUser = db.getUser("Bernard");
        User retrievedUser2 = db.getUser("Dolores");

        // Inserting Test Attempts
        int testScore = 8;
        int testScore2 = 10;
        Log.d(TAG, "Inserting attempts...");
        db.addAttempt(new Attempt(retrievedUser.getUserName(), testScore));
        db.addAttempt(new Attempt(retrievedUser2.getUserName(), testScore2));

        // Get total number of attempts
        int totalNumberOfAttempts = db.getAttemptsCount();
        Log.d(TAG, "Total attempts: " + totalNumberOfAttempts);

        // Reading all attempts
        Log.d(TAG, "Reading all attempts...");
        ArrayList<Attempt> attempts = db.getAllAttempts();

        for (Attempt attempt : attempts) {
            String log = "Name: " + attempt.getUserName() + " , Score: " + attempt.getScore();
            // Writing attempts to log
            Log.d(TAG, log);
        }

    }
}
