package com.stclaircollege.rnb.hikingapp;

/**
 * Created by Royce on 3/6/2018.
 */

public class Trip {
    private int id;
    private int location;
    private String startDate;
    private String endDate;
    private int organizer;
    private String noOfDays;
    private String reminder;
    private String highlights;
    private String wildlife;
    private int daysHike;
    private int bagNights;
    private int contactInfo;

    public Trip(){

    }
    public Trip(int id,int location,String startDate,String endDate,int organizer,String noOfDays,String reminder,
                String highlights,String wildlife,int daysHike,int bagNights,int contactInfo){
        this.id = id;
        this.location = location;
        this.startDate = startDate;
        this.endDate= endDate;
        this.organizer = organizer;
        this.noOfDays = noOfDays;
        this.reminder = reminder;
        this.highlights = highlights;
        this.wildlife = wildlife;
        this.daysHike = daysHike;
        this.bagNights = bagNights;
        this.contactInfo = contactInfo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getOrganizer() {
        return organizer;
    }

    public void setOrganizer(int organizer) {
        this.organizer = organizer;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getWildlife() {
        return wildlife;
    }

    public void setWildlife(String wildlife) {
        this.wildlife = wildlife;
    }

    public int getDaysHike() {
        return daysHike;
    }

    public void setDaysHike(int daysHike) {
        this.daysHike = daysHike;
    }

    public int getBagNights() {
        return bagNights;
    }

    public void setBagNights(int bagNights) {
        this.bagNights = bagNights;
    }

    public int getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }
}
