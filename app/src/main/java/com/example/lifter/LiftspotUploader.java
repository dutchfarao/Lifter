package com.example.lifter;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LiftspotUploader {

    /**

     This class uploads all liftspots to a database. It is used one time by myself, I called it from the LoginActivity.
     After this I removed it, all the liftspots are now in the database.

     */

    Context context;
    String name;
    String rating;
    String type;
    String lat;
    String lon;
    ArrayList<User> driversArray;
    ArrayList<User> liftersArray;
    RequestQueue queue;

    //constructor
    public LiftspotUploader(Context context, String name, String rating, String type, String lat, String lon, ArrayList<User> drivers, ArrayList<User> lifters) {
        this.context = context;
        this.name = name;
        this.rating = rating;
        this.type = type;
        this.lat = lat;
        this.lon = lon;
        this.driversArray = drivers;
        this.liftersArray = lifters;
    }

    // Callback
    public interface Callback { ;
        void postedLiftspotError(VolleyError error);
        void postedLiftspot(String response);
    }

    // Sends a liftspot object to the server
    public void sendLiftspot(final LiftspotUploader.Callback activity, int id) {
        // Code based on https://www.kompulsa.com/how-to-send-a-post-request-in-android/

        //Because of the limitations of rester, I decided to upload each liftspotobject with a unique POST url, that way it is impossible to
        //retrieve the wrong liftspotobject, this did happen when I didn't work with unique urls. A url consists of /liftspot+ it's unique id.
        String url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot" + id;

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        activity.postedLiftspot(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        activity.postedLiftspotError(error);
                    }
                }) {

            // Put data in request
            @Override
            protected Map<String, String> getParams() {
                // Put data in request
                Map<String, String> MyData = new HashMap<>();
                MyData.put("name", name);
                MyData.put("rating", rating);
                MyData.put("type", type);
                MyData.put("lat", lat);
                MyData.put("lon", lon);
                //convert arrays to strings
                MyData.put("drivers", driversArray.toString());
                MyData.put("lifters", liftersArray.toString());
                return MyData;
            }
        };
        queue = Volley.newRequestQueue(context);
        queue.add(MyStringRequest);
    }
}
