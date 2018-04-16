package com.stclaircollege.rnb.hikingapp.Util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.stclaircollege.rnb.hikingapp.Model.LocationHike;
import com.stclaircollege.rnb.hikingapp.Model.Hike;
import com.stclaircollege.rnb.hikingapp.Model.Location;
import com.stclaircollege.rnb.hikingapp.Model.Trip;
import com.stclaircollege.rnb.hikingapp.Model.Type;
import com.stclaircollege.rnb.hikingapp.Model.Participant;

import java.util.ArrayList;

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
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_STARTDATE = "startdate";
    private static final String COLUMN_ENDDATE = "enddate";
    private static final String COLUMN_ORGANIZER = "organizer";
    private static final String COLUMN_PARTICIPANTS = "participants";
    private static final String COLUMN_NOOFDAYS = "numberofdays";
    private static final String COLUMN_ACCOMMODATIONS = "accommodations";
    private static final String COLUMN_HIKES = "hikes";
    private static final String COLUMN_REMINDER = "reminder";
    private static final String COLUMN_HIGHLIGHTS = "highlights";
    private static final String COLUMN_WILDLIFE = "wildlife";
    private static final String COLUMN_STATUS = "status";


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
    private static final String COLUMN_NOOFDAYHIKES = "noofdayhikes";
    private static final String COLUMN_NOOFBAGNIGHTS = "noofbagnights";
    private static final String COLUMN_DISTANCE = "distance";
    private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_DAILYBREAKDOWN = "dailybreakdown";
    private static final String COLUMN_CONTACTINFO = "contactinfo";

    /**
     * Create statements for all of our tables
     */
    private static final String CREATE_TRIP_TABLE = "CREATE TABLE " +
            TABLE_TRIP + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_LOCATION + " TEXT, " +
            COLUMN_STARTDATE + " TEXT, " +
            COLUMN_ENDDATE + " TEXT, " +
            COLUMN_ORGANIZER + " INTEGER, " +
            COLUMN_PARTICIPANTS + " TEXT, " +
            COLUMN_NOOFDAYS + " INTEGER, " +
            COLUMN_ACCOMMODATIONS + " TEXT, " +
            COLUMN_HIKES + " TEXT, " +
            COLUMN_REMINDER + " TEXT, " +
            COLUMN_WILDLIFE + " TEXT, " +
            COLUMN_HIGHLIGHTS + " TEXT, " +
            COLUMN_STATUS  + " INTEGER " + ")";

    private static final String CREATE_HIKE_TABLE = "CREATE TABLE " +
            TABLE_HIKE + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_HIKENAME + " TEXT, " +
            COLUMN_NOOFDAYHIKES + " INTEGER," +
            COLUMN_NOOFBAGNIGHTS + " INTEGER," +
            COLUMN_DISTANCE + " REAL," +
            COLUMN_UNIT + " INTEGER," +
            COLUMN_CONTACTINFO + " TEXT, " +
            COLUMN_DAILYBREAKDOWN + " TEXT" + ")";

    private static final String CREATE_PARTICIPANT_TABLE = "CREATE TABLE " +
            TABLE_PARTICIPANT + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_NAME + " TEXT, " +
            COLUMN_EMAIL + " TEXT, " +
            COLUMN_PHONENUMBER + " TEXT" + ")";

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
    public int addTrip(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, trip.location);
        values.put(COLUMN_STARTDATE, trip.startDate);
        values.put(COLUMN_ENDDATE, trip.endDate);
        values.put(COLUMN_ORGANIZER, trip.tripOrganizer);
        values.put(COLUMN_PARTICIPANTS, trip.participants);
        values.put(COLUMN_NOOFDAYS, trip.noOfDays);
        values.put(COLUMN_ACCOMMODATIONS, trip.accommodations);
        values.put(COLUMN_HIKES, trip.hikes);
        values.put(COLUMN_REMINDER, trip.reminders);
        values.put(COLUMN_WILDLIFE, trip.wildlifeSeen);
        values.put(COLUMN_HIGHLIGHTS, trip.highlights);
        values.put(COLUMN_STATUS, trip.status);
        int id = (int)db.insert(TABLE_TRIP, null, values);
        db.close();
        return id;
    }

    public int addHike(Hike hike) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HIKENAME, hike.hikeName);
        values.put(COLUMN_NOOFDAYHIKES, hike.noOfDayHikes);
        values.put(COLUMN_NOOFBAGNIGHTS, hike.noOfBagNights);
        values.put(COLUMN_DISTANCE, hike.distance);
        values.put(COLUMN_UNIT, hike.unit);
        values.put(COLUMN_CONTACTINFO, hike.contactInfo);
        values.put(COLUMN_DAILYBREAKDOWN, hike.dailyBreakdown);
        int id = (int)db.insert(TABLE_HIKE, null, values);
        db.close();
        return id;
    }

    public int addParticipant(Participant participant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, participant.name);
        values.put(COLUMN_EMAIL, participant.email);
        values.put(COLUMN_PHONENUMBER, participant.phoneNumber);
        int id = (int) db.insert(TABLE_PARTICIPANT, null, values);
        db.close();
        return id;
    }

    /**
     * READ objects from database
     */
    //creating getTrip and getAllTrips

    public Trip getTrip(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRIP,
                new String[] { COLUMN_ID, COLUMN_LOCATION, COLUMN_STARTDATE, COLUMN_ENDDATE,COLUMN_ORGANIZER, COLUMN_PARTICIPANTS, COLUMN_NOOFDAYS,COLUMN_ACCOMMODATIONS,
                        COLUMN_HIKES,COLUMN_REMINDER,COLUMN_WILDLIFE,COLUMN_HIGHLIGHTS,COLUMN_STATUS}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();
        Trip trip = new Trip();
        trip.id = cursor.getInt(0);
        trip.location = cursor.getString(1);
        trip.startDate = cursor.getString(2);
        trip.endDate = cursor.getString(3);
        trip.tripOrganizer = cursor.getInt(4);
        trip.participants = cursor.getString(5);
        trip.noOfDays = cursor.getInt(6);
        trip.accommodations = cursor.getString(7);
        trip.hikes = cursor.getString(8);
        trip.reminders = cursor.getString(9);
        trip.wildlifeSeen = cursor.getString(10);
        trip.highlights = cursor.getString(11);
        trip.status = cursor.getInt(12);
        return trip;
    }

    public ArrayList<Trip> getAllTrips() {
        ArrayList<Trip> tripsList = new ArrayList<Trip>();
        String selectQuery = "SELECT * FROM " + TABLE_TRIP;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Trip trip = new Trip();
                trip.id = cursor.getInt(0);
                trip.location = cursor.getString(1);
                trip.startDate = cursor.getString(2);
                trip.endDate = cursor.getString(3);
                trip.tripOrganizer = cursor.getInt(4);
                trip.participants = cursor.getString(5);
                trip.noOfDays = cursor.getInt(6);
                trip.accommodations = cursor.getString(7);
                trip.hikes = cursor.getString(8);
                trip.reminders = cursor.getString(9);
                trip.wildlifeSeen = cursor.getString(10);
                trip.highlights = cursor.getString(11);
                trip.status = cursor.getInt(12);
                tripsList.add(trip);
            } while (cursor.moveToNext());
        }
        return tripsList;
    }

    public Hike getHike(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HIKE,
                new String[] { COLUMN_ID, COLUMN_HIKENAME,COLUMN_NOOFDAYHIKES, COLUMN_NOOFBAGNIGHTS, COLUMN_DISTANCE, COLUMN_UNIT, COLUMN_CONTACTINFO,COLUMN_DAILYBREAKDOWN },
                COLUMN_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();
        Hike hike = new Hike();
        hike.id = cursor.getInt(0);
        hike.hikeName = cursor.getString(1);
        hike.noOfDayHikes = cursor.getInt(2);
        hike.noOfBagNights = cursor.getInt(3);
        hike.distance = cursor.getFloat(4);
        hike.unit = cursor.getInt(5);
        hike.contactInfo = cursor.getString(6);
        hike.dailyBreakdown = cursor.getString(7);
        return hike;
    }

    public ArrayList<Hike> getAllHike() {
        ArrayList<Hike> hikesList = new ArrayList<Hike>();
        String selectQuery = "SELECT * FROM " + TABLE_HIKE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Hike hike = new Hike();
                hike.id = cursor.getInt(0);
                hike.hikeName = cursor.getString(1);
                hike.noOfDayHikes = cursor.getInt(2);
                hike.noOfBagNights = cursor.getInt(3);
                hike.distance = cursor.getFloat(4);
                hike.unit = cursor.getInt(5);
                hike.contactInfo = cursor.getString(6);
                hike.dailyBreakdown = cursor.getString(7);
                hikesList.add(hike);
            } while (cursor.moveToNext());
        }
        return hikesList;
    }

    //creating getPartcipant and getAllParticipants
    public Participant getParticipant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PARTICIPANT,
                new String[] { COLUMN_ID, COLUMN_NAME }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Participant participant = new Participant();
        participant.id = cursor.getInt(0);
        participant.name = cursor.getString(1);
        return participant;
    }

    public ArrayList<Participant> getAllParticipant() {
        ArrayList<Participant> participantsList = new ArrayList<Participant>();
        String selectQuery = "SELECT * FROM " + TABLE_PARTICIPANT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Participant participant = new Participant();
                participant.id = cursor.getInt(0);
                participant.name = cursor.getString(1);
                participantsList.add(participant);
            } while (cursor.moveToNext());
        }
        return participantsList;
    }

    /**
     * UPDATE objects in database
     */
    public int updateTrip(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, trip.location);
        values.put(COLUMN_STARTDATE, trip.startDate);
        values.put(COLUMN_ENDDATE, trip.endDate);
        values.put(COLUMN_ORGANIZER, trip.tripOrganizer);
        values.put(COLUMN_PARTICIPANTS, trip.participants);
        values.put(COLUMN_NOOFDAYS, trip.noOfDays);
        values.put(COLUMN_ACCOMMODATIONS, trip.accommodations);
        values.put(COLUMN_HIKES, trip.hikes);
        values.put(COLUMN_REMINDER, trip.reminders);
        values.put(COLUMN_WILDLIFE, trip.wildlifeSeen);
        values.put(COLUMN_HIGHLIGHTS, trip.highlights);
        values.put(COLUMN_STATUS, trip.status);
        return db.update(TABLE_TRIP, values, COLUMN_ID + " = ?", new String[] { String.valueOf(trip.id) });
    }

    public int updateHike(Hike hike) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HIKENAME, hike.hikeName);
        values.put(COLUMN_NOOFDAYHIKES, hike.noOfDayHikes);
        values.put(COLUMN_NOOFBAGNIGHTS, hike.noOfBagNights);
        values.put(COLUMN_DISTANCE, hike.distance);
        values.put(COLUMN_UNIT, hike.unit);
        values.put(COLUMN_CONTACTINFO, hike.contactInfo);
        values.put(COLUMN_DAILYBREAKDOWN, hike.dailyBreakdown);
        return db.update(TABLE_HIKE, values, COLUMN_ID + " = ?", new String[] { String.valueOf(hike.id) });
    }

    public int updateParticipant(Participant participant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, participant.name);
        values.put(COLUMN_EMAIL, participant.email);
        values.put(COLUMN_PHONENUMBER, participant.phoneNumber);
        return db.update(TABLE_PARTICIPANT, values, COLUMN_ID + " = ?", new String[] { String.valueOf(participant.id) });
    }

    /**
     * DELETE objects from database
     */
    public void deleteTrip(long trip_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIP, COLUMN_ID + " = ?",
                new String[] { String.valueOf(trip_id) });
    }
    public void deleteHike(long hike_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HIKE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(hike_id) });
    }
    public void deleteParticipant(long participant_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PARTICIPANT, COLUMN_ID + " = ?",
                new String[] { String.valueOf(participant_id) });
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
