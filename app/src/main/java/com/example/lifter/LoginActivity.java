package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements UserDownloader.Callback{

    /**

     This activity handles the user login, and also the intent to RegisterActivity if user doesn't have an account yet.

     */

    EditText username;
    EditText password;
    String loginUsername;
    String loginPassword;
    User tempUser;
    String tempUsername;
    String tempUserpassword;
    ArrayList<Liftspot> retrievedLiftspots;
    int checker = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.LoginUsername);
        password = findViewById(R.id.LoginEditTextPassword);
        retrievedLiftspots = new ArrayList<>();


        /**
         Greyed out code below was used for uploading of liftspots.
         */

//        //read the liftspots from csv file using csv helper
//        InputStream inputStream = getResources().openRawResource(R.raw.liftplekken);
//        CSVHelper csvFile = new CSVHelper(inputStream);
//        final ArrayList<Liftspot> liftspots;
//        liftspots = csvFile.read();
//        ArrayList<User> liftersarray = new ArrayList<>();
//        ArrayList<User> driversarray = new ArrayList<>();
//
//        int counter =0;
//        for(Liftspot liftspot : liftspots) {
//           LiftspotUploader upload = new LiftspotUploader(this, liftspot.getName(), liftspot.getRating(), liftspot.getType(), String.valueOf(liftspot.getLat()), String.valueOf(liftspot.getLon()), driversarray, liftersarray);
//           upload.sendLiftspot(this, counter);
//           counter ++;
//      }


        //create listener for registerlink button
        Button btn2 = findViewById(R.id.LoginRegisterLinkButton);
        onClick2 onButtonClick2 = new onClick2();
        btn2.setOnClickListener(onButtonClick2);
    }

    @Override
    public void gotUser(ArrayList<User> users) {

        /**

         This method checks if the user is in the databse, and if password is correct

         */

        // get input from get texts
        loginUsername = String.valueOf(username.getText());
        loginPassword = String.valueOf(password.getText());

        //iterate over ArrayList of all users
        for (int i = 0; i < users.size(); i++){
            tempUser = users.get(i);
            tempUsername = tempUser.getUsername();
            //check if usernames match
            if (loginUsername.equals(tempUsername)){
                tempUserpassword = tempUser.getPassword();
                //check if passwords match
                if (loginPassword.equals(tempUserpassword)){
                    //set checker to 1 so other toast won't be displayed
                    checker = 1;
                    Toast.makeText(this , "Login succesful, welcome back " + tempUsername + "!", Toast.LENGTH_LONG).show();
                    //start intent
                    Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                    intent.putExtra("userObject", tempUser);
                    startActivity(intent);
                    //finish activity
                    finish();
                }
            }
        }
        //if there is no match, display toast
        if(checker == 0) {
            Toast.makeText(this, "Incorrect username/password.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void gotUserError(String message) {
        // Informs the user if an error occurred while logging in
        Toast.makeText(this , "Login error, check your connection!", Toast.LENGTH_LONG).show();
    }

    //this happens when user clicks login button
    public void login(View v) {
        //download users from database and check if user/password combination exists
        UserDownloader login = new UserDownloader(this);
        login.getUsers(this);
    }

    //creation of onclick for button
    public class onClick2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActvity.class);
            startActivity(intent);
        }
    }

    /**
     Greyed out code below was used for uploading of liftspots.
     */
    //    @Override
//    public void postedLiftspotError(VolleyError error) {
//        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    public void postedLiftspot(String response) {
//        Toast.makeText(this, "Liftspots have been uploaded to database", Toast.LENGTH_LONG).show();
//
//    }
}
