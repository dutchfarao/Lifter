package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class RegisterActvity extends AppCompatActivity implements UserUploader.Callback, UserDownloader.Callback{


    /**

     This activity handles the registration of a user, and calls UserUploader to submit the user to database.

     */

    EditText name;
    EditText username;
    EditText password;
    EditText age;
    EditText city;
    EditText car;
    EditText bio;
    String inputname;
    String inputage;
    String inputcity;
    String inputcar;
    String inputbio;
    String inputusername;
    String inputpassword;
    User tempUser;
    String tempUsername;


    // Shows the user a warning if an error is encountered during the uploading of the score
    @Override
    public void postedUserError(VolleyError error) {
        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();
    }

    // Shows the user a toast if the score is successfully uploaded to the database
    @Override
    public void postedUser(String response) {
        Toast.makeText(this, "Account has been created!", Toast.LENGTH_LONG).show();
        //download users from database and check if user/password combination exists
        UserDownloader login = new UserDownloader(this);
        login.getUsers(this);
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
        //get all values from edittexts
         inputname = String.valueOf(name.getText());
         inputage = String.valueOf(age.getText());
         inputcity = String.valueOf(city.getText());
         inputcar = String.valueOf(car.getText());
         inputbio = String.valueOf(bio.getText());
         inputusername = String.valueOf(username.getText());
         inputpassword = String.valueOf(password.getText());

         //call userloader, thus submitting user to database
        UserUploader register = new UserUploader(this, inputusername, inputpassword, inputname, inputcity, inputage, inputcar, inputbio);
        register.sendUser(this);

    }

    @Override
    public void gotUser(ArrayList<User> users) {

        /**

         This method make sure that the user is downloaded after it has been registered

         */

        //iterate over ArrayList of all users
        for (int i = 0; i < users.size(); i++){
            tempUser = users.get(i);
            tempUsername = tempUser.getUsername();
            //check if usernames match
            if (inputusername.equals(tempUsername)){
                //start intent
                Intent intent = new Intent(RegisterActvity.this, MapsActivity.class);
                intent.putExtra("userObject", tempUser);
                startActivity(intent);
                //finish activity
                finish();
            }
        }
    }

    @Override
    public void gotUserError(String message) {
        // Informs the user if an error occurred while getting account in
        Toast.makeText(this , "Error, check your connection!", Toast.LENGTH_LONG).show();
    }
}
