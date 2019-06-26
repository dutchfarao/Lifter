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

public class UserUpdater {

    /**

     This class updates a specific User object in a database, it is called in the MyProfileActivity when a User makes changes to their profile.

     */

    Context context;
    String username;
    String password;
    String id;
    String name;
    String city;
    String age;
    String car;
    String bio;
    RequestQueue queue;
    String url;

    // Constructor
    UserUpdater(Context context1, String url1, String id1, String username1, String password1, String name1, String city1, String age1, String car1, String bio1) {
        this.context = context1;
        id = id1;
        username = username1;
        password = password1;
        name = name1;
        city = city1;
        age = age1;
        car = car1;
        bio = bio1;
        url = url1;
    }

    // Callback
    public interface Callback { ;
        void updatedUserError(VolleyError error);
        void updatedUser(String response);
    }


    // Sends your points to the server
    public void updateUser(final Callback activity) {
        //create request
        StringRequest MyStringRequest = new StringRequest(Request.Method.PUT, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        activity.updatedUser(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        activity.updatedUserError(error);
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