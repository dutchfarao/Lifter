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

public class LiftspotDownloader implements Response.ErrorListener, Response.Listener<JSONArray> {

    /**

     This class downloads all liftspots from a database, it then creates liftspotobjects with that data.

     */

    Context context;
    JSONArray JSONLiftspots;
    Liftspot liftspot;
    ArrayList<User> driversArray;
    ArrayList<User> liftersArray;
    Callback activity;
    int id;

    // constructor
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

        driversArray = new ArrayList<>();
        liftersArray = new ArrayList<>();
        JSONLiftspots = new JSONArray();
        liftspot = new Liftspot();
        // Loop through liftspots
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject selectedLiftspot= (JSONObject) data.get(0);

                // Data
                String liftspotid = String.valueOf(id);
                String liftspotname = selectedLiftspot.getString("name");
                String lon = selectedLiftspot.getString("lon");
                String lat = selectedLiftspot.getString("lat");
                String rating = selectedLiftspot.getString("rating");
                String type = selectedLiftspot.getString("type");

                //create liftspot
                liftspot.setId(Integer.valueOf(liftspotid));
                liftspot.setName(liftspotname);
                liftspot.setLon(Float.valueOf(lon));
                liftspot.setLat(Float.valueOf(lat));
                liftspot.setRating(rating);
                liftspot.setType(type);

                //convert JSON array to ArrayList of Users
                String driversstring = selectedLiftspot.getString("drivers");
                JSONArray JSONdrivers = new JSONArray(driversstring);
                for (int j=0; j <JSONdrivers.length(); j++) {
                    JSONObject JSONDriver = JSONdrivers.getJSONObject(j);

                    //create user object
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
                    //add user object to ArrayList
                    driversArray.add(driver);
                }

                //convert JSON array to ArrayList of Users
                String liftersstring = selectedLiftspot.getString("lifters");
                JSONArray JSONlifters = new JSONArray(liftersstring);
                for (int k=0; k <JSONlifters.length(); k++) {
                    JSONObject JSONLifter = JSONlifters.getJSONObject(k);

                    //create user object
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
                    //add user object to ArrayList
                    liftersArray.add(lifter);
                }
                // set created arraylists to liftobject
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
        //retrieve the correct liftspot using it's id
        String url = "https://ide50-dutchfarao.legacy.cs50.io:8080/liftspot" + id1;
        id = id1;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url , this, this);
        queue.add(jsonArrayRequest);
    }
}