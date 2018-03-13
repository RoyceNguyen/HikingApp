package com.stclaircollege.rnb.hikingapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.print.PageRange;

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
    private static final String COLUMN_STARTDATE = "startdate";
    private static final String COLUMN_ENDDATE = "enddate";
    private static final String COLUMN_ORGANIZER = "organizer";
    private static final String COLUMN_NOOFDAYS= "numberofdays";
    private static final String COLUMN_REMINDER = "reminder";
    private static final String COLUMN_HIGHLIGHTS = "highlights";
    private static final String COLUMN_WILDLIFE = "wildlife";
    //private static final String COLUMN_PICTURE = "picture";
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
            //COLUMN_PICTURE + " INTEGER REFERENCES " +
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
            COLUMN_TYPE + " INTEGER REFERENCES  " +
            TABLE_TYPE+ "("+COLUMN_ID+")," +
            COLUMN_PHONENUMBER + " TEXT, " +
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
    public void addTrip(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, trip.getLocation());
        //Log.d("addBuildWeapValue", build.getWeapon() + "");
        values.put(COLUMN_STARTDATE, trip.getStartDate());
        values.put(COLUMN_ENDDATE, trip.getEndDate());
        values.put(COLUMN_ORGANIZER, trip.getOrganizer());
        values.put(COLUMN_NOOFDAYS, trip.getNoOfDays());
        values.put(COLUMN_REMINDER, trip.getReminder());
        values.put(COLUMN_HIGHLIGHTS, trip.getHighlights());
        values.put(COLUMN_WILDLIFE, trip.getWildlife());
        values.put(COLUMN_DAYSHIKE, trip.getDaysHike());
        values.put(COLUMN_BAGNIGHTS, trip.getBagNights());
        values.put(COLUMN_CONTACTINFO, trip.getContactInfo());
        db.insert(TABLE_TRIP, null, values);
        db.close();
    }
    public void addHike(Hike hike) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HIKENAME, hike.getHikeName());
        //Log.d("addBuildWeapValue", build.getWeapon() + "");
        values.put(COLUMN_LENGTH, hike.getLength());
        values.put(COLUMN_DAILYBREAKDOWN, hike.getDailybreakdown());
        values.put(COLUMN_KILOMETRES, hike.getKilometres());
        db.insert(TABLE_HIKE, null, values);
        db.close();
    }
    public void addLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, location.getLocation());
        //Log.d("addBuildWeapValue", build.getWeapon() + "");
        values.put(COLUMN_CITY, location.getCity());
        values.put(COLUMN_COUNTRY, location.getCountry());
        values.put(COLUMN_TYPE, location.getType());
        values.put(COLUMN_PHONENUMBER, location.getPhoneNumber());
        db.insert(TABLE_LOCATION, null, values);
        db.close();
    }
    public void addParticipant(Participant participant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, participant.getName());
        //Log.d("addBuildWeapValue", build.getWeapon() + "");
        values.put(COLUMN_EMAIL, participant.getEmail());
        values.put(COLUMN_PHONENUMBER, participant.getPhoneNumber());
        db.insert(TABLE_PARTICIPANT, null, values);
        db.close();
    }
    public void addLocationHike(LocationHike locationHike) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATIONID, locationHike.getLocationId());
        //Log.d("addBuildWeapValue", build.getWeapon() + "");
        values.put(COLUMN_HIKEID, locationHike.getHikeId());
        db.insert(TABLE_LOCATIONHIKE, null, values);
        db.close();
    }
    public void addType(Type type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, type.getName());
        //Log.d("addBuildWeapValue", build.getWeapon() + "");
        db.insert(TABLE_TYPE, null, values);
        db.close();
    }
    /**
     * READ objects from database
     */
    //creating getTrip and getAllTrips

    public Trip getTrip(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRIP,
                new String[] { COLUMN_ID, COLUMN_LOCATION, COLUMN_STARTDATE, COLUMN_ENDDATE,COLUMN_ORGANIZER,COLUMN_NOOFDAYS,
                        COLUMN_REMINDER,COLUMN_HIGHLIGHTS,COLUMN_WILDLIFE,COLUMN_DAYSHIKE,COLUMN_BAGNIGHTS,COLUMN_CONTACTINFO}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Trip trip = new Trip(Integer.parseInt(cursor.getString(0)),
                cursor.getInt(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getInt(9),cursor.getInt(10),cursor.getInt(11));
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
                trip.setId(Integer.parseInt(cursor.getString(0)));
                trip.setLocation(cursor.getInt(1));
                trip.setStartDate(cursor.getString(2));
                trip.setEndDate(cursor.getString(3));
                trip.setOrganizer(cursor.getInt(4));
                trip.setNoOfDays(cursor.getString(5));
                trip.setReminder(cursor.getString(6));
                trip.setHighlights(cursor.getString(7));
                trip.setWildlife(cursor.getString(8));
                trip.setDaysHike(cursor.getInt(9));
                trip.setBagNights(cursor.getInt(10));
                trip.setContactInfo(cursor.getInt(11));

                tripsList.add(trip);
            } while (cursor.moveToNext());
        }
        return tripsList;
    }
    //creating getHike and getAllHike
    public Hike getHike(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HIKE,
                new String[] { COLUMN_ID, COLUMN_HIKENAME,COLUMN_LENGTH,COLUMN_DAILYBREAKDOWN,COLUMN_KILOMETRES }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Hike hike = new Hike(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getDouble(4));
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
                hike.setId(Integer.parseInt(cursor.getString(0)));
                hike.setHikeName(cursor.getString(1));
                hike.setLength(cursor.getString(2));
                hike.setDailybreakdown(cursor.getString(3));
                hike.setKilometres(cursor.getDouble(4));
                hikesList.add(hike);
            } while (cursor.moveToNext());
        }
        return hikesList;
    }
//creating getLocation and getAllLocation
public Location getLocation(int id) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(TABLE_LOCATION,
            new String[] { COLUMN_ID, COLUMN_LOCATION,COLUMN_CITY,COLUMN_COUNTRY,COLUMN_TYPE,COLUMN_PHONENUMBER }, COLUMN_ID + "=?",
            new String[] { String.valueOf(id) }, null, null, null, null);

    if (cursor != null)
        cursor.moveToFirst();
    Location location = new Location(Integer.parseInt(cursor.getString(0)),
            cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5));
    return location;
}

    public ArrayList<Location> getAllLocation() {
        ArrayList<Location> locationsList = new ArrayList<Location>();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setId(Integer.parseInt(cursor.getString(0)));
                location.setLocation(cursor.getString(1));
                location.setCity(cursor.getString(2));
                location.setCountry(cursor.getString(3));
                location.setType(cursor.getInt(4));
                location.setPhoneNumber(cursor.getString(5));
                locationsList.add(location);
            } while (cursor.moveToNext());
        }
        return locationsList;
    }
    //creating getPartcipant and getAllParticipants
    public Participant getParticipant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PARTICIPANT,
                new String[] { COLUMN_ID, COLUMN_NAME,COLUMN_EMAIL,COLUMN_EMAIL}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Participant participant = new Participant(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
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
                participant.setId(Integer.parseInt(cursor.getString(0)));
                participant.setName(cursor.getString(1));
                participant.setEmail(cursor.getString(2));
                participant.setPhoneNumber(cursor.getString(3));
                participantsList.add(participant);
            } while (cursor.moveToNext());
        }
        return participantsList;
    }
    //creating getLocationHike and getAllLocationHike

    public LocationHike getLocationHike(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LOCATIONHIKE,
                new String[] { COLUMN_LOCATIONID,COLUMN_HIKEID}, COLUMN_LOCATIONID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        LocationHike locationHike = new LocationHike(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)));
        return locationHike;
    }

    public ArrayList<LocationHike> getAllLocationHike() {
        ArrayList<LocationHike> locationHikesList = new ArrayList<LocationHike>();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATIONHIKE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                LocationHike locationHike = new LocationHike();
                locationHike.setLocationId(Integer.parseInt(cursor.getString(0)));
                locationHike.setHikeId(Integer.parseInt(cursor.getString(1)));
                locationHikesList.add(locationHike);
            } while (cursor.moveToNext());
        }
        return locationHikesList;
    }
    //creating getType and getAllTypes

    public Type getType(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TYPE,
                new String[] { COLUMN_ID, COLUMN_NAME}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Type type = new Type(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        return type;
    }

    public ArrayList<Type> getAllType() {
        ArrayList<Type> typesList = new ArrayList<Type>();
        String selectQuery = "SELECT * FROM " + TABLE_TYPE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Type type = new Type();
                type.setId(Integer.parseInt(cursor.getString(0)));
                type.setName(cursor.getString(1));
                typesList.add(type);
            } while (cursor.moveToNext());
        }
        return typesList;
    }
    /**
     * UPDATE objects in database
     */
    public int updateTrip(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, trip.getLocation());
        values.put(COLUMN_STARTDATE, trip.getStartDate());
        values.put(COLUMN_ENDDATE, trip.getEndDate());
        values.put(COLUMN_ORGANIZER, trip.getOrganizer());
        values.put(COLUMN_NOOFDAYS, trip.getNoOfDays());
        values.put(COLUMN_REMINDER, trip.getReminder());
        values.put(COLUMN_HIGHLIGHTS, trip.getHighlights());
        values.put(COLUMN_WILDLIFE, trip.getWildlife());
        values.put(COLUMN_DAYSHIKE, trip.getDaysHike());
        values.put(COLUMN_BAGNIGHTS, trip.getBagNights());
        values.put(COLUMN_CONTACTINFO, trip.getContactInfo());
        return db.update(TABLE_TRIP, values, COLUMN_ID + " = ?", new String[] { String.valueOf(trip.getId()) });
    }

    public int updateHike(Hike hike) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HIKENAME, hike.getHikeName());
        values.put(COLUMN_LENGTH, hike.getLength());
        values.put(COLUMN_DAILYBREAKDOWN, hike.getDailybreakdown());
        values.put(COLUMN_KILOMETRES, hike.getKilometres());
        return db.update(TABLE_HIKE, values, COLUMN_ID + " = ?", new String[] { String.valueOf(hike.getId()) });
    }
    public int updateParticipant(Participant participant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, participant.getName());
        values.put(COLUMN_EMAIL, participant.getEmail());
        values.put(COLUMN_PHONENUMBER, participant.getPhoneNumber());
        return db.update(TABLE_PARTICIPANT, values, COLUMN_ID + " = ?", new String[] { String.valueOf(participant.getId()) });
    }
    public int updateLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, location.getLocation());
        values.put(COLUMN_CITY, location.getCity());
        values.put(COLUMN_COUNTRY, location.getCountry());
        values.put(COLUMN_TYPE, location.getType());
        values.put(COLUMN_PHONENUMBER, location.getPhoneNumber());
        return db.update(TABLE_LOCATION, values, COLUMN_ID + " = ?", new String[] { String.valueOf(location.getId()) });
    }
    public int updateLocationHike(LocationHike locationHike) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATIONID, locationHike.getLocationId());
        values.put(COLUMN_HIKEID, locationHike.getHikeId());
        return db.update(TABLE_LOCATIONHIKE, values, COLUMN_ID + " = ?", new String[] { String.valueOf(locationHike.getHikeId()) });
    }
    public int updateType(Type type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, type.getName());
        return db.update(TABLE_TYPE, values, COLUMN_ID + " = ?", new String[] { String.valueOf(type.getId()) });
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
    public void deleteLocation(long location_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATION, COLUMN_ID + " = ?",
                new String[] { String.valueOf(location_id) });
    }
    public void deleteParticipant(long participant_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PARTICIPANT, COLUMN_ID + " = ?",
                new String[] { String.valueOf(participant_id) });
    }
    public void deleteLocationHike(long locationHike_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATIONHIKE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(locationHike_id) });
    }
    public void deleteType(long type_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TYPE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(type_id) });
    }
    /**
     * Closing the database connections
     */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }



}
