package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.io.InputStream;
import java.util.ArrayList;

public class DriverLifterActivity extends AppCompatActivity implements LiftspotUpdater.Callback {
    User userProfile;
    Liftspot liftspotObject;
    String category;


    @Override
    public void updatedLiftspotError(VolleyError error) {
        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();

    }

    @Override
    public void updatedLiftspot(String response) {
        Toast.makeText(this, "You have been added as a " + category + "to this location! (" + liftspotObject.getName() + ")", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DriverLifterActivity.this, LiftActivity.class);
        intent.putExtra("userObject", userProfile);
        intent.putExtra("lifspotObject", liftspotObject);
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
    }

    public void add(View v) {

    }

    public void remove(View v) {

    }
}
