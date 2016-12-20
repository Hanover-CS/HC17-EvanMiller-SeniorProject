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
                + "LONGITUDE DOUBLE);");

        /*
        INSERT SITES
        */
        //ACADEMIC
        insertSites(db, "Classic Hall", 1947, 12345,
                "The construction of Classic Hall, also known as 'New' Classic Hall, " +
                        "was completed in 1947. The building was built to replace Old " +
                        "Classic Hall, which had been demolished after a devastating fire in 1941. " +
                        "The academic building was last renovated in 2002.",
                "None", 38.713063, -85.458299);
        insertSites(db, "Hendricks Hall", 1903, 12345,
                "Hendricks Hall, originally Hendricks Library, was opened to students in 1903. The money" +
                        "for the building was donated by Eliza C. Morgan Hendricks, the wife of " +
                        "the United States Vice President Thomas A. Hendricks. Hendricks graduated from" +
                        "Hanover College in 1840. Hendricks Library became Hendricks Hall in 1952 when" +
                        "a new college library was built. Hendricks Hall has served many purposes through" +
                        "the years including classrooms, Computer Science labs, Business Scholar Program offices," +
                        "and most recently the Hanover College Career Center.",
                "Vice President Thomas A. Hendricks", 38.712584, -85.457797);
        insertSites(db, "Parker Auditorium", 1947, 12345,
                "Parker Auditorium was built in celebration of Albert G. Parker Jr., 12th president of" +
                        "Hanover College, and his twenty-five years of service to the college. The building" +
                        "served as a main gathering space for campus events and chapel services in its " +
                        "early days. Parker Auditorium has also held many theatre productions throughout its" +
                        "history.",
                "President Parker", 38.713423, -85.457188);
        insertSites(db, "Science Center", 2000, 12345,
                "Science Center is the largest academic building on Hanover College's campus. It's " +
                        "construction took place in the late 1990's and was completed in 2000. The south side" +
                        "of the building was originally Goodrich Hall, which was renovated and joined to " +
                        "Science Center. Goodrich Hall was built in 1947 in conjunction with Classic Hall and was " +
                        "named after P. E. Goodrich, president of the Hanover College Board of Trustees from " +
                        "1930-1948.",
                "P. E. Goodrich", 38.714262, -85.458282);
        insertSites(db, "Science Hall", 1952, 12345,
                "Science Hall was originally built in 1952 as the new Hanover College Library, replacing Hendricks Library. " +
                        "at the time. The library was then moved to its current residence in the Hanover " +
                        "College Duggan Library in 1973 upon the new building's completion. Science Hall, also " +
                        "referred to as 'Old Science Hall', has served many purposes through the years including " +
                        "classrooms, faculty offices, and Computer Science Lab.",
                "None", 38.713917, -85.459202);
        insertSites(db, "Newby Hall", 1939, 12345,
                "Newby Hall was originally built as the Hanover College Newby Hospital in 1939 after " +
                        "NEWBY...? In 1963, Newby Hospital was renovated and enlarged to accommodate a " +
                        "growing student population. It is unclear when the building ceased being a campus " +
                        "hospital. Today, Newby Hall serves as the headquarters for the Education department," +
                        "providing classrooms and faculty offices.",
                "NEWBY...?", 38.714366, -85.461857);
        insertSites(db, "Faculty Office Building", 1931, 12345,
                "The Faculty Office Building (FOB) was originally built in 1931 as a fraternity house for the" +
                        "Phi Delta Theta fraternity. In 1969, Phi Delta Theta built a new fraternity" +
                        "house and left their old residence for the use of Hanover College. It is unclear " +
                        "exactly when the building was officially renamed the Faculty Office Building, " +
                        "but today, the building serves as classrooms and faculty offices.",
                "None", 38.714578, -85.458873);
        insertSites(db, "Lynn Hall", 1947, 12345,
                "Lynn Hall was built in 1947 as Lynn Gymnasium. The building was named after Charles J. " +
                        "Lynn, president of the Hanover College Board of Trustees from 1948-1958. " +
                        "It served as the official gymnasium at " +
                        "Hanover College until Long Gymnasium was built in 1957 (located on the current " +
                        "site of Horner Health and Recreational Center). Lynn Gymnasium (also referred to as " +
                        "'Lynn Gymn') served as a practice gym, meeting space, and recreational area until " +
                        "2013 when renovations began on the building. In the fall of 2014, Lynn Hall opened " +
                        "as an academic building and option for campus housing, offering two classrooms, " +
                        "faculty offices, and suite-style dorm rooms. Today, the building also serves as the " +
                        "home of the Computer Science department.",
                "Charles J. Lynn", 38.714588, -85.459754);
        insertSites(db, "Duggan Library", 1973, 12345,
                "The Duggan Library was built in 1973 to replace the old Hanover College Library (now " +
                        "Science Hall). The library was named after Mrs. A. L. Duggan, sister of J. Graham Brown" +
                        "who attended Hanover College from 1898-1900, who donated the money to construct the " +
                        "building. Less than a year after its completion, a tornado hit the building in 1974, " +
                        "tearing the roof off; the building was quickly repaired. A major renovation " +
                        "took place in the early 2000's, renovating the third floor for student use and" +
                        "adding an official space for the Hanover College Archives.",
                "Mrs. A. L. Duggan", 38.715624, -85.460044);
        insertSites(db, "Lynn Center for the Fine Arts", 1978, 12345,
                "The Lynn Center for the Fine Arts (also referred to as CFA) was built in 1978. It was " +
                        "named after Mr. and Mrs. Charles J. Lynn. Charles J. Lynn served as the president " +
                        "of the Hanover College Board of Trustees from 1948-1958." +
                        "The building serves as the current home for the Mathematics, " +
                        "Communications, Studio Art and Design, and Art History departments. ",
                "Charles J. Lynn", 38.716473, -85.460089);

        //STUDENT HOUSING
        insertSites(db, "Donner Residence Hall", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Ide Residence Hall", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Katherine Parker Residence Hall", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Blythe Residence Hall", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Crowe Residence Hall", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Wiley Residence Hall", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Coulter Residence Hall", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Ogle Suites", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Greenwood Suites", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "File House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Young House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);

        //GREEK LIFE
        insertSites(db, "Lambda Chi Alpha Fraternity House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Phi Delta Theta Fraternity House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Phi Gamma Delta Fraternity House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Sigma Chi Fraternity House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Alpha Delta Pi Sorority House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Chi Omega Sorority House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Kappa Alpha Theta Sorority House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Phi Mu Sorority House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);

        //ADMINISTRATION
        insertSites(db, "Long Administration Building", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Admission Building", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Academic Computing Center", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);

        //CAMPUS_LIFE
        insertSites(db, "Brown Memorial Chapel", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "President's House", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Brown Campus Center", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Horner Health and Recreational Center", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Shoebox", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "YMCA", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Culbertson Observatory", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Panther Athletic Conference", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);

        //OUTSIDE
        insertSites(db, "The Point", 1900, 12345,
                "Fa La La La La", "",
                0.0, 0.0);

        //MEMORIAL

        //OLD CAMPUS
        insertSites(db, "Old Classic Hall", 1900, 1901,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Old Science Hall", 1900, 1901,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "College Point House", 1900, 1901,
                "Fa La La La La", "",
                0.0, 0.0);
        insertSites(db, "Long Gymnasium", 1900, 1901,
                "Fa La La La La", "",
                0.0, 0.0);


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
                + "GREEK_HOUSING STRING, "
                + "ADMINISTRATION STRING, "
                + "CAMPUS_LIFE STRING, "
                + "OUTSIDE STRING, "
                + "MEMORIAL STRING, "
                + "OLD_CAMPUS STRING);");

        //INSERT SITES_TO_SITE_TYPE
        insertSitesToSiteType(db, "Classic Hall", "Y", "N", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Hendricks Hall", "Y", "N", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Parker Auditorium", "Y", "N", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Science Center", "Y", "N", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Science Hall", "Y", "N", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Newby Hall", "Y", "N", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Faculty Office Building", "Y", "N", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Lynn Hall", "Y", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Duggan Library", "Y", "N", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Lynn Center for the Fine Arts", "Y", "N", "N", "N", "N", "N", "N", "N");

        insertSitesToSiteType(db, "Donner Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Ide Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Katherine Parker Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Blythe Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Crowe Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Wiley Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Coulter Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Ogle Suites", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Greenwood Suites", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "File House", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Young House", "N", "Y", "N", "N", "N", "N", "N", "N");

        insertSitesToSiteType(db, "Lambda Chi Alpha Fraternity House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Phi Delta Theta Fraternity House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Phi Gamma Delta Fraternity House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Sigma Chi Fraternity House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Alpha Delta Pi Sorority House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Chi Omega Sorority House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Kappa Alpha Theta Sorority House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Phi Mu Sorority House", "N", "N", "Y", "N", "N", "N", "N", "N");

        insertSitesToSiteType(db, "Long Administration Building", "N", "N", "N", "Y", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Admissions Building", "N", "N", "N", "Y", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Academic Computing Center", "N", "N", "N", "Y", "N", "N", "N", "N");

        insertSitesToSiteType(db, "Brown Memorial Chapel", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "President's House", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Brown Campus Center", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Horner Health and Recreational Center", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Shoebox", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "YMCA", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Culbertson Observatory", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Panther Athletic Complex", "N", "N", "N", "N", "Y", "N", "N", "N");

        insertSitesToSiteType(db, "Old Classic Hall", "N", "N", "N", "N", "N", "N", "N", "Y");
        insertSitesToSiteType(db, "Old Science Hall", "N", "N", "N", "N", "N", "N", "N", "Y");
        insertSitesToSiteType(db, "College Point House", "N", "N", "N", "N", "N", "N", "N", "Y");
        insertSitesToSiteType(db, "Long Gymnasium", "N", "N", "N", "N", "N", "N", "N", "Y");

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
        insertSiteType(db, "GREEK_HOUSING", "Greek Housing", "INFO");
        insertSiteType(db, "ADMINISTRATION", "Administration", "INFO");
        insertSiteType(db, "CAMPUS_LIFE", "Campus Life", "INFO");
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
        insertSitesToPeople(db, "Lynn Center for the Fine Arts", "Mr. and Mrs. Lynn");

        insertSitesToPeople(db, "Donner Residence Hall", "William H. Donner");
        insertSitesToPeople(db, "Ide Residence Hall", "Mrs. John J. Ide");
        insertSitesToPeople(db, "Katherine Parker Residence Hall", "Katherine Parker");
        insertSitesToPeople(db, "Blythe Residence Hall", "James Blythe");
        insertSitesToPeople(db, "Crowe Residence Hall", "John Finley Crowe");
        insertSitesToPeople(db, "Wiley Residence Hall", "Harvey W. Wiley");
        insertSitesToPeople(db, "Coulter Residence Hall", "Coulter");
        insertSitesToPeople(db, "Ogle Suites", "Ogle");
        insertSitesToPeople(db, "Greenwood Suites", "Greenwood");
        insertSitesToPeople(db, "File House", "File");
        insertSitesToPeople(db, "Young House", "Young");

        insertSitesToPeople(db, "Lambda Chi Alpha Fraternity House", "NONE");
        insertSitesToPeople(db, "Phi Delta Theta Fraternity House", "NONE");
        insertSitesToPeople(db, "Phi Gamma Delta Fraternity House", "NONE");
        insertSitesToPeople(db, "Sigma Chi Fraternity House", "NONE");
        insertSitesToPeople(db, "Alpha Delta Pi Sorority House", "NONE");
        insertSitesToPeople(db, "Chi Omega Sorority House", "NONE");
        insertSitesToPeople(db, "Kappa Alpha Theta Sorority House", "NONE");
        insertSitesToPeople(db, "Phi Mu Sorority House", "NONE");

        insertSitesToPeople(db, "Long Administration Building", "Henry C. Long");
        insertSitesToPeople(db, "Admission Building", "NONE");
        insertSitesToPeople(db, "Academic Computing Center", "NONE");

        insertSitesToPeople(db, "Brown Memorial Chapel", "James Graham Brown");
        insertSitesToPeople(db, "President's House", "NONE");
        insertSitesToPeople(db, "Brown Campus Center", "James Graham Brown");
        insertSitesToPeople(db, "Horner Health and Recreational Center", "John E. Horner");
        insertSitesToPeople(db, "Shoebox", "NONE");
        insertSitesToPeople(db, "YMCA", "NONE");
        insertSitesToPeople(db, "Culbertson Observatory", "Glenn Culbertson");
        insertSitesToPeople(db, "Panther Athletic Complex", "Lyman S. Ayers");

        insertSitesToPeople(db, "Old Classic Hall", "NONE");
        insertSitesToPeople(db, "Old Science Hall", "NONE");
        insertSitesToPeople(db, "College Point House", "NONE");
        insertSitesToPeople(db, "Long Gymnasium", "Henry C. Long");

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
                                    double latitude, double longitude) {
        ContentValues siteValues = new ContentValues();
        siteValues.put("SITE_NAME", site_name);
        siteValues.put("DATE_BUILT", date_built);
        siteValues.put("DATE_DESTROYED", date_destroyed);
        siteValues.put("DESCRIPTION", description);
        siteValues.put("NAMESAKE", namesake);
        siteValues.put("LATITUDE", latitude);
        siteValues.put("LONGITUDE", longitude);
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
                                              String greek_housing, String administration,
                                              String campus_life, String outside,
                                              String memorial, String old_campus) {
        ContentValues sitesSiteTypeValues = new ContentValues();
        sitesSiteTypeValues.put("SITE_NAME", site_name);
        sitesSiteTypeValues.put("ACADEMIC_BUILDING", academic_building);
        sitesSiteTypeValues.put("STUDENT_HOUSING", student_housing);
        sitesSiteTypeValues.put("GREEK_HOUSING", greek_housing);
        sitesSiteTypeValues.put("ADMINISTRATION", administration);
        sitesSiteTypeValues.put("CAMPUS_LIFE", campus_life);
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
