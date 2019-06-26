package com.example.lifter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.InputStream;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LiftspotDownloader.Callback {

    /**

     This activity can be seen as the homepage. From this activity users can navigate to liftspots and their own userprofile.

     */

    private GoogleMap mMap;
    public static User userProfile;
    String markerId;
    Liftspot liftspot;
    ArrayList<Liftspot> retrievedLiftspots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        userProfile = (User)intent.getSerializableExtra("userObject");
        // download liftspots
        retrievedLiftspots = new ArrayList<>();
        //run csv helper to get number of liftspots
        InputStream inputStream = getResources().openRawResource(R.raw.liftplekken);
        CSVHelper csvFile = new CSVHelper(inputStream);
        final ArrayList<Liftspot> liftspots;
        liftspots = csvFile.read();

        //download liftspots, one for one.
        int counter;
        for (counter = 0; counter < liftspots.size(); counter ++) {
            LiftspotDownloader download = new LiftspotDownloader(this);
            download.getLiftspots(this, counter);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //create listener for floating action button
        FloatingActionButton btn = findViewById(R.id.MapsFloatingActionButton);
        onClick onButtonClick = new onClick();
        btn.setOnClickListener(onButtonClick);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //creation of onclick for floating action button
    private class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MapsActivity.this, MyProfileActivity.class);
            intent.putExtra("userObject", userProfile);
            startActivity(intent);
        }
    }

    @Override
    public void gotLiftspots(Liftspot liftspot1) {
        //add liftspot to ArrayList of liftspots
        liftspot = liftspot1;
        retrievedLiftspots.add(liftspot);
        //set marker on map
        LatLng position = new LatLng(liftspot.getLat(), liftspot.getLon());
        Marker marker = mMap.addMarker(new MarkerOptions().position(position).title(String.valueOf(liftspot.getName())));
        marker.setTag(position);
    }

    @Override
    public void gotLiftspotsError(String message) {
        // Informs the user if an error occurred while logging in
        Toast.makeText(this , "Something went wrong, couldn't load liftspots", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //set listener for each marker
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //set intent
                Intent intent = new Intent(MapsActivity.this, LiftActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("liftspots", retrievedLiftspots);
                markerId = marker.getTitle();
                args.putString("markerId", markerId);
                args.putSerializable("userObject", userProfile);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
                return true;
            }
        });

        //position the camera with the Netherlands as center
        LatLng center = new LatLng(52.18, 5.70);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(7).bearing(0).tilt(0).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
