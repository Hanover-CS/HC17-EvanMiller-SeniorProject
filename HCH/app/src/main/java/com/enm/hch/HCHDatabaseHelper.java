package com.enm.hch;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class HCHDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "HCH"; // the name of our database
    private static final int DB_VERSION = 1; //the version of the database

    HCHDatabaseHelper(Context context) {
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

            //CREATE TABLE SITES
            db.execSQL("CREATE TABLE SITES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "SITE_NAME TEXT,"
                    + "DATE_BUILT INTEGER,"
                    + "DATE_DESTROYED INTEGER,"
                    + "DESCRIPTION TEXT,"
                    + "SITE_TYPE TEXT,"
                    + "NAMESAKE TEXT"
                    + "IMAGE_ID INTEGER);");

            //CREATE TABLE PEOPLE
            db.execSQL("CREATE TABLE PEOPLE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAMESAKE TEXT,"
                    + "NAME_LAST TEXT,"
                    + "NAME_FIRST TEXT,"
                    + "NAME_MIDDLE TEXT,"
                    + "DATE_BIRTH INTEGER,"
                    + "DATE_DEATH INTEGER,"
                    + "DESCRIPTION TEXT,"
                    + "IMAGE_ID INTEGER);");

            //CREATE TABLE SITES_TO_PEOPLE
            db.execSQL("CREATE TABLE SITES_TO_PEOPLE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "SITE TEXT,"
                    + "NAME_PERSON TEXT);");

            //CREATE TABLE IMAGES_SITES
            db.execSQL("CREATE TABLE IMAGES_SITES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "SITE_NAME TEXT,"
                    + "IMAGE_ID TEXT,"
                    + "FILE TEXT);");

            //CREATE TABLE IMAGES_PEOPLE
            db.execSQL("CREATE TABLE IMAGES_PEOPLE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "SITE_TYPE TEXT,"
                    + "IMAGE_ID TEXT,"
                    + "FILE TEXT);");

            //
            //INPUTS
            //

            //INSERT SITES
            insertSites(db, "Classic Hall", 1900, 0000,
                    "Fa La La La La", "Academic Building", "None",
                    01234);
            insertSites(db, "Hendricks Hall", 1900, 0000,
                    "Fa La La La La", "Academic Building", "None",
                    01234);

        }

        else {

        }
    }

    /**
     *
     * @param db
     * @param site_name
     * @param date_built
     * @param date_destroyed
     * @param description
     * @param site_type
     * @param namesake
     * @param image_id
     */
    private static void insertSites(SQLiteDatabase db, String site_name,
                                    int date_built, int date_destroyed,
                                    String description, String site_type,
                                    String namesake, int image_id) {
        ContentValues values = new ContentValues();
        values.put("SITE_NAME", site_name);
        values.put("DATE_BUILT", date_built);
        values.put("DATE_DESTROYED", date_destroyed);
        values.put("DESCRIPTION", description);
        values.put("SITE_TYPE", site_type);
        values.put("NAMESAKE", namesake);
        values.put("IMAGE_ID", image_id);
        db.insert("SITES", null, values);
    }

    /**
     *
     * @param db
     * @param namesake
     * @param name_last
     * @param name_first
     * @param name_middle
     * @param date_born
     * @param date_death
     * @param description
     * @param image_id
     */
    private static void insertPeople(SQLiteDatabase db, String namesake,
                                    String name_last, String name_first, String name_middle,
                                    int date_born, int date_death,
                                    String description, int image_id) {
        ContentValues values = new ContentValues();
        values.put("NAMESAKE", namesake);
        values.put("NAME_LAST", name_last);
        values.put("NAME_FIRST", name_first);
        values.put("NAME_MIDDLE", name_middle);
        values.put("DATE_BORN", date_born);
        values.put("DATE_DEATH", date_death);
        values.put("DESCRIPTION", description);
        values.put("IMAGE_ID", image_id);
        db.insert("PEOPLE", null, values);
    }

    /**
     *
     * @param db
     * @param site_name
     * @param name_person
     */
    private static void insertSitesToPeople (SQLiteDatabase db, String site_name,
                                             String name_person) {
        ContentValues values = new ContentValues();
        values.put("SITE_NAME", site_name);
        values.put("NAME_PERSON", name_person);
        db.insert("SITE_TO_PEOPLE", null, values);
    }

    /**
     *
     * @param db
     * @param site_name
     * @param image_id
     * @param file
     */
    private static void insertImageSites (SQLiteDatabase db, String site_name,
                                          String image_id, String file) {
        ContentValues values = new ContentValues();
        values.put("SITE_NAME", site_name);
        values.put("IMAGE_ID", image_id);
        values.put("FILE", file);
        db.insert("IMAGES_SITES", null, values);
    }

    /**
     *
     * @param db
     * @param person_name
     * @param image_id
     * @param file
     */
    private static void insertImagesPeople (SQLiteDatabase db, String person_name,
                                            String image_id, String file) {
        ContentValues values = new ContentValues();
        values.put("SITE_NAME", person_name);
        values.put("IMAGE_ID", image_id);
        values.put("FILE", file);
        db.insert("IMAGES_PEOPLE", null, values);
    }
}

