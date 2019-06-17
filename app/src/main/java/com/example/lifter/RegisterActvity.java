package com.example.lifter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActvity extends AppCompatActivity {

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



        //create listener for login button
        Button btn = findViewById(R.id.RegisterButton);
        onClick onButtonClick = new onClick();
        btn.setOnClickListener(onButtonClick);
    }

    //creation of onclick for button
    private class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {


            //get values
            Context context = v.getContext();

            inputname = String.valueOf(name.getText());
            inputagestring = String.valueOf(age.getText());
            inputage = Integer.valueOf(inputagestring);
            inputcity = String.valueOf(city.getText());
            inputcar = String.valueOf(car.getText());
            inputbio = String.valueOf(bio.getText());
            inputusername = String.valueOf(username.getText());
            inputpassword = String.valueOf(password.getText());

            LoginRequest register = new LoginRequest(context, inputusername, inputpassword, inputname, inputcity, inputage, inputcar, inputbio);
            register.sendUser(inputusername, inputpassword, inputname, inputcity, inputage, inputcar, inputbio);



            Intent intent = new Intent(RegisterActvity.this, MapsActivity.class);
            startActivity(intent);
        }
    }
}
