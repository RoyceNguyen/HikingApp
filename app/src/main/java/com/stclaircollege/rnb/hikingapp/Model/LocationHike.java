package com.stclaircollege.rnb.hikingapp.Model;

/**
 * Created by Royce on 3/8/2018.
 */

public class LocationHike {
    private int locationId;
    private int hikeId;

    public  LocationHike(){

    }
    public  LocationHike(int locationId,int hikeId){
        this.locationId = locationId;
        this.hikeId = hikeId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getHikeId() {
        return hikeId;
    }

    public void setHikeId(int hikeId) {
        this.hikeId = hikeId;
    }
}
