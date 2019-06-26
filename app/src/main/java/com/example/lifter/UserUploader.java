package com.example.lifter;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UserUploader {
    /**

     This class uploads all users to a database. It is called in RegisterActivity when someone creates a new account.

     */

    Context context;
    String username;
    String password;
    String name;
    String city;
    String age;
    String car;
    String bio;
    RequestQueue queue;

    // Constructor
    UserUploader(Context context1, String username1, String password1, String name1, String city1, String age1, String car1, String bio1) {
        this.context = context1;
        username = username1;
        password = password1;
        name = name1;
        city = city1;
        age = age1;
        car = car1;
        bio = bio1;
    }

    // Callback
    public interface Callback { ;
        void postedUserError(VolleyError error);
        void postedUser(String response);
}


    // Sends user to the database
    public void sendUser(final Callback activity) {
        // Code based on https://www.kompulsa.com/how-to-send-a-post-request-in-android/

        // set url
        String url = "https://ide50-dutchfarao.legacy.cs50.io:8080/kak";

        //create request
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        activity.postedUser(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        activity.postedUserError(error);
                    }
                }) {

            // Put data in request
            @Override
            protected Map<String, String> getParams() {
                // Put data in request
                Map<String, String> MyData = new HashMap<>();
                MyData.put("username", username);
                MyData.put("password", password);
                MyData.put("name", name);
                MyData.put("city", city);
                MyData.put("age", age);
                MyData.put("car", car);
                MyData.put("bio", bio);
                return MyData;
            }
        };
        queue = Volley.newRequestQueue(context);
        queue.add(MyStringRequest);
    }
}
