package com.example.lifter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ProfileAdapter extends ArrayAdapter {

    /**

     This class acts ass an adapter. It puts clickable user profiles in the gridlayout in LiftActivity

     */

    public ArrayList<User> users;

    //constructor
    public ProfileAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> users1) {
        super(context, resource, users1);
        users = users1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }
        TextView username = convertView.findViewById(R.id.username);

        //get correct user and set username
        User chooser = users.get(position);
        username.setText(chooser.getUsername());

        return convertView;
    }
}
