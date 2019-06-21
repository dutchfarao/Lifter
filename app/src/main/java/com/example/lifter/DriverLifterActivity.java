package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;

public class DriverLifterActivity extends AppCompatActivity implements LiftspotUpdater.Callback {
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
    ArrayList<User> driversArray;
    ArrayList<User> liftersArray;

    @Override
    public void updatedLiftspotError(VolleyError error) {
        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();

    }

    @Override
    public void updatedLiftspot(String response) {
        Toast.makeText(this, "You status as a " + category + "to this location (" + liftspotObject.getName() + ") has been updated!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DriverLifterActivity.this, LiftActivity.class);
        intent.putExtra("userObject", userProfile);
        intent.putExtra("liftspotObject", liftspotObject);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_lifter);
        //gte intent
        Intent intent = getIntent();
        userProfile = (User)intent.getSerializableExtra("userObject");
        category = intent.getStringExtra("category");
        liftspotObject = (Liftspot)intent.getSerializableExtra("liftspotObject");
        driversArray = liftspotObject.getDrivers();
        liftersArray = liftspotObject.getLifters();
    }

    public void add(View v) {
        if (category.equals("driver")){
            driversArray.add(userProfile);
            liftspotObject.setDrivers(driversArray);
        }
        if (category.equals("lifter")){
            liftersArray.add(userProfile);
            liftspotObject.setLifters(liftersArray);
        }
        String id = String.valueOf(liftspotObject.getId());
        url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot/" + id;
        name = liftspotObject.getName();
        rating = liftspotObject.getRating();
        type = liftspotObject.getType();
        lat = String.valueOf(liftspotObject.getLat());
        lon = String.valueOf(liftspotObject.getLon());
        drivers = ArraytoJSON(driversArray);
        lifters = ArraytoJSON(liftersArray);

        LiftspotUpdater add = new LiftspotUpdater(this, url, name, rating, type, lat, lon, drivers, lifters);
        add.updateLiftspot(this);
    }

    public void remove(View v) {
        if (category.equals("driver")){
            if (!driversArray.contains(userProfile)){
                Toast.makeText(this, "You are not yet assigned as driver to this location", Toast.LENGTH_LONG).show();

            }
            else{
                driversArray.remove(userProfile);
                liftspotObject.setDrivers(driversArray);
            }
        }
        if(category.equals("lifter")){
            if (!liftersArray.contains(userProfile)){
                Toast.makeText(this, "You are not yet assigned as driver to this location", Toast.LENGTH_LONG).show();
            }
            else{
                liftersArray.remove(userProfile);
                liftspotObject.setLifters(liftersArray);
            }
        }

        String id = String.valueOf(liftspotObject.getId());
        url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot/" + id;
        name = liftspotObject.getName();
        rating = liftspotObject.getRating();
        type = liftspotObject.getType();
        lat = String.valueOf(liftspotObject.getLat());
        lon = String.valueOf(liftspotObject.getLon());
        drivers = ArraytoJSON(driversArray);
        lifters = ArraytoJSON(liftersArray);

        LiftspotUpdater add = new LiftspotUpdater(this, url, name, rating, type, lat, lon, drivers, lifters);
        add.updateLiftspot(this);


    }

    String ArraytoJSON(ArrayList<User> input){
        String json = new Gson().toJson(input);
        return(json);
    }

}
