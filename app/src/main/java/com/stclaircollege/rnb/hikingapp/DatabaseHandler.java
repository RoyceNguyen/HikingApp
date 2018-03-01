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
    private static final String COLUMN_NOOFDAYS= "numberofdays";
    private static final String COLUMN_REMINDER = "reminder";
    private static final String COLUMN_HIGHLIGHTS = "highlights";
    private static final String COLUMN_WILDLIFE = "wildlife";
    private static final String COLUMN_PICTURE = "picture";
    private static final String COLUMN_COMPLETEDHIKES = "completedhike";
    private static final String COLUMN_MILES = "comlumnmiles";
    private static final String COLUMN_KILOMETRES = "kilometres";
    private static final String COLUMN_DAYSHIKE = "dayshike";
    private static final String COLUMN_BAGNIGHTS = "bagnights";
    private static final String COLUMN_CONTACTINFO = "contactinfo";

    /**
     * Participant Table Column Names
     */
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONENUMBER = "phonenumber";

    /**
     * Hike Table Column Names
     */
    private static final String COLUMN_HIKENAME= "hikename";
    private static final String COLUMN_LENGTH = "length";
    private static final String COLUMN_DAILYBREAKDOWN = "dailybreakdown";
    //private static final Double COLUMN_MILES = 0.0;
   // private static final Double COLUMN_KILOMETRES = 0.0;
    //private static final String COLUMN_CONTACTINFO = "contactinfo";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
