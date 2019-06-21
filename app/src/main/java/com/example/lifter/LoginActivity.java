package com.example.lifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements UserDownloader.Callback {

    EditText username;
    EditText password;
    String loginUsername;
    String loginPassword;
    User tempUser;
    String tempUsername;
    String tempUserpassword;
    int checker = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.LoginUsername);
        password = findViewById(R.id.LoginEditTextPassword);



        //create listener for registerlink button
        Button btn2 = findViewById(R.id.LoginRegisterLinkButton);
        onClick2 onButtonClick2 = new onClick2();
        btn2.setOnClickListener(onButtonClick2);

    }

    @Override
    public void gotUser(ArrayList<User> users) {
        loginUsername = String.valueOf(username.getText());
        loginPassword = String.valueOf(password.getText());

        for (int i = 0; i < users.size(); i++){
            tempUser = users.get(i);
            tempUsername = tempUser.getUsername();
            if (loginUsername.equals(tempUsername)){
                tempUserpassword = tempUser.getPassword();
                if (loginPassword.equals(tempUserpassword)){
                    checker = 1;
                    Toast.makeText(this , "Login succesful, welcome back " + tempUsername + "!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                    intent.putExtra("userObject", tempUser);
                    startActivity(intent);
                }
            }
        }
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


}
