package com.example.lifter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiftspotUpdater {
    Context context;
    String url;
    String name;
    String rating;
    String type;
    String lat;
    String lon;
    String drivers;
    String lifters;
    RequestQueue queue;

    public LiftspotUpdater(Context context, String url, String name, String rating, String type, String lat, String lon, String drivers, String lifters) {
        this.context = context;
        this.url = url;
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
        void updatedLiftspotError(VolleyError error);
        void updatedLiftspot(String response);
    }




    // Sends your points to the server
    public void updateLiftspot(final LiftspotUpdater.Callback activity) {
        // Code based on https://www.kompulsa.com/how-to-send-a-post-request-in-android/
        // POST the values

        StringRequest MyStringRequest = new StringRequest(Request.Method.PUT, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        activity.updatedLiftspot(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        activity.updatedLiftspotError(error);
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
                MyData.put("drivers", drivers);
                MyData.put("lifters", lifters);
                return MyData;
            }
        };
        queue = Volley.newRequestQueue(context);
        queue.add(MyStringRequest);

    }

}
