package com.example.lifter;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class UserDownloader implements Response.ErrorListener, Response.Listener<JSONArray> {

    /**

     This class downloads all users from a database, it then creates userobjects with that data.

     */

    Context context;
    JSONArray JSONUsers;
    ArrayList<User> users;
    Callback activity;

    // constructor
    public UserDownloader(Context context) {
        this.context = context;
    }

    // Implementing the OnErrorResponse
    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = error.getMessage();
        activity.gotUserError("Please check your connection: " + errorMessage);
    }

    // Implementing the OnResponse
    @Override
    public void onResponse(JSONArray data) {
        // Declares a new ArrayList which holds users
        users = new ArrayList<>();
        JSONUsers = new JSONArray();

        // Loop to get all the user data
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject selectedUser= (JSONObject) data.get(i);

                // Data
                String userid = selectedUser.getString("id");
                String username = selectedUser.getString("username");
                String password = selectedUser.getString("password");
                String name = selectedUser.getString("name");
                String city = selectedUser.getString("city");
                String age = selectedUser.getString("age");
                String car= selectedUser.getString("car");
                String bio = selectedUser.getString("bio");

                //create userobject
                User user = new User(
                Integer.valueOf(userid),
                username,
                password,
                name,
                city,
                Integer.valueOf(age),
                car,
                bio
                );
                //add user object to ArrayList of users
                users.add(user);
            }
        }
        // Runs in case of an exception
        catch (JSONException e) {
            Toast.makeText(context, "JSONException: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
        activity.gotUser(users);
    }

    // Implements the Callback method
    public interface Callback {
        void gotUser(ArrayList<User> users);
        void gotUserError(String message);
    }

    // Implements the actual JSONRequest
    public void getUsers(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://ide50-dutchfarao.legacy.cs50.io:8080/kak", this, this);
        queue.add(jsonArrayRequest);
    }
}