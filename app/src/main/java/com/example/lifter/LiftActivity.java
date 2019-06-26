package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class LiftActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    /**

     This activity shows user a selected liftspot, including drivers and lifters that have submitted themselves to that liftspot.

     */

    TextView LiftTitle;
    RatingBar LiftRating;
    String markerId;
    User userObject;
    public static Liftspot liftspotObject;
    Liftspot tempLiftspot;
    String tempLiftspotname;
    GridView driversLayout;
    GridView liftersLayout;
    ArrayList<User> driversArray;
    ArrayList<User> liftersArray;
    private static final String STREETVIEW_BUNDLE = "StreetViewBundle";
    StreetViewPanoramaFragment g_map_street;
    ArrayList<Liftspot> liftspots;
    float lat;
    float lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);
        //set driver and lifter gridlayout
        driversLayout = findViewById(R.id.DriverLinearLayout);
        liftersLayout = findViewById(R.id.LifterLinearLayout);

        //initialising GridItemListener and setting it to layouts
        GridItemClickListener profileClick = new GridItemClickListener();
        driversLayout.setOnItemClickListener(profileClick);
        liftersLayout.setOnItemClickListener(profileClick);

        //based on https://developers.google.com/maps/documentation/android-sdk/streetview
        g_map_street =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.g_map_street);
        g_map_street.getStreetViewPanoramaAsync(this);

        //get intent from MapActivity
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        liftspots = (ArrayList<Liftspot>)args.getSerializable("liftspots");
        userObject = (User)args.getSerializable("userObject");
        markerId = args.getString("markerId");
        //determine which liftspot is selected by comparing markerId to id's of liftspots during iteration
        for (int i = 0; i < liftspots.size(); i++){
            tempLiftspot = liftspots.get(i);
            tempLiftspotname = tempLiftspot.getName();
            if (markerId.equals(tempLiftspotname)){
                //setting correct liftspotobject
                liftspotObject = tempLiftspot;
            }
        }
        //setting driversarray and liftersarray used for GridLayouts
        driversArray = liftspotObject.getDrivers();
        liftersArray = liftspotObject.getLifters();

        //set title
        LiftTitle = findViewById(R.id.LiftTitel);
        LiftTitle.setText((Html.fromHtml(liftspotObject.getName())));
        //set rating
        LiftRating = findViewById(R.id.LiftRatingbar);
        //a rating is always a string (bad, average, good or great), set star rating according to that string.
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
        driversLayout.setAdapter(adapter);

        //implementing the adapter for the separate grid items
        ProfileAdapter adapter2 = new ProfileAdapter(this, R.layout.grid_item, liftersArray);
        liftersLayout.setAdapter(adapter2);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        //set latitude and longitude for correct streetview
        lat = liftspotObject.getLat();
        lon = liftspotObject.getLon();
        streetViewPanorama.setPosition(new LatLng(lat,lon));
    }

    @Override
    protected void onResume() {
        g_map_street.onResume();
        driversArray = liftspotObject.getDrivers();
        liftersArray = liftspotObject.getLifters();

        //implementing the adapter for the separate grid items
        ProfileAdapter adapter = new ProfileAdapter(this, R.layout.grid_item, driversArray);
        driversLayout.setAdapter(adapter);

        //implementing the adapter for the separate grid items
        ProfileAdapter adapter2 = new ProfileAdapter(this, R.layout.grid_item, liftersArray);
        liftersLayout.setAdapter(adapter2);

        super.onResume();
    }

    @Override
    protected void onPause() {
        g_map_street.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
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
        /**
        Gives intent to DriverLifterActivity, determines that a user wants to change it's driverstatus
         */
        Intent intent = new Intent(LiftActivity.this, DriverLifterActivity.class);
        intent.putExtra("userObject", userObject);
        intent.putExtra("liftspotObject", liftspotObject);
        //set category to driver
        intent.putExtra("category", "driver");
        startActivity(intent);
    }

    public void addLifter(View v) {
        /**
         Gives intent to DriverLifterActivity, determines that a user wants to change it's lifterstatus
         */
        Intent intent = new Intent(LiftActivity.this, DriverLifterActivity.class);
        intent.putExtra("userObject", userObject);
        intent.putExtra("liftspotObject", liftspotObject);
        //set category to lifter
        intent.putExtra("category", "lifter");
        startActivity(intent);
    }


    public class onClick implements View.OnClickListener {
        /**
         This OnClick handles a click from a user and ends the activity, thus directing user to MapActivity
         */
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        /**
         This GridItemClickListener handles a click from a user on another submitted user, and leads them to the profile page of the clicked user.
         */

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            User clickedUser = (User) parent.getItemAtPosition(position);
            Intent intent = new Intent(LiftActivity.this, ProfileActivity.class);
            intent.putExtra("clickedUser", clickedUser);
            startActivity(intent);
        }
    }
}




