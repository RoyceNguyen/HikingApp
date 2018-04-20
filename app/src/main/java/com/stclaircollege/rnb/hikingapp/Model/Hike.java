package com.stclaircollege.rnb.hikingapp.Model;

/**
 * Created by Royce on 1/30/2018.
 */

public class Hike {
    public int id;
    public String hikeName;
    public int noOfDayHikes;
    public int noOfBagNights;
    public float distance;
    public int unit; // 0 is km, 1 is mile
    public String contactInfo;
    public String dailyBreakdown;

    public Hike() {
        this.hikeName = "";
        this.noOfBagNights = 0;
        this.noOfDayHikes = 0;
        this.distance = 0;
        this.unit = 0;
        this.contactInfo = "";
        this.dailyBreakdown = "";
    }

}
