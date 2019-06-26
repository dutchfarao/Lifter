package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class ProfileActivity extends AppCompatActivity {

    /**

     This activity shows the profile of another user, after that user has been selected in LiftActivity.

     */

    TextView ProfileUsername;
    TextView ProfileName;
    TextView ProfileCity;
    TextView ProfileAge;
    TextView ProfileCar;
    TextView ProfileBio;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //get intent
        Intent intent = getIntent();
        final User userProfile = (User) intent.getSerializableExtra("clickedUser");

        //set listener on button
        btn = findViewById(R.id.ProfileButton);
        onClick listener = new onClick();
        btn.setOnClickListener(listener);

        //assign edittexts
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
            //finish activity, user is directed to LiftActivity
            finish();
        }
    }
}
