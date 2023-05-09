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
    public static final String COLUMN_NOTE = "COLUMN_NOTE";
    public static final String NOTES_TABLE = "NOTES_TABLE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "EventBox.db", null, 1);
    }

    // this will be called the first time you try to access the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT)";
        String createTableEvents = "CREATE TABLE " + EVENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EVENT_NAME + " TEXT, " + COLUMN_EVENT_DATE + " TEXT, " + COLUMN_EVENT_DESCRIPTION + " TEXT, " + COLUMN_EVENT_PLACE + " TEXT)" ;
        String createTableNotes = "CREATE TABLE " + NOTES_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE + " TEXT)" ;


        db.execSQL(createTableStatement);
        db.execSQL(createTableEvents);
        db.execSQL(createTableNotes);
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

        public boolean addOneNote(NotesModel notesModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOTE, notesModel.getNote());

        long insert = db.insert(NOTES_TABLE, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public void addInitialEvents(){
        EventModel event1 = new EventModel(0,"Bad Bunny Concert",
                "17/06/2023",
                "Description of the concert",
                "WiZink Center");
        this.addOneEvent(event1);
        EventModel event2 = new EventModel(1,"Coldplay Concert",
                "24/05/2023",
                "Music of the spheres world tour",
                "Estadi Olímpic Lluís Companys");
        this.addOneEvent(event2);
        EventModel event3 = new EventModel(2,"Fórmula 1®: La Exposición",
                "27/06/2023",
                "Immersive and interactive experience",
                "IFEMA Madrid");
        this.addOneEvent(event3);
        EventModel event4 = new EventModel(3,"Mentes expertas",
                "12/06/2023",
                "Conference of Marian Rojas",
                "Cines Capitol");
        this.addOneEvent(event4);
        EventModel event5 = new EventModel(4,"Mad Cool",
                "06/07/2023",
                "Techno, Pop-Rock",
                "Espacio Mad Cool");
        this.addOneEvent(event5);
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

    public int getNextIdEvents(){
        SQLiteDatabase db = this.getWritableDatabase();

        // obtener el ID del último evento insertado
        String query = "SELECT MAX(" + COLUMN_ID + ") FROM " + EVENT_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int maxId = 0;
        if (cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
        }
        cursor.close();
        return (maxId+1);
    }

    public int getNextIdNotes(){
        SQLiteDatabase db = this.getWritableDatabase();

        // obtener el ID del último evento insertado
        String query = "SELECT MAX(" + COLUMN_ID + ") FROM " + NOTES_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int maxId = 0;
        if (cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
        }
        cursor.close();
        return (maxId+1);
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

    public List<NotesModel> getAllNotes(){

        List<NotesModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + NOTES_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the results
            do {
                int noteID = cursor.getInt(0);
                String noteNote = cursor.getString(1);

                NotesModel newNote = new NotesModel(noteID, noteNote);
                returnList.add(newNote);

            }while (cursor.moveToNext());

        } else {
            // failure. do not do anything
        }
        // close the cursor and the db when done
        cursor.close();
        db.close();
        return returnList;
    }

    public void updateUserPassword(UserModel userModel, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_PASSWORD, newPassword);
        db.update(USER_TABLE, values, COLUMN_USER_NAME + " = ?", new String[] { userModel.getName() });
        db.close();
    }

    public boolean deleteOneNote (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NOTES_TABLE + " WHERE " + COLUMN_ID + " = " + id;
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }


}
