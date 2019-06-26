package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.VolleyError;

public class MyProfileActivity extends AppCompatActivity implements UserUpdater.Callback{

    /**

     This activity shows the user it's own profile, it is also used to edit and update the user object.

     */
    User userProfile;
    EditText MyprofileUsername;
    EditText MyprofilePassword;
    EditText MyprofileName;
    EditText MyprofileCity;
    EditText MyprofileAge;
    EditText MyprofileCar;
    EditText MyprofileBio;
    String inputname;
    String inputage;
    String inputcity;
    String inputcar;
    String inputbio;
    String inputusername;
    String inputpassword;
    String id;
    String url;

    // Shows the user a warning if an error is encountered during the uploading of the score
    @Override
    public void updatedUserError(VolleyError error) {
        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();
    }

    // Shows the user a toast if the score is successfully uploaded to the database
    @Override
    public void updatedUser(String response) {
        Toast.makeText(this, "Your account has been updated!", Toast.LENGTH_LONG).show();

        //update user object
        userProfile.setUsername(inputusername);
        userProfile.setPassword(inputpassword);
        userProfile.setName(inputname);
        userProfile.setCity(inputcity);
        userProfile.setAge(Integer.valueOf(inputage));
        userProfile.setCar(inputcar);
        userProfile.setBio(inputbio);

        //update static object
        MapsActivity.userProfile = userProfile;

        // finish activity when account had been updated
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        Intent intent = getIntent();
        userProfile = (User)intent.getSerializableExtra("userObject");

        //asign edittexts
        MyprofileUsername = findViewById(R.id.MyProfileUsername);
        MyprofilePassword = findViewById(R.id.MyProfilePassword);
        MyprofileName = findViewById(R.id.MyProfileNaam);
        MyprofileCity = findViewById(R.id.MyProfileWoonplaats);
        MyprofileAge = findViewById(R.id.MyProfileLeeftijd);
        MyprofileCar = findViewById(R.id.MyProfileCar);
        MyprofileBio = findViewById(R.id.MyProfileBio);
        //set edittexts
        MyprofileUsername.setText(userProfile.getUsername());
        MyprofilePassword.setText(userProfile.getPassword());
        MyprofileName.setText(userProfile.getName());
        MyprofileCity.setText(userProfile.getCity());
        MyprofileAge.setText(String.valueOf(userProfile.getAge()));
        MyprofileCar.setText(userProfile.getCar());
        MyprofileBio.setText(userProfile.getBio());

    }

    public void update(View v) {
        inputname = String.valueOf(MyprofileName.getText());
        inputage = String.valueOf(MyprofileAge.getText());
        inputcity = String.valueOf(MyprofileCity.getText());
        inputcar = String.valueOf(MyprofileCar.getText());
        inputbio = String.valueOf(MyprofileBio.getText());
        inputusername = String.valueOf(MyprofileUsername.getText());
        inputpassword = String.valueOf(MyprofilePassword.getText());
        id = String.valueOf(userProfile.getUserId());
        url = "https://ide50-dutchfarao.legacy.cs50.io:8080/kak/" + id;

        //PUT request
        UserUpdater update = new UserUpdater(this, url, id, inputusername, inputpassword, inputname, inputcity, inputage, inputcar, inputbio);
        update.updateUser(this);
    }
}
