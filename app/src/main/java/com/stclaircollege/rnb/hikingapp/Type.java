package com.stclaircollege.rnb.hikingapp;

/**
 * Created by Royce on 3/8/2018.
 */

public class Type {
    private int id;
    private String name;

    public Type(){

    }

    public Type(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}