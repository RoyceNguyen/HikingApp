package com.stclaircollege.rnb.hikingapp;

/**
 * Created by Royce on 1/30/2018.
 */

public class Hike {
    private int id;
    private String hikeName;
    private String length;
    private String dailybreakdown;
    private double kilometres;
    private String contactInfo;

    public Hike(){

    }

    public  Hike(int id,String hikeName,String length,String dailybreakdown,double kilometres,String contactInfo){
        this.id = id;
        this.hikeName = hikeName;
        this.length = length;
        this.dailybreakdown = dailybreakdown;
        this.kilometres = kilometres;
        this.contactInfo = contactInfo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHikeName() {
        return hikeName;
    }

    public void setHikeName(String hikeName) {
        this.hikeName = hikeName;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDailybreakdown() {
        return dailybreakdown;
    }

    public void setDailybreakdown(String dailybreakdown) {
        this.dailybreakdown = dailybreakdown;
    }

    public double getKilometres() {
        return kilometres;
    }

    public void setKilometres(double kilometres) {
        this.kilometres = kilometres;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
