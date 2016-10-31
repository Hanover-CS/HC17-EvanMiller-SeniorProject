package com.enm.hch;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class SitesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Sites"; // the name of our database
    private static final int DB_VERSION = 1; //the version of the database

    SitesDatabaseHelper(Context context) {
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
            db.execSQL("CREATE TABLE SITES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "SITE_NAME TEXT,"
                    + "DATE_BUILT INTEGER,"
                    + "DATE_DESTROYED INTEGER,"
                    + "DESCRIPTION TEXT,"
                    + "SITE_TYPE TEXT,"
                    + "PERSON_NAMESAKE TEXT"
                    + "LONGITUDE TEXT,"
                    + "LATITUDE TEXT,"
                    + "IMAGE_ID INTEGER);");

            //INPUT SITES
        }

        else {

        }
    }
}
