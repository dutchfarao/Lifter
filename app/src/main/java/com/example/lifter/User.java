package com.example.lifter;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String name;
    private int id;
    private Bitmap image;
    private float rating;
    private String city;
    private int age;
    private String car;
    private String bio;
}