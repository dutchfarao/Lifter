package com.example.lifter;
import java.io.Serializable;
import java.util.ArrayList;

public class Liftspot implements Serializable {

    /**

    Representation of a Liftspot object in Lifter

    */

    private String name;
    private int id;
    private String rating;
    private String type;
    private float lat;
    private float lon;
    public ArrayList<User> drivers;
    public ArrayList<User> lifters;

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

    public ArrayList<User> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<User> drivers) {
        this.drivers = drivers;
    }

    public ArrayList<User> getLifters() {
        return lifters;
    }

    public void setLifters(ArrayList<User> lifters) {
        this.lifters = lifters;
    }
}
