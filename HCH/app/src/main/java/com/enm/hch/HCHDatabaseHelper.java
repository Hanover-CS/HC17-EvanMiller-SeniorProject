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
            //
            //CREATE TABLE SITES
            //
            db.execSQL("CREATE TABLE SITES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "SITE_NAME TEXT, "
                    + "DATE_BUILT INTEGER, "
                    + "DATE_DESTROYED INTEGER, "
                    + "DESCRIPTION TEXT, "
                    + "SITE_TYPE TEXT, "
                    + "NAMESAKE TEXT, "
                    + "IMAGE_ID INTEGER);");

            //INSERT SITES
            insertSites(db, "Classic Hall", 1900, 1901,
                    "Fa La La La La", "Academic Building", "None",
                    1234);
            insertSites(db, "Hendricks Hall", 1900, 1901,
                    "Fa La La La La", "Academic Building", "VP Hendricks",
                    1234);
            insertSites(db, "Parker Auditorium", 1900, 1901,
                    "Fa La La La La", "Academic Building", "President Parker",
                    1234);
            insertSites(db, "Science Center", 1900, 1901,
                    "Fa La La La La", "Academic Building", "Goodrich",
                    1234);
            insertSites(db, "Science Hall", 1900, 1901,
                    "Fa La La La La", "Academic Building", "None",
                    1234);
            insertSites(db, "Newby", 1900, 1901,
                    "Fa La La La La", "Academic Building", "Newby",
                    1234);
            insertSites(db, "Faculty Office Building", 1900, 1901,
                    "Fa La La La La", "Academic Building", "None",
                    1234);
            insertSites(db, "Lynn Hall", 1900, 1901,
                    "Fa La La La La", "Academic Building", "Lynn",
                    1234);
            insertSites(db, "Duggan Library", 1900, 1901,
                    "Fa La La La La", "Academic Building", "Duggan",
                    1234);
            insertSites(db, "Lynn Center for the Fine Arts (CFA)", 1900, 1901,
                    "Fa La La La La", "Academic Building", "Lynn",
                    1234);

            //
            //CREATE TABLE PEOPLE
            //
            db.execSQL("CREATE TABLE PEOPLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAMESAKE TEXT, "
                    + "NAME_LAST TEXT, "
                    + "NAME_FIRST TEXT, "
                    + "NAME_MIDDLE TEXT, "
                    + "DATE_BIRTH INTEGER, "
                    + "DATE_DEATH INTEGER, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_ID INTEGER);");

            //INSERT PEOPLE
            insertPeople(db, "Vice President Thomas A. Hendricks",
                    "Hendricks", "Thomas", "A.", 1900, 2000,
                    "Description", 1234);

            //
            //CREATE TABLE SITES_TO_PEOPLE
            //
            db.execSQL("CREATE TABLE SITES_TO_PEOPLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "SITE TEXT, "
                    + "NAME_PERSON TEXT);");

            //INSERT SITES_TO_PEOPLE


            //
            //CREATE TABLE IMAGES_SITES
            //
            db.execSQL("CREATE TABLE IMAGES_SITES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "SITE_NAME TEXT, "
                    + "IMAGE_ID TEXT, "
                    + "FILE TEXT);");

            //INSERT IMAGES_SITES


            //CREATE TABLE IMAGES_PEOPLE
            db.execSQL("CREATE TABLE IMAGES_PEOPLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "SITE_TYPE TEXT, "
                    + "IMAGE_ID TEXT, "
                    + "FILE TEXT);");

            //INSERT IMAGES_PEOPLE

    }


    private static void insertSites(SQLiteDatabase db, String site_name,
                                    int date_built, int date_destroyed,
                                    String description, String site_type,
                                    String namesake, int image_id) {
        ContentValues siteValues = new ContentValues();
        siteValues.put("SITE_NAME", site_name);
        siteValues.put("DATE_BUILT", date_built);
        siteValues.put("DATE_DESTROYED", date_destroyed);
        siteValues.put("DESCRIPTION", description);
        siteValues.put("SITE_TYPE", site_type);
        siteValues.put("NAMESAKE", namesake);
        siteValues.put("IMAGE_ID", image_id);
        db.insert("SITES", null, siteValues);
    }

    private static void insertPeople(SQLiteDatabase db, String namesake,
                                    String name_last, String name_first, String name_middle,
                                    int date_born, int date_death,
                                    String description, int image_id) {
        ContentValues peopleValues = new ContentValues();
        peopleValues.put("NAMESAKE", namesake);
        peopleValues.put("NAME_LAST", name_last);
        peopleValues.put("NAME_FIRST", name_first);
        peopleValues.put("NAME_MIDDLE", name_middle);
        peopleValues.put("DATE_BORN", date_born);
        peopleValues.put("DATE_DEATH", date_death);
        peopleValues.put("DESCRIPTION", description);
        peopleValues.put("IMAGE_ID", image_id);
        db.insert("PEOPLE", null, peopleValues);
    }

    private static void insertSitesToPeople (SQLiteDatabase db, String site_name,
                                             String name_person) {
        ContentValues sitesPeopleValues = new ContentValues();
        sitesPeopleValues.put("SITE_NAME", site_name);
        sitesPeopleValues.put("NAME_PERSON", name_person);
        db.insert("SITE_TO_PEOPLE", null, sitesPeopleValues);
    }

    private static void insertImageSites (SQLiteDatabase db, String site_name,
                                          String image_id, String file) {
        ContentValues imageSitesValues = new ContentValues();
        imageSitesValues.put("SITE_NAME", site_name);
        imageSitesValues.put("IMAGE_ID", image_id);
        imageSitesValues.put("FILE", file);
        db.insert("IMAGES_SITES", null, imageSitesValues);
    }

    private static void insertImagesPeople (SQLiteDatabase db, String person_name,
                                            String image_id, String file) {
        ContentValues imagePeopleValues = new ContentValues();
        imagePeopleValues.put("SITE_NAME", person_name);
        imagePeopleValues.put("IMAGE_ID", image_id);
        imagePeopleValues.put("FILE", file);
        db.insert("IMAGES_PEOPLE", null, imagePeopleValues);
    }
}

