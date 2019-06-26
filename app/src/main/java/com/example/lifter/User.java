package com.example.lifter;
import java.io.Serializable;

public class User implements Serializable {

    /**

     Representation of a User object in Lifter

     */

    int userId;
    String username;
    String password;
    String name;
    String city;
    int age;
    String car;
    String bio;

    public User(int userId, String username, String password, String name, String city, int age, String car, String bio) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.city = city;
        this.age = age;
        this.car = car;
        this.bio = bio;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}