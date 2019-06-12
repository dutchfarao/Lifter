package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //create listener for login button
        Button btn = findViewById(R.id.LoginButton);
        onClick onButtonClick = new onClick();
        btn.setOnClickListener(onButtonClick);

        //create listener for registerlink button
        Button btn2 = findViewById(R.id.LoginRegisterLinkButton);
        onClick2 onButtonClick2 = new onClick2();
        btn2.setOnClickListener(onButtonClick2);

    }


    //creation of onclick for button
    private class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //read the liftspots from csv file using csv helper
            InputStream inputStream = getResources().openRawResource(R.raw.liftplekken);
            CSVHelper csvFile = new CSVHelper(inputStream);
            List<Liftspot> liftspots = csvFile.read();
            for(Liftspot liftspot : liftspots) {
                System.out.println(liftspot.getName() + liftspot.getLat() + liftspot.getLon());
            }


            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    }

    //creation of onclick for button
    private class onClick2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActvity.class);
            startActivity(intent);
        }
    }
}
