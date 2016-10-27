package com.enm.hch;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class PeopleDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "People"; // the name of our database
    private static final int DB_VERSION = 1; //the version of the database

    PeopleDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1) {
            //CREATE DATABASE STRUCTURE
            db.execSQL("CREATE TABLE PEOPLE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "SITE_NAME TEXT,"
                    + "NAME_LAST TEXT,"
                    + "NAME_FIRST TEXT,"
                    + "NAME_MIDDLE TEXT,"
                    + "DATE_BIRTH INTEGER,"
                    + "DATE_DEATH INTEGER,"
                    + "DESCRIPTION TEXT,"
                    + "IMAGE_ID INTEGER);");

            //INPUT PEOPLE
        }

        else {

        }
    }
}
