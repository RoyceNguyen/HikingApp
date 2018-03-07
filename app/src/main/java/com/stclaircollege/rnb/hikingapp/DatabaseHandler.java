package com.stclaircollege.rnb.hikingapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
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
    private static final String TABLE_LOCATION = "location";
    private static final String TABLE_LOCATIONHIKE = "locationhike";
    private static final String TABLE_TYPE = "type";

    /**
     * Common column names
     */
    private static final String COLUMN_ID = "id";
    /**
     * Trip Table Column Names
     */
//need location column
    private static final String COLUMN_STARTDATE = "startdate";
    private static final String COLUMN_ENDDATE = "enddate";
    private static final String COLUMN_ORGANIZER = "organizer";
    private static final String COLUMN_NOOFDAYS= "numberofdays";
    private static final String COLUMN_REMINDER = "reminder";
    private static final String COLUMN_HIGHLIGHTS = "highlights";
    private static final String COLUMN_WILDLIFE = "wildlife";
    private static final String COLUMN_PICTURE = "picture";
    private static final String COLUMN_DAYSHIKE = "dayshike";
    private static final String COLUMN_BAGNIGHTS = "bagnights";
    private static final String COLUMN_CONTACTINFO = "contactinfo";


    /**
     * Participants Table Column Names
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
    private static final String COLUMN_KILOMETRES = "kilometres";
    //private static final String COLUMN_CONTACTINFO = "contactinfo";

    /**
     * Location Table Column Names
     */

    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_TYPE = "type";
// HAS PHONE NUMBER

    /**
     * LocationHike Table Column Names
     */
    private static final String COLUMN_LOCATIONID = "locationid";
    private static final String COLUMN_HIKEID = "hikeid";


    /**
     * Create statements for all of our tables
     */

    private static final String CREATE_TRIP_TABLE = "CREATE TABLE " +
            TABLE_TRIP + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_LOCATION + " INTEGER REFERENCES " +
            TABLE_LOCATION + "("+COLUMN_ID+")," +
            COLUMN_STARTDATE + " TEXT, " +
            COLUMN_ENDDATE + " TEXT, " +
            COLUMN_ORGANIZER + " INTEGER REFERENCES " +
            TABLE_PARTICIPANT + "("+COLUMN_ID+")," +
            COLUMN_NOOFDAYS + " TEXT, " +
            COLUMN_REMINDER + " TEXT, " +
            COLUMN_HIGHLIGHTS + " TEXT, " +
            COLUMN_WILDLIFE + " TEXT, " +
            COLUMN_PICTURE + " INTEGER REFERENCES " +
            COLUMN_DAYSHIKE + " INTEGER, " +
            COLUMN_BAGNIGHTS + " INTEGER, " +
            COLUMN_CONTACTINFO + " INTEGER REFERENCES  " +
            TABLE_PARTICIPANT + "("+COLUMN_ID+")," +
            ")";

    private static final String CREATE_HIKE_TABLE = "CREATE TABLE " +
            TABLE_HIKE + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_HIKENAME + " TEXT, " +
            COLUMN_LENGTH + " TEXT, " +
            COLUMN_DAILYBREAKDOWN + " TEXT, " +
            COLUMN_KILOMETRES + " DECIMAL, " +
            COLUMN_CONTACTINFO + " INTEGER REFERENCES  " +
            TABLE_LOCATION + "("+COLUMN_ID+")," +
            ")";

    private static final String CREATE_PARTICIPANT_TABLE = "CREATE TABLE " +
            TABLE_PARTICIPANT + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_NAME + " TEXT, " +
            COLUMN_EMAIL + " TEXT, " +
            COLUMN_PHONENUMBER + " TEXT, " +
            ")";

    private static final String CREATE_LOCATION_TABLE = "CREATE TABLE " +
            TABLE_LOCATION + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_LOCATION + " TEXT, " +
            COLUMN_CITY + " TEXT, " +
            COLUMN_COUNTRY + " TEXT, " +
            COLUMN_TYPE + " TEXT, " +
            ")";
    private static final String CREATE_LOCATIONHIKE_TABLE = "CREATE TABLE " +
            TABLE_LOCATIONHIKE + "(" +
            COLUMN_LOCATIONID + " INTEGER REFERENCES  " +
            TABLE_LOCATION + "("+COLUMN_ID+")," +
            COLUMN_HIKEID + " INTEGER REFERENCES  " +
            TABLE_HIKE+ "("+COLUMN_ID+")," +
            ")";
    private static final String CREATE_TYPE_TABLE = "CREATE TABLE " +
            TABLE_TYPE + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_NAME + " TEXT, " +
            ")";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /**
     * Create the tables inside of the database
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HIKE_TABLE);
        db.execSQL(CREATE_TRIP_TABLE);
        db.execSQL(CREATE_PARTICIPANT_TABLE);
        db.execSQL(CREATE_LOCATION_TABLE);
        db.execSQL(CREATE_LOCATIONHIKE_TABLE);
        db.execSQL(CREATE_TYPE_TABLE);
    }
    /**
     * When the database upgrades delete the old tables and recreate them
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIKE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONHIKE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTICIPANT);
        onCreate(db);
    }

    /**
     * CRUD OPERATIONS FOR THE DATABASE AND TABLES
     */

    /**
     * CREATE new objects for the tables
     *
     */


}
