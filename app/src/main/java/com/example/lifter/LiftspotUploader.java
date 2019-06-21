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
    Context context;
    String name;
    String rating;
    String type;
    String lat;
    String lon;
    String drivers;
    String lifters;
    RequestQueue queue;

    public LiftspotUploader(Context context, String name, String rating, String type, String lat, String lon, String drivers, String lifters) {
        this.context = context;
        this.name = name;
        this.rating = rating;
        this.type = type;
        this.lat = lat;
        this.lon = lon;
        this.drivers = drivers;
        this.lifters = lifters;
    }


    // Callback
    public interface Callback { ;
        void postedLiftspotError(VolleyError error);
        void postedLiftspot(String response);
    }


    // Sends your points to the server
    public void sendLiftspot(final LiftspotUploader.Callback activity) {
        // Code based on https://www.kompulsa.com/how-to-send-a-post-request-in-android/
        // POST the values

        String url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot";

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
                MyData.put("drivers", "");
                MyData.put("lifters", "");
                return MyData;
            }
        };
        queue = Volley.newRequestQueue(context);
        queue.add(MyStringRequest);

    }

}
