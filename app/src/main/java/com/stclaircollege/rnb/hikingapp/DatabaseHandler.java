package com.stclaircollege.rnb.hikingapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Royce on 1/30/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;

    /**
     * create the name of the database
     */
    private static final String DATABASE_NAME = "hikingapp";

    /**
     * Create the names of all the tables
     */
    private static final String TABLE_TRIP = "trip";
    private static final String TABLE_HIKE = "hike";
    private static final String TABLE_PARTICIPANT = "participant";

    /**
     * Common column names
     */
    private static final String COLUMN_ID = "id";
    /**
     * Trip Table Column Names
     */
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_STARTDATE = "startdate";
    private static final String COLUMN_ENDDATE = "enddate";
    private static final String COLUMN_NOOFDAYS= "attackdamage";
    private static final String COLUMN_ATTACKSPEED = "attackspeed";
    private static final String COLUMN_MAGICDAMAGE = "magicdamage";
    private static final String COLUMN_CRIT = "crit";
    private static final String COLUMN_CRITDAMAGE = "critdamage";
    private static final String COLUMN_HEALTH = "health";
    private static final String COLUMN_ARMOR = "ARMOR";
    private static final String COLUMN_MAGICRESIST = "magicresist";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
