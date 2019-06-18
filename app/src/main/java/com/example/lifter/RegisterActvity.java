package com.example.lifter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

public class RegisterActvity extends AppCompatActivity implements UserUploader.Callback {

    EditText name;
    EditText username;
    EditText password;
    EditText age;
    EditText city;
    EditText car;
    EditText bio;
    String inputname;
    String inputagestring;
    int inputage;
    String inputcity;
    String inputcar;
    String inputbio;
    String inputusername;
    String inputpassword;


    // Shows the user a warning if an error is encountered during the uploading of the score
    @Override
    public void postedUserError(VolleyError error) {
        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();
    }

    // Shows the user a toast if the score is successfully uploaded to the database
    @Override
    public void postedUser(String response) {
        Toast.makeText(this, "Account had been created!", Toast.LENGTH_LONG).show();

        // Directs user to the next activity using Intent
        Intent intent = new Intent(RegisterActvity.this, MapsActivity.class);
        startActivity(intent);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        city = findViewById(R.id.RegisterWoonplaats);
        car = findViewById(R.id.RegisterCar);
        bio = findViewById(R.id.RegisterBio);
        name = findViewById(R.id.RegisterNaam);
        age = findViewById(R.id.RegisterLeeftijd);
        username = findViewById(R.id.RegisterUsername);
        password = findViewById(R.id.RegisterPassword);





    }



    public void submit(View v) {

//        inputname = String.valueOf(name.getText());
//            inputagestring = String.valueOf(age.getText());
//            inputage = Integer.valueOf(inputagestring);
//            inputcity = String.valueOf(city.getText());
//            inputcar = String.valueOf(car.getText());
//            inputbio = String.valueOf(bio.getText());
         inputusername = String.valueOf(username.getText());
         inputpassword = String.valueOf(password.getText());

        UserUploader register = new UserUploader(this, inputusername, inputpassword);
        register.sendUser(this);




    }

}
