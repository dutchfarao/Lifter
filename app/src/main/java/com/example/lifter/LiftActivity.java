package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class LiftActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
    TextView LiftTitle;
    RatingBar LiftRating;
    StreetViewPanorama streetView;
    Liftspot liftspotObject;
    private static final String STREETVIEW_BUNDLE = "StreetViewBundle";
    private StreetViewPanoramaView g_map_street;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);

        //code based on https://www.zoftino.com/android-google-map-street-view-example
        StreetViewPanoramaFragment streetViewFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.g_map_street);
        streetViewFragment.getStreetViewPanoramaAsync(this);
        streetView.setPosition(new LatLng(liftspotObject.getLat(), liftspotObject.getLon()));
        streetView.setStreetNamesEnabled(true);


        //get intent
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Liftspot> liftspots = (ArrayList<Liftspot>) args.getSerializable("liftspots");
        String liftspotstring = args.getString("markerId");
        int liftspotId = Integer.valueOf(liftspotstring);
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
        streetView = streetViewPanorama;


    }


    //creation of onclick for button
    public class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LiftActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    }
}




