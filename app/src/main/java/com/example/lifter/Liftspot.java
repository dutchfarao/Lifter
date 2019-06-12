package com.example.lifter;

import android.graphics.Bitmap;

import java.io.Serializable;


import java.io.Serializable;
import java.util.ArrayList;

public class Liftspot implements Serializable {

    private String name;
    private int id;
    private Bitmap image;
    private String rating;
    private String type;
    private float lat;
    private float lon;
    private ArrayList drivers;
    private ArrayList lifters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public ArrayList getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList drivers) {
        this.drivers = drivers;
    }

    public ArrayList getLifters() {
        return lifters;
    }

    public void setLifters(ArrayList lifters) {
        this.lifters = lifters;
    }
}
