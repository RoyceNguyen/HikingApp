package com.stclaircollege.rnb.hikingapp.Model;

/**
 * Created by Royce on 3/6/2018.
 */

public class Trip {
    public int id;
    public String location;
    public String startDate;
    public String endDate;
    public int tripOrganizer;
    public String participants;
    public String noOfDays;
    public String accommodations;
    public String hikes;
    public String reminders;
    public String wildlifeSeen;
    public String highlights;
    public int status; // 0 future, 1 completed

    public Trip() {
        location = "";
        startDate = "";
        endDate = "";
        tripOrganizer = 0;
        participants = "";
        noOfDays = "";
        accommodations = "";
        hikes = "";
        reminders = "";
        wildlifeSeen = "";
        highlights = "";
        status = 0;
    }
}
