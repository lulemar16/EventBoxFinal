package com.example.eventbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_ID = "ID";
    public static final String EVENT_TABLE = "EVENT_TABLE";
    public static final String COLUMN_EVENT_NAME = "COLUMN_EVENT_NAME";
    public static final String COLUMN_EVENT_DESCRIPTION = "COLUMN_EVENT_DESCRIPTION";
    public static final String COLUMN_EVENT_PLACE = "COLUMN_EVENT_PLACE";
    public static final String COLUMN_EVENT_DATE = "COLUMN_EVENT_DATE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "EventBox.db", null, 1);
    }

    // this will be called the first time you try to access the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT)";
        String createTableEvents = "CREATE TABLE " + EVENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EVENT_NAME + " TEXT, " + COLUMN_EVENT_DATE + " TEXT, " + COLUMN_EVENT_DESCRIPTION + " TEXT, " + COLUMN_EVENT_PLACE + " TEXT )" ;

        db.execSQL(createTableStatement);
    }

    // called whenever the version number of the database change
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOneUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, userModel.getName());
        cv.put(COLUMN_USER_EMAIL, userModel.getEmail());
        cv.put(COLUMN_USER_PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addOneEvent(EventModel eventModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EVENT_NAME, eventModel.getName());
        cv.put(COLUMN_EVENT_DESCRIPTION, eventModel.getDescription());
        cv.put(COLUMN_EVENT_DATE, eventModel.getDate());
        cv.put(COLUMN_EVENT_PLACE, eventModel.getPlace());

        long insert = db.insert(EVENT_TABLE, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }


    public List<UserModel> getEveryone(){

        List<UserModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the results
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userEmail = cursor.getString(2);
                String userPassword = cursor.getString(3);

                UserModel newUser = new UserModel(userID, userName, userEmail, userPassword);
                returnList.add(newUser);

            }while (cursor.moveToNext());

        } else {
            // failure. do not do anything
        }
        // close the cursor and the db when done
        cursor.close();
        db.close();
        return returnList;
    }

    public List<EventModel> getEvents(){

        List<EventModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + EVENT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the results
            do {
                int eventID = cursor.getInt(0);
                String eventName = cursor.getString(1);
                String eventDate = cursor.getString(2);
                String eventDescription = cursor.getString(3);
                String eventPlace = cursor.getString(4);

                EventModel newEvent = new EventModel(eventID, eventName, eventDate, eventDescription, eventPlace);
                returnList.add(newEvent);

            }while (cursor.moveToNext());

        } else {
            // failure. do not do anything
        }
        // close the cursor and the db when done
        cursor.close();
        db.close();
        return returnList;
    }
}
