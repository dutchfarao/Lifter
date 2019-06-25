package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
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
    String markerId;
    int checker;
    User tempDriver;
    String tempDrivername;
    User tempLifter;
    String tempLiftername;
    ArrayList<Liftspot> liftspots;
    ArrayList<User> driversArray;
    ArrayList<User> liftersArray;

    private static final String TAG = "DriverLifterActivity";


    @Override
    public void updatedLiftspotError(VolleyError error) {
        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();

    }

    @Override
    public void updatedLiftspot(String response) {
        Intent intent = new Intent(DriverLifterActivity.this, LiftActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("liftspots", liftspots);
        args.putString("markerId", markerId);
        args.putSerializable("userObject", userProfile);
        intent.putExtra("BUNDLE",args);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_lifter);


        //gte intent
        Intent intent = getIntent();
        liftspots = (ArrayList<Liftspot>)intent.getSerializableExtra("liftspots");
        userProfile = (User)intent.getSerializableExtra("userObject");
        Log.d(TAG, "this is the userobject before button is clicked" + userProfile);
        markerId = intent.getStringExtra("markerId");
        category = intent.getStringExtra("category");
        liftspotObject = (Liftspot)intent.getSerializableExtra("liftspotObject");
        Log.d(TAG, "this is the liftspotobject in the driverslifters activity" + liftspotObject);

        driversArray = liftspotObject.getDrivers();
        if (driversArray == null){
            driversArray = new ArrayList<>();
        }


        liftersArray = liftspotObject.getLifters();
        if (liftersArray == null){
            liftersArray = new ArrayList<>();
        }


    }

    public void add(View v) {
        if (category.equals("driver")){
            Log.d(TAG, "this is the userprofile when button is clicked" + userProfile);
            Log.d(TAG, "this is the driversarray when button is clicked" + driversArray);

            driversArray.add(userProfile);
            liftspotObject.setDrivers(driversArray);
        }
        if (category.equals("lifter")){
            liftersArray.add(userProfile);
            liftspotObject.setLifters(liftersArray);
        }
        int id = liftspotObject.getId();
        url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot" + id + "/1";
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
            checker = userChecker(driversArray, userProfile);
            if (checker != -1000){
                driversArray.remove(checker);
                liftspotObject.setDrivers(driversArray);
                Log.d(TAG, "this is the updated driversarray after removal:" + driversArray);
            }
            else{
                Toast.makeText(this, "You are not yet assigned as driver to this location", Toast.LENGTH_LONG).show();
            }
        }
        if(category.equals("lifter")){
            checker = userChecker(liftersArray, userProfile);
            if (checker != -1000){
                liftersArray.remove(checker);
                liftspotObject.setLifters(liftersArray);
            }
            else{
                Toast.makeText(this, "You are not yet assigned as lifter to this location", Toast.LENGTH_LONG).show();
            }
        }

        int id = liftspotObject.getId();
        url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot" + id + "/1";
        name = liftspotObject.getName();
        rating = liftspotObject.getRating();
        type = liftspotObject.getType();
        lat = String.valueOf(liftspotObject.getLat());
        lon = String.valueOf(liftspotObject.getLon());
        drivers = ArraytoJSON(driversArray);
        lifters = ArraytoJSON(liftersArray);

        LiftspotUpdater remove = new LiftspotUpdater(this, url, name, rating, type, lat, lon, drivers, lifters);
        remove.updateLiftspot(this);


    }

    String ArraytoJSON(ArrayList<User> input){
        String json = new Gson().toJson(input);
        return(json);
    }

    //i implemented this userchecker because arraylist.contains didn't work at all.
    int userChecker(ArrayList<User> input, User user) {
        int value = 0;
        for (int x = 0; x < input.size(); x++) {

            User tempInput = input.get(x);
            String imputName = tempInput.getName();
            String userName = user.getName();
            if (imputName.equals(userName)) {
                value = x;
            } else {
                value = -1000;
            }
        }
        return value;
    }

}
