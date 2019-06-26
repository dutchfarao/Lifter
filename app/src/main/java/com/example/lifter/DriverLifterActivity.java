package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import java.util.ArrayList;

public class DriverLifterActivity extends AppCompatActivity implements LiftspotUpdater.Callback {

    /**

     This activity handles the addition and removal of users as drivers or lifters to a specific liftspot.

     */

    User userProfile;
    Liftspot liftspotObject;
    String category;
    String url;
    String name;
    String rating;
    String type;
    String lat;
    String lon;
    String drivers;
    String lifters;
    String markerId;
    int checker;
    ArrayList<Liftspot> liftspots;
    ArrayList<User> driversArray;
    ArrayList<User> liftersArray;


    @Override
    public void updatedLiftspotError(VolleyError error) {
        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();

    }

    @Override
    public void updatedLiftspot(String response) {
        //finish the activity, user is sent back to LiftActivty.
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_lifter);


        //get intent
        Intent intent = getIntent();
        userProfile = (User)intent.getSerializableExtra("userObject");
        category = intent.getStringExtra("category");
        liftspotObject = (Liftspot)intent.getSerializableExtra("liftspotObject");

        //set driversarray, create a new one if driversarray is null
        driversArray = liftspotObject.getDrivers();
        if (driversArray == null){
            driversArray = new ArrayList<>();
        }

        //set liftersarray, create a new one if driversarray is null
        liftersArray = liftspotObject.getLifters();
        if (liftersArray == null){
            liftersArray = new ArrayList<>();
        }
    }

    public void add(View v) {

        /**
         Adds a user to driversarray or liftersarray, depends on what category user has chosen in previous view (driver or lifter)
         */

        if (category.equals("driver")){
            checker = userChecker(driversArray, userProfile, "add");
            // checks if user is not already assigned to location
            if(checker != -1000) {
                //add user to driversarray and update liftspotobject
                driversArray.add(userProfile);
                liftspotObject.setDrivers(driversArray);
            }
            else{
                Toast.makeText(this, "You are already assigned as driver to this location", Toast.LENGTH_LONG).show();
            }
        }

        if (category.equals("lifter")) {
            checker = userChecker(liftersArray, userProfile, "add");
            // checks if user is not already assigned to location
            if (checker != -1000) {
                //add user to liftersarray and update liftspotobject
                liftersArray.add(userProfile);
                liftspotObject.setLifters(liftersArray);
            }
            else{
                Toast.makeText(this, "You are already assigned as lifter to this location", Toast.LENGTH_LONG).show();
            }
        }

        //update static liftspotobject, later used in LiftActivity
        LiftActivity.liftspotObject = liftspotObject;

        //set all values used for PUT request
        int id = liftspotObject.getId();
        url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot" + id + "/1";
        name = liftspotObject.getName();
        rating = liftspotObject.getRating();
        type = liftspotObject.getType();
        lat = String.valueOf(liftspotObject.getLat());
        lon = String.valueOf(liftspotObject.getLon());
        drivers = ArraytoJSON(driversArray);
        lifters = ArraytoJSON(liftersArray);

        // PUT request
        LiftspotUpdater add = new LiftspotUpdater(this, url, name, rating, type, lat, lon, drivers, lifters);
        add.updateLiftspot(this);
    }

    public void remove(View v) {

        /**
         Removes a user from driversarray or liftersarray, depends on what category user has chosen in previous view (driver or lifter)
         */

        if (category.equals("driver")){

            checker = userChecker(driversArray, userProfile, "remove");
            // checks if user is assigned to location
            if (checker != -1000){
                //remove user from driversarray and update liftspotobject
                driversArray.remove(checker);
                liftspotObject.setDrivers(driversArray);
            }
            else{
                Toast.makeText(this, "You are not yet assigned as driver to this location", Toast.LENGTH_LONG).show();
            }
        }

        if(category.equals("lifter")){
            checker = userChecker(liftersArray, userProfile, "remove");
            // checks if user is assigned to location
            if (checker != -1000){
                //remove user from liftersarray and update liftspotobject
                liftersArray.remove(checker);
                liftspotObject.setLifters(liftersArray);
            }
            else{
                Toast.makeText(this, "You are not yet assigned as lifter to this location", Toast.LENGTH_LONG).show();
            }
        }

        //update static liftspotobject, later used in LiftActivity
        LiftActivity.liftspotObject = liftspotObject;

        //set all values used for PUT request
        int id = liftspotObject.getId();
        url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot" + id + "/1";
        name = liftspotObject.getName();
        rating = liftspotObject.getRating();
        type = liftspotObject.getType();
        lat = String.valueOf(liftspotObject.getLat());
        lon = String.valueOf(liftspotObject.getLon());
        drivers = ArraytoJSON(driversArray);
        lifters = ArraytoJSON(liftersArray);

        //PUT request
        LiftspotUpdater remove = new LiftspotUpdater(this, url, name, rating, type, lat, lon, drivers, lifters);
        remove.updateLiftspot(this);
    }


    String ArraytoJSON(ArrayList<User> input){
        /**
         Converts Arraylist to JSON string
         */
        String json = new Gson().toJson(input);
        return(json);
    }

    //I implemented this userchecker because arraylist.contains didn't work at all.
    int userChecker(ArrayList<User> input, User user, String category) {
        /**
         Checks if a specified arraylist contains a user object, returns -1000 if an error should occur
         */
        int value = 0;
        //if arraylist is empty and users wants ro remove itself, return -1000
        if (input.size() == 0 && category.equals("remove")){
            value = -1000;
        }
        //else check if user object is in arraylist
        else {
            for (int x = 0; x < input.size(); x++) {
                //iterate trough arraylist and compare .getName of object and inputted user
                User tempInput = input.get(x);
                String imputName = tempInput.getName();
                String userName = user.getName();
                if (imputName.equals(userName) ) {
                    // if user is found, user may be removed
                    if(category.equals("remove")){
                        value = x;
                    }
                    //if user if found, user may not be added
                    if(category.equals("add")){
                        value = -1000;
                    }
                }
                else {
                    //if user is not found, user may be added
                    if(category.equals("add")){
                        value = x;
                    }
                    //if user is not found, user may not be removed
                    if(category.equals("remove")){
                        value = -1000;
                    }
                }
            }
        }
        return value;
    }

}
