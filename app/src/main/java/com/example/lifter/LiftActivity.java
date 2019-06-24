package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
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
    String liftspotstring;
    User userObject;
    Liftspot liftspotObject;
    int liftspotId;
    private static final String STREETVIEW_BUNDLE = "StreetViewBundle";
    StreetViewPanoramaFragment g_map_street;
    float lat;
    float lon;
    private static final String TAG = "LiftActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);

        //based on https://developers.google.com/maps/documentation/android-sdk/streetview
        g_map_street =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.g_map_street);
        g_map_street.getStreetViewPanoramaAsync(this);

        //get intent
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Liftspot> liftspots = (ArrayList<Liftspot>)args.getSerializable("liftspots");
        Log.d(TAG, "liftspotsarray" + liftspots);
        liftspotstring = args.getString("markerId");
        liftspotId = Integer.valueOf(liftspotstring);
        Log.d(TAG, "liftspotId" + liftspotId);

        userObject = (User)args.getSerializable("userObject");
        liftspotObject = liftspots.get(liftspotId);


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
        intent.putExtra("category", "driver");
        startActivity(intent);
    }

    public void addLifter(View v) {
        Intent intent = new Intent(LiftActivity.this, DriverLifterActivity.class);
        intent.putExtra("userObject", userObject);
        intent.putExtra("liftspotObject", liftspotObject);
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




