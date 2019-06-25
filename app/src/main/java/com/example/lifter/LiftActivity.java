package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import java.util.ArrayList;

public class LiftActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {




    TextView LiftTitle;
    RatingBar LiftRating;
    StreetViewPanorama streetView;
    String markerId;
    User userObject;
    Liftspot liftspotObject;
    Liftspot tempLiftspot;
    String tempLiftspotname;
    GridView driversLayout;
    GridView liftersLayout;
    ArrayList<User> driversArray;
    ArrayList<User> liftersArray;
    int liftspotId;
    private static final String STREETVIEW_BUNDLE = "StreetViewBundle";
    StreetViewPanoramaFragment g_map_street;
    ArrayList<Liftspot> liftspots;
    float lat;
    float lon;
    private static final String TAG = "LiftActivity";

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            User clickedUser = (User) parent.getItemAtPosition(position);
            Intent intent = new Intent(LiftActivity.this, ProfileActivity.class);
            intent.putExtra("clickedUser", clickedUser);
            intent.putExtra("liftspots", liftspots);
            intent.putExtra("liftspotObject", liftspotObject);
            intent.putExtra("markerId", markerId);
            startActivity(intent);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);

        driversLayout = findViewById(R.id.DriverLinearLayout);
        liftersLayout = findViewById(R.id.LifterLinearLayout);

        //setting listeners for the drivers and lifters
        //initialising GridItemListener and calling it
        GridItemClickListener profileClick = new GridItemClickListener();
        driversLayout.setOnItemClickListener(profileClick);
        liftersLayout.setOnItemClickListener(profileClick);

        //based on https://developers.google.com/maps/documentation/android-sdk/streetview
        g_map_street =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.g_map_street);
        g_map_street.getStreetViewPanoramaAsync(this);

        //get intent
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        liftspots = (ArrayList<Liftspot>)args.getSerializable("liftspots");
        Log.d(TAG, "liftspotsarray" + liftspots);
        markerId = args.getString("markerId");
        Log.d(TAG, "liftspotId" + markerId);
        for (int i = 0; i < liftspots.size(); i++){
            tempLiftspot = liftspots.get(i);
            tempLiftspotname = tempLiftspot.getName();
            if (markerId.equals(tempLiftspotname)){
                liftspotObject = tempLiftspot;
            }
        }
        driversArray = liftspotObject.getDrivers();
        Log.d(TAG, "dit is de driversarray" + driversArray);
        liftersArray = liftspotObject.getLifters();


        userObject = (User)args.getSerializable("userObject");
        Log.d(TAG, "liftspotobject" + liftspotObject);
        Log.d(TAG, "liftspotId" + liftspotObject.getId());



        //set textviews and rating
        LiftTitle = findViewById(R.id.LiftTitel);
        LiftRating = findViewById(R.id.LiftRatingbar);
        //set title
        LiftTitle.setText(liftspotObject.getName());
        //set ratingbar
        switch (liftspotObject.getRating()) {
            case "Bad":
                LiftRating.setRating(Float.parseFloat("2.0"));
                break;
            case "Average":
                LiftRating.setRating(Float.parseFloat("3.0"));
                break;
            case "Good":
                LiftRating.setRating(Float.parseFloat("4.0"));
                break;
            case "Great":
                LiftRating.setRating(Float.parseFloat("5.0"));
                break;
        }


        //create listener for login button
        Button btn = findViewById(R.id.LiftHomeButton);
        onClick onButtonClick = new onClick();
        btn.setOnClickListener(onButtonClick);

        //implementing the adapter for the separate grid items
        ProfileAdapter adapter = new ProfileAdapter(this, R.layout.grid_item, driversArray);
        Log.d(TAG, "driversArray" + driversArray);
        driversLayout.setAdapter(adapter);

        //implementing the adapter for the separate grid items
        ProfileAdapter adapter2 = new ProfileAdapter(this, R.layout.grid_item, liftersArray);
        liftersLayout.setAdapter(adapter2);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        lat = liftspotObject.getLat();
        lon = liftspotObject.getLon();
        streetViewPanorama.setPosition(new LatLng(lat,lon));

        //streetView = streetViewPanorama;
    }

    @Override
    protected void onResume() {
        g_map_street.onResume();


        super.onResume();
    }

    @Override
    protected void onPause() {
        g_map_street.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        g_map_street.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mStreetViewBundle = outState.getBundle(STREETVIEW_BUNDLE);
        if (mStreetViewBundle == null) {
            mStreetViewBundle = new Bundle();
            outState.putBundle(STREETVIEW_BUNDLE, mStreetViewBundle);
        }

        g_map_street.onSaveInstanceState(mStreetViewBundle);
    }

    public void addDriver(View v) {
        Intent intent = new Intent(LiftActivity.this, DriverLifterActivity.class);
        intent.putExtra("userObject", userObject);
        intent.putExtra("liftspotObject", liftspotObject);
        intent.putExtra("liftspots", liftspots);
        intent.putExtra("markerId", markerId);
        intent.putExtra("category", "driver");
        startActivity(intent);
    }

    public void addLifter(View v) {
        Intent intent = new Intent(LiftActivity.this, DriverLifterActivity.class);
        intent.putExtra("userObject", userObject);
        intent.putExtra("liftspotObject", liftspotObject);
        intent.putExtra("liftspots", liftspots);
        intent.putExtra("markerId", markerId);
        intent.putExtra("category", "lifter");
        startActivity(intent);
    }


    //creation of onclick for button
    public class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LiftActivity.this, MapsActivity.class);
            intent.putExtra("userObject", userObject);
            startActivity(intent);
        }
    }
}




