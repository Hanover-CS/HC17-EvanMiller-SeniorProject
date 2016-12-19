package com.enm.hch;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class HCHDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "HCH";
    private static final int DB_VERSION = 1;

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
                + "NAMESAKE TEXT, "
                + "LATITUDE DOUBLE, "
                + "LONGITUDE DOUBLE, "
                + "IMAGE_ID INTEGER);");

        //INSERT SITES
        insertSites(db, "Classic Hall", 1947, 2017,
                "The construction of Classic Hall, also known as 'New' Classic Hall, " +
                        "was completed in 1947. The building was built to replace Old " +
                        "Classic Hall, which had been demolished after a fire in 1941. " +
                        "The academic building was last renovated in 2002.",
                "None", 38.713063, -85.458299, 1234);
        insertSites(db, "Hendricks Hall", 1900, 1901,
                "Fa La La La La", "VP Hendricks",
                38.712584, -85.457797, 1234);
        insertSites(db, "Parker Auditorium", 1900, 1901,
                "Fa La La La La", "President Parker",
                38.713423, -85.457188, 1234);
        insertSites(db, "Science Center", 1900, 1901,
                "Fa La La La La", "Goodrich",
                38.714262, -85.458282, 1234);
        insertSites(db, "Science Hall", 1900, 1901,
                "Fa La La La La", "None",
                38.713917, -85.459202, 1234);
        insertSites(db, "Newby Hall", 1900, 1901,
                "Fa La La La La", "Newby",
                38.714366, -85.461857, 1234);
        insertSites(db, "Faculty Office Building", 1900, 1901,
                "Fa La La La La", "None",
                38.714578, -85.458873, 1234);
        insertSites(db, "Lynn Hall", 1900, 1901,
                "Fa La La La La", "Lynn",
                38.714588, -85.459754, 1234);
        insertSites(db, "Duggan Library", 1900, 1901,
                "Fa La La La La", "Duggan",
                38.715624, -85.460044, 1234);
        insertSites(db, "Lynn Center for the Fine Arts (CFA)", 1900, 1901,
                "Fa La La La La", "Lynn",
                38.716473, -85.460089, 1234);


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
        //TESTING PEOPLE
        insertPeople(db, "Alumnus Test Guy",
                "Guy", "Alumnus", "Test", 1900, 2000,
                "Description", 1234);
        insertPeople(db, "Faculty Test Guy",
                "Guy", "Faculty", "Test", 1900, 2000,
                "Description", 1234);
        insertPeople(db, "President Test Guy",
                "Guy", "President", "Test", 1900, 2000,
                "Description", 1234);
        insertPeople(db, "Relative Test Guy",
                "Guy", "Relative", "Test", 1900, 2000,
                "Description", 1234);
        insertPeople(db, "Local Test Guy",
                "Guy", "Local", "Test", 1900, 2000,
                "Description", 1234);


        //
        //CREATE TABLE SITES_TO_SITE_TYPE
        //
        db.execSQL("CREATE TABLE SITES_TO_SITE_TYPE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "SITE_NAME STRING, "
                + "ACADEMIC_BUILDING STRING, "
                + "STUDENT_HOUSING STRING, "
                + "ADMINISTRATION STRING, "
                + "OUTSIDE STRING, "
                + "MEMORIAL STRING, "
                + "OLD_CAMPUS STRING);");

        //INSERT SITES_TO_SITE_TYPE
        insertSitesToSiteType(db, "Classic Hall", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Hendricks Hall", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Parker Auditorium", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Science Center", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Science Hall", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Newby Hall", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Faculty Office Building", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Lynn Hall", "Y", "Y", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Duggan Library", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Lynn Center for the Fine Arts (CFA)", "Y", "N", "N", "N", "N", "N");


        //
        //CREATE TABLE SITE_TYPE
        //
        db.execSQL("CREATE TABLE SITE_TYPE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "CATEGORY TEXT, "
                + "CATEGORY_PROPER TEXT, "
                + "DESCRIPTION TEXT);");

        //INSERT SITE_TYPE
        insertSiteType(db, "ACADEMIC_BUILDING", "Academic Building", "INFO");
        insertSiteType(db, "STUDENT_HOUSING", "Student Housing", "INFO");
        insertSiteType(db, "ADMINISTRATION", "Administration", "INFO");
        insertSiteType(db, "OUTSIDE", "Outside", "INFO");
        insertSiteType(db, "MEMORIAL", "Memorial", "INFO");
        insertSiteType(db, "OLD_CAMPUS", "Old Campus", "INFO");


        //
        //CREATE TABLE SITES_TO_PEOPLE
        //
        db.execSQL("CREATE TABLE SITES_TO_PEOPLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "SITE_NAME TEXT, "
                + "NAMESAKE TEXT);");

        //INSERT SITES_TO_PEOPLE
        insertSitesToPeople(db, "Classic Hall", "NONE");
        insertSitesToPeople(db, "Hendricks Hall", "Vice President Thomas A. Hendricks");
        insertSitesToPeople(db, "Parker Auditorium", "President Parker");
        insertSitesToPeople(db, "Science Center", "NONE");
        insertSitesToPeople(db, "Science Hall", "NONE");
        insertSitesToPeople(db, "Newby Hall", "Mr. Newby");
        insertSitesToPeople(db, "Faculty Office Building", "NONE");
        insertSitesToPeople(db, "Lynn Hall", "Mr. and Mrs. Lynn");
        insertSitesToPeople(db, "Duggan Library", "Mrs. Duggan");
        insertSitesToPeople(db, "Lynn Center for the Fine Arts (CFA)", "Mr. and Mrs. Lynn");


        //
        //CREATE TABLE PEOPLE_TO_CONNECTION
        db.execSQL("CREATE TABLE PEOPLE_TO_CONNECTION (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAMESAKE TEXT, "
                + "ALUMNUS TEXT, "
                + "FACULTY_STAFF TEXT, "
                + "PRESIDENT TEXT, "
                + "RELATIVE TEXT, "
                + "LOCAL TEXT);");

        //INSERT PEOPLE_TO_CONNECTION
        insertPeopleToConnection(db, "Vice President Thomas A. Hendricks",
                "Y", "N", "N", "N", "N");
        //TESTING
        //TESTING PEOPLE
        insertPeopleToConnection(db, "Alumnus Test Guy", "Y", "N", "N", "N", "N");
        insertPeopleToConnection(db, "Faculty Test Guy", "N", "Y", "N", "N", "N");
        insertPeopleToConnection(db, "President Test Guy", "N", "N", "Y", "N", "N");
        insertPeopleToConnection(db, "Relative Test Guy", "N", "N", "N", "Y", "N");
        insertPeopleToConnection(db, "Local Test Guy", "N", "N", "N", "N", "Y");


        //
        //CREATE TABLE CONNECTION_PEOPLE
        //
        db.execSQL("CREATE TABLE CONNECTION_PEOPLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "CATEGORY TEXT, "
                + "CATEGORY_PROPER TEXT, "
                + "DESCRIPTION TEXT);");

        //INSERT CONNECTION_PEOPLE
        insertConnectionPeople(db, "ALUMNUS", "Alumnus", "Description");
        insertConnectionPeople(db, "FACULTY_STAFF", "Faculty & Staff", "Description");
        insertConnectionPeople(db, "PRESIDENT", "President", "Description");
        insertConnectionPeople(db, "RELATIVE", "Relative", "Description");
        insertConnectionPeople(db, "LOCAL", "Local", "Description");



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
                                    String description, String namesake,
                                    double latitude, double longitude,
                                    int image_id) {
        ContentValues siteValues = new ContentValues();
        siteValues.put("SITE_NAME", site_name);
        siteValues.put("DATE_BUILT", date_built);
        siteValues.put("DATE_DESTROYED", date_destroyed);
        siteValues.put("DESCRIPTION", description);
        siteValues.put("NAMESAKE", namesake);
        siteValues.put("LATITUDE", latitude);
        siteValues.put("LONGITUDE", longitude);
        siteValues.put("IMAGE_ID", image_id);
        db.insert("SITES", null, siteValues);
    }

    private static void insertPeople(SQLiteDatabase db, String namesake,
                                     String name_last, String name_first, String name_middle,
                                     int date_birth, int date_death,
                                     String description, int image_id) {
        ContentValues peopleValues = new ContentValues();
        peopleValues.put("NAMESAKE", namesake);
        peopleValues.put("NAME_LAST", name_last);
        peopleValues.put("NAME_FIRST", name_first);
        peopleValues.put("NAME_MIDDLE", name_middle);
        peopleValues.put("DATE_BIRTH", date_birth);
        peopleValues.put("DATE_DEATH", date_death);
        peopleValues.put("DESCRIPTION", description);
        peopleValues.put("IMAGE_ID", image_id);
        db.insert("PEOPLE", null, peopleValues);
    }

    private static void insertSitesToSiteType(SQLiteDatabase db, String site_name,
                                              String academic_building, String student_housing,
                                              String administration, String outside,
                                              String memorial, String old_campus) {
        ContentValues sitesSiteTypeValues = new ContentValues();
        sitesSiteTypeValues.put("SITE_NAME", site_name);
        sitesSiteTypeValues.put("ACADEMIC_BUILDING", academic_building);
        sitesSiteTypeValues.put("STUDENT_HOUSING", student_housing);
        sitesSiteTypeValues.put("ADMINISTRATION", administration);
        sitesSiteTypeValues.put("OUTSIDE", outside);
        sitesSiteTypeValues.put("MEMORIAL", memorial);
        sitesSiteTypeValues.put("OLD_CAMPUS", old_campus);
        db.insert("SITES_TO_SITE_TYPE", null, sitesSiteTypeValues);
    }

    private static void insertSiteType(SQLiteDatabase db, String category,
                                       String category_proper, String description) {
        ContentValues siteTypeValues = new ContentValues();
        siteTypeValues.put("CATEGORY", category);
        siteTypeValues.put("CATEGORY_PROPER", category_proper);
        siteTypeValues.put("DESCRIPTION", description);
        db.insert("SITE_TYPE", null, siteTypeValues);
    }

    private static void insertSitesToPeople(SQLiteDatabase db, String site_name,
                                            String namesake) {
        ContentValues sitesPeopleValues = new ContentValues();
        sitesPeopleValues.put("SITE_NAME", site_name);
        sitesPeopleValues.put("NAMESAKE", namesake);
        db.insert("SITES_TO_PEOPLE", null, sitesPeopleValues);
    }

    private static void insertPeopleToConnection(SQLiteDatabase db, String namesake,
                                                 String alumnus, String faculty_staff,
                                                 String president, String relative,
                                                 String local) {
        ContentValues peopleValues = new ContentValues();
        peopleValues.put("NAMESAKE", namesake);
        peopleValues.put("ALUMNUS", alumnus);
        peopleValues.put("FACULTY_STAFF", faculty_staff);
        peopleValues.put("PRESIDENT", president);
        peopleValues.put("RELATIVE", relative);
        peopleValues.put("LOCAL", local);
        db.insert("PEOPLE_TO_CONNECTION", null, peopleValues);
    }

    private static void insertConnectionPeople(SQLiteDatabase db, String category,
                                               String category_proper, String description) {
        ContentValues connectionPeopleValues = new ContentValues();
        connectionPeopleValues.put("CATEGORY", category);
        connectionPeopleValues.put("CATEGORY_PROPER", category_proper);
        connectionPeopleValues.put("DESCRIPTION", description);
        db.insert("CONNECTION_PEOPLE", null, connectionPeopleValues);
    }

    private static void insertImageSites(SQLiteDatabase db, String site_name,
                                         String image_id, String file) {
        ContentValues imageSitesValues = new ContentValues();
        imageSitesValues.put("SITE_NAME", site_name);
        imageSitesValues.put("IMAGE_ID", image_id);
        imageSitesValues.put("FILE", file);
        db.insert("IMAGES_SITES", null, imageSitesValues);
    }

    private static void insertImagesPeople(SQLiteDatabase db, String person_name,
                                           String image_id, String file) {
        ContentValues imagePeopleValues = new ContentValues();
        imagePeopleValues.put("SITE_NAME", person_name);
        imagePeopleValues.put("IMAGE_ID", image_id);
        imagePeopleValues.put("FILE", file);
        db.insert("IMAGES_PEOPLE", null, imagePeopleValues);
    }
}
