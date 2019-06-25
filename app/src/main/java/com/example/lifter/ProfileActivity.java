package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    TextView ProfileUsername;
    TextView ProfileName;
    TextView ProfileCity;
    TextView ProfileAge;
    TextView ProfileCar;
    TextView ProfileBio;
    Button btn;
    String markerId;
    ArrayList<Liftspot> liftspots;
    Liftspot liftspotObject;
    User userProfile;

    private static final String TAG = "LiftActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        liftspots = (ArrayList<Liftspot>)intent.getSerializableExtra("liftspots");
        liftspotObject = (Liftspot)intent.getSerializableExtra("liftspotObject");
        markerId = intent.getStringExtra("markerId");
        final User userProfile = (User) intent.getSerializableExtra("clickedUser");
        Log.d(TAG, "userprofile" + userProfile);

        btn = findViewById(R.id.ProfileButton);
        onClick listener = new onClick();
        btn.setOnClickListener(listener);



        //asign edittexts
        ProfileUsername = findViewById(R.id.ProfileUsername);
        ProfileName = findViewById(R.id.ProfileNaam);
        ProfileCity = findViewById(R.id.ProfileWoonplaats);
        ProfileAge = findViewById(R.id.ProfileLeeftijd);
        ProfileCar = findViewById(R.id.ProfileCar);
        ProfileBio = findViewById(R.id.ProfileBio);
        //set edittexts
        ProfileUsername.setText(userProfile.getUsername());
        ProfileName.setText(userProfile.getName());
        ProfileCity.setText(userProfile.getCity());
        ProfileAge.setText(String.valueOf(userProfile.getAge()));
        ProfileCar.setText(userProfile.getCar());
        ProfileBio.setText(userProfile.getBio());

    }

    //creation of onclick for button
    public class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProfileActivity.this, LiftActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("liftspots", liftspots);
            args.putString("markerId", markerId);
            args.putSerializable("userObject", userProfile);
            intent.putExtra("BUNDLE",args);
            startActivity(intent);
        }
    }
}
