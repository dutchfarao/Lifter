package com.example.lifter;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class LiftspotDownloader implements Response.ErrorListener, Response.Listener<JSONArray> {

    Context context;
    JSONArray JSONLiftspots;
    Liftspot liftspot;
    ArrayList<User> driversArray;
    ArrayList<User> liftersArray;
    Callback activity;
    int id;
    private static final String TAG = "LiftspotDownloader";


    // constructer
    public LiftspotDownloader(Context context) {
        this.context = context;
    }

    // Implementing the OnErrorResponse
    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = error.getMessage();
        activity.gotLiftspotsError("Something went wrong, could not retrieve liftspots.. " + errorMessage);
    }

    // Implementing the OnResponse
    @Override
    public void onResponse(JSONArray data) {

        // Declares a new ArrayList which holds the scores
        driversArray = new ArrayList<>();
        liftersArray = new ArrayList<>();
        JSONLiftspots = new JSONArray();

        liftspot = new Liftspot();
        // Loop to parse all the score data from the downloaded JSONObject
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject selectedLiftspot= (JSONObject) data.get(0);
                Log.d(TAG, "this is the selected liftspot:" + selectedLiftspot);




                // Data
                String liftspotid = String.valueOf(id);
                Log.d(TAG, "this is the selected liftspot id:" + liftspotid);
                String liftspotname = selectedLiftspot.getString("name");
                Log.d(TAG, "this is the selected liftspot name :" + liftspotname);
                String lon = selectedLiftspot.getString("lon");
                String lat = selectedLiftspot.getString("lat");
                String rating = selectedLiftspot.getString("rating");
                String type = selectedLiftspot.getString("type");
                String driversstring = selectedLiftspot.getString("drivers");
                //create lifterobject
                liftspot.setId(Integer.valueOf(liftspotid));
                liftspot.setName(liftspotname);
                liftspot.setLon(Float.valueOf(lon));
                liftspot.setLat(Float.valueOf(lat));
                liftspot.setRating(rating);
                liftspot.setType(type);
                Log.d(TAG, "this is the liftspot that is sent:" + liftspot);
                JSONArray JSONdrivers = new JSONArray(driversstring);
                Log.d(TAG, "this is the lenght of driversarray " + JSONdrivers.length());

                for (int j=0; j <JSONdrivers.length(); j++) {
                    JSONObject JSONDriver = JSONdrivers.getJSONObject(j);

                    User driver = new User(
                            Integer.valueOf(JSONDriver.getString("userId")),
                            JSONDriver.getString("username"),
                            JSONDriver.getString("password"),
                            JSONDriver.getString("name"),
                            JSONDriver.getString("city"),
                            Integer.valueOf(JSONDriver.getString("age")),
                            JSONDriver.getString("car"),
                            JSONDriver.getString("bio")
                    );


                    driversArray.add(driver);
                    Log.d(TAG, "this is the driversArray :" + driversArray + "liftspotid" + liftspotid);

                }





                String liftersstring = selectedLiftspot.getString("lifters");
                Log.d(TAG, liftersstring);
                JSONArray JSONlifters = new JSONArray(liftersstring);


                for (int k=0; k <JSONlifters.length(); k++) {
                    JSONObject JSONLifter = JSONlifters.getJSONObject(k);
                    Log.d(TAG, "this is the liftersarray :" + "hoi");

                    User lifter = new User(
                            Integer.valueOf(JSONLifter.getString("userId")),
                            JSONLifter.getString("username"),
                            JSONLifter.getString("password"),
                            JSONLifter.getString("name"),
                            JSONLifter.getString("city"),
                            Integer.valueOf(JSONLifter.getString("age")),
                            JSONLifter.getString("car"),
                            JSONLifter.getString("bio")
                    );


                    liftersArray.add(lifter);
                    Log.d(TAG, "this is the liftersarray :" + liftersArray);

                }






                liftspot.setDrivers(driversArray);
                liftspot.setLifters(liftersArray);


            }

        }
        // Runs in case of an exception
        catch (JSONException e) {
            Toast.makeText(context, "JSONException: " + e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        activity.gotLiftspots(liftspot);

    }

    // Implements the Callback method
    public interface Callback {
        void gotLiftspots(Liftspot liftspot);
        void gotLiftspotsError(String message);
    }

    // Implements the actual JSONRequest
    public void getLiftspots(Callback activity, int id1) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot" + id1;
        id = id1;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url , this, this);
        queue.add(jsonArrayRequest);
    }
}