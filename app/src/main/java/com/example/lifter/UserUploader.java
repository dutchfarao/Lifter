package com.example.lifter;


import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class UserUploader {
    Context context;
    String username;
    String password;
    String name;
    String city;
    int age;
    String car;
    String bio;
    RequestQueue queue;


    // Constructor
    UserUploader(Context context1, String username1, String password1) {
        this.context = context1;
        username = username1;
        password = password1;
    }


//    @Override
//    public void onResponse(JSONArray response) {
//        try {
//            //create arrayList with scores
//            ArrayList<User> users = new ArrayList<>();
//            // For each question
//            for (int items = 0; items < response.length(); items = items + 1){
//                JSONObject selected = (JSONObject) response.get(items);
//                // getting all the data from scoreboard using keyword "Points"
//                User user = new User();
//                user.setUserId(selected.getInt("userId"));
//                user.setUsername(selected.getString("username"));
//                user.setName(selected.getString("name"));
//                user.setCity(selected.getString("city"));
//                user.setAge(selected.getInt("age"));
//                user.setCar(selected.getString("car"));
//                user.setBio(selected.getString("bio"));
//                user.setPassword(selected.getString("password"));
//                users.add(user);
//            }
//            activity.postedUser(users);
//        }
//        catch (JSONException exception){
//            exception.printStackTrace();
//        }
//    }

    // Callback
    public interface Callback { ;
        void postedUserError(VolleyError error);
        void postedUser(String response);
}


    // Sends your points to the server
    public void sendUser(final Callback activity) {
        // Code based on https://www.kompulsa.com/how-to-send-a-post-request-in-android/
        // POST the values

        String url = "https://ide50-dutchfarao.legacy.cs50.io:8080/list";

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
                return MyData;
            }
        };
        queue = Volley.newRequestQueue(context);
        queue.add(MyStringRequest);

    }

}
