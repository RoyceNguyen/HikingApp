package com.stclaircollege.rnb.hikingapp;

/**
 * Created by Royce on 3/8/2018.
 */

public class Location {
    private int id;
    private String location;
    private String city;
    private String country;
    private String type;
    private String phoneNumber;



    public Location(){

    }



    public Location(int id, String location, String city, String country, String type, String phoneNumber){
        this.id = id;
        this.location = location;
        this.city = city;
        this.country = country;
        this.type = type;
        this.phoneNumber = phoneNumber

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
