package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LiftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);

        //create listener for login button
        Button btn = findViewById(R.id.LiftHomeButton);
        onClick onButtonClick = new onClick();
        btn.setOnClickListener(onButtonClick);
    }

    //creation of onclick for button
    private class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LiftActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    }
}

