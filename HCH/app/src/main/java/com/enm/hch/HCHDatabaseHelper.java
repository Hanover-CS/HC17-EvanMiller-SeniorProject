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
                "Newby Hall was originally built as the Hanover College Newby Hospital in 1939. Funds were donated " +
                        "by Arthur C. Newby, a friend of a Hanover College trustee. " +
                        "In 1963, Newby Hospital was renovated and enlarged to accommodate a " +
                        "growing student population and serve as classrooms. Today, Newby Hall serves as the " +
                        "headquarters for the Education department, providing classrooms and faculty offices.",
                "Arthur C. Newby", 38.714366, -85.461857);
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
                        "'Lynn Gym') served as a practice gym, meeting space, and recreational area until " +
                        "2013 when renovations began on the building. In the fall of 2014, Lynn Hall opened " +
                        "as an academic building and option for campus housing, offering two classrooms, " +
                        "faculty offices, and suite-style dorm rooms. Today, the building also serves as the " +
                        "home of the Computer Science department.",
                "Charles J. Lynn", 38.714588, -85.459754);
        insertSites(db, "Duggan Library", 1973, 12345,
                "The Duggan Library was built in 1973 to replace the old Hanover College Library (now " +
                        "Science Hall). The library is named in honor of Mrs. Agnus L. Brown Duggan, " +
                        "sister of J. Graham Brown (who attended Hanover College from 1898-1900) and " +
                        "donor of the money to construct the " +
                        "building. Less than a year after its completion, a tornado hit the building in 1974, " +
                        "tearing the roof off; the building was quickly repaired. A major renovation " +
                        "took place in the early 2000's, renovating the third floor for student use and" +
                        "adding an official space for the Hanover College Archives.",
                "Agnus L. Brown Duggan", 38.715624, -85.460044);
        insertSites(db, "Lynn Center for the Fine Arts", 1978, 12345,
                "The Lynn Center for the Fine Arts (also referred to as CFA) was built in 1978. It was " +
                        "named after Mr. and Mrs. Charles J. Lynn. Charles J. Lynn served as the president " +
                        "of the Hanover College Board of Trustees from 1948-1958." +
                        "The building serves as the current home for the Mathematics, " +
                        "Communications, Studio Art and Design, and Art History departments. ",
                "Charles J. Lynn", 38.716473, -85.460089);

        //STUDENT HOUSING
        insertSites(db, "Donner Residence Hall", 1939, 12345,
                "",
                "William H. Donner", 38.713380, -85.461991);
        insertSites(db, "Ide Residence Hall", 1951, 12345,
                "",
                "Mrs. John J. Ide", 38.713368, -85.462475);
        insertSites(db, "Katherine Parker Residence Hall", 1964, 12345,
                "",
                "Katherine Parker", 38.713276, -85.463051);
        insertSites(db, "Blythe Residence Hall", 1956, 12345,
                "",
                "James Blythe", 38.714691, -85.462096);
        insertSites(db, "Crowe Residence Hall", 1949, 12345,
                "",
                "John Finley Crowe", 38.714788, -85.460660);
        insertSites(db, "Wiley Residence Hall", 1963, 12345,
                "",
                "Harvey W. Wiley", 38.715628, -85.461520);
        //UNKNOWN
        insertSites(db, "Coulter Residence Hall", 1900, 12345,
                "",
                "Coulter", 38.717656, -85.462175);
        //UNKNOWN
        insertSites(db, "Ogle Suites", 1900, 12345,
                "",
                "Josephine Ogle", 38.712780, -85.464459);
        //UNKNOWN
        insertSites(db, "Greenwood Suites", 1900, 12345,
                "",
                "None", 38.717104, -85.462500);


        //GREEK LIFE
        insertSites(db, "Lambda Chi Alpha Fraternity House", 1955, 12345,
                "",
                "None", 38.716892, -85.461540);
        insertSites(db, "Phi Delta Theta Fraternity House", 1969, 12345,
                "",
                "None", 38.718544, -85.461024);
        insertSites(db, "Phi Gamma Delta Fraternity House", 1955, 12345,
                "",
                "None", 38.712477, -85.462598);
        insertSites(db, "Sigma Chi Fraternity House", 1955, 12345,
                "",
                "None", 38.716565, -85.461530);
        insertSites(db, "Alpha Delta Pi Sorority House", 1954, 12345,
                "",
                "None", 38.713679, -85.463206);
        insertSites(db, "Chi Omega Sorority House", 1954, 12345,
                "",
                "None", 38.713920, -85.462968);
        insertSites(db, "Kappa Alpha Theta Sorority House", 1971, 12345,
                "",
                "None", 38.713837, -85.464092);
        insertSites(db, "Phi Mu Sorority House", 1954, 12345,
                "",
                "None", 38.713913, -85.463439);


        //ADMINISTRATION
        insertSites(db, "Long Administration Building", 1963, 12345,
                "",
                "Henry C. Long", 38.712451, -85.461101);
        //UNKNOWN
        insertSites(db, "Admission Building", 1900, 12345,
                "",
                "None", 38.714356, -85.461486);
        //UNKNOWN
        insertSites(db, "Academic Computing Center", 1900, 12345,
                "",
                "None", 38.714536, -85.461404);


        //CAMPUS_LIFE
        insertSites(db, "Brown Memorial Chapel", 1956, 12345,
                "",
                "Mr. & Mrs. William Pool Brown", 38.713000, -85.459784);
        insertSites(db, "Brown Campus Center", 1967, 12345,
                "",
                "James Graham Brown", 38.713801, -85.460400);
        insertSites(db, "Horner Health and Recreational Center", 1995, 12345,
                "",
                "John E. Horner", 38.715023, -85.462316);
        insertSites(db, "President's House", 1952, 12345,
                "",
                "None", 38.712941, -85.459196);
        insertSites(db, "Shoebox", 1900, 12345,
                "",
                "None", 38.719390, -85.461238);
        insertSites(db, "YMCA", 1900, 12345,
                "",
                "None", 38.720722, -85.462305);

        /*
        //OLD CAMPUS
        //UNKNOWN
        insertSites(db, "Old Classic Hall", 1900, 1901,
                "",
                "None", 38.712572, -85.456412);
        //UNKNOWN
        insertSites(db, "Old Science Hall", 1900, 1901,
                "",
                "None", 38.712972, -85.457066);
        //UNKNOWN
        insertSites(db, "College Point House", 1900, 1901,
                "",
                "None", 38.712480, -85.461738);
        insertSites(db, "Long Gymnasium", 1957, 1992,
                "",
                "Henry C. Long", 38.715027, -85.463144);
        */

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
                + "GENDER STRING, "
                + "DESCRIPTION TEXT);");

        //
        //INSERT PEOPLE
        //

        //PRESIDENTS
        insertPeople(db, "James Blythe",
                "Blythe", "James", "NONE",
                1765, 1842, "M",
                "Rev. James Blythe served as the first president of Hanover College's from 1832-1836. " +
                        "Blythe came to Hanover College from Transylvania " +
                        "University where he had also served as president. As the first president of Hanover " +
                        "College, he witnessed and oversaw a tremendous amount of growth for the college, " +
                        "including the College's charter from the Indiana State Legislature, the " +
                        "construction of the College Edifice (the College's first academic building, " +
                        "which stands today as the Hanover Presbyterian Church), many successful " +
                        "fundraisers for the College's endowment and classroom tools, first academic " +
                        "catalogue, founding of the Alumni Association, and Hanover College's first " +
                        "graduating class in 1834 of seven students. Blythe was also a pastor and " +
                        "served in said capacity after his time at Hanover College until his death.");
        insertPeople(db, "Duncan McAuley",
                "McAuley", "Duncan", "NONE",
                1900, 2000, "M",
                "Description");
        insertPeople(db, "Eramus D. MacMaster",
                "MacMaster", "Eramus", "D.",
                1805, 1866, "M",
                "Rev. Eramus D. MacMaster served as the third president of Hanover College from 1838-1845. " +
                        "MacMaster oversaw the set up of a bookkeeping system and the sale of some of " +
                        "the College's property to pay off debts, which in turn put the College in " +
                        "good financial standing. He oversaw the move of Hanover College to Madison, Indiana, in " +
                        "1845, renaming the institution to Madison University. However, due to decreased " +
                        "student enrollment, MacMaster resigned as president. The institution was " +
                        "moved back to Hanover within the next year, and the name Hanover " +
                        "College was reinstated.");
        insertPeople(db, "Sylvester D. Scovel",
                "Scovel", "Sylvester", "D.",
                1796, 1849, "M",
                "Sylvester Scovel served as the fourth president of Hanover College from 1846-1849. " +
                        "During his short time as president, Scovel worked to repair the financial " +
                        "situation and lack of public trust from the community, faculty, and students " +
                        " that resulted from the College's short-lived move to Madison, Indiana. He " +
                        "accomplished this by not accepting a salary his first year in office, working " +
                        "as the College treasurer for no extra fee, convincing the founder of Hanover College " +
                        "John Finley Crowe to work for free for a period of time, campaigned across the " +
                        "Midwest for funds, and doubled the student enrollment. Scovel died in office " +
                        "after contracting cholera during an epidemic in the surrounding area.");
        insertPeople(db, "Thomas E. Thomas",
                "Thomas", "Thomas", "Ebenezer",
                12345, 1875, "M",
                "Rev. Thomas Ebenezer Thomas served as the fifth president of Hanover College from 1849-1854. " +
                        "Thomas oversaw two main accomplishments for Hanover College. First, he purchased " +
                        "the land that the College currently resides (known as Campbell's Farm). Second, " +
                        "he began the construction of the first academic building on the campus's current " +
                        "location: Old Classic Hall (1855-1941). Thomas resigned from the presidency of " +
                        "Hanover College to become a professor at a theological seminary.");
        insertPeople(db, "Jonathan Edwards",
                "Edwards", "Jonathan", "NONE",
                12345, 1891, "M",
                "Rev. Jonathan Edwards served as the sixth president of Hanover College from 1855-1857. " +
                        "Edwards oversaw the move of the College into the new academic building known " +
                        "today as Old Classic Hall (1855-1941). He also increased the College's endowment, " +
                        "passing $100,000 for the first time. Edwards resigned at the end of his second year. " +
                        "Edwards is the only Hanover College president to be an alum of the College, being " +
                        "a graduate of the class of 1835.");
        insertPeople(db, "James Wood",
                "Wood", "James", "NONE",
                12345, 12345, "M",
                "Rev. James Wood served as the seventh president of Hanover College from 1859-1866, " +
                        "covering the time period of the Civil War. Wood kept the College afloat during " +
                        "this tumultuous time, keeping the doors open even when so many young men were " +
                        "off fighting. With student enrollment low, the three faculty pulling double " +
                        "loads, and finances stretched thin, Wood was able to keep the College going " +
                        "by collecting overdue financial pledges from the Presbyterian Church, its members, " +
                        "and wealthy patrons. After the end of the war, student enrollment increased. " +
                        "Wood resigned in 1866 to accept the presidency at a different college.");
        insertPeople(db, "George D. Archibald",
                "Archibald", "George", "D.",
                12345, 12345, "M",
                "Rev. George Dr. Archibald served as the eighth president of Hanover College from 1868-1870. " +
                        "Archibald oversaw several significant changes at Hanover College including the " +
                        "partial admittance of women to the College (women were allowed to take classes but " +
                        "not allowed tp graduate) and restructured and expanded the science curriculum, thus " +
                        "elevating the College's bachelor of science degree. As well, he prevented the " +
                        "College from relocating to Indianapolis in a proposed deal with Wabash College in " +
                        "Crawfordsville, Indiana. Before becoming president, Archibald had served on the " +
                        "Board of Trustees at Hanover College. ");
        insertPeople(db, "George C. Heckman",
                "Heckman", "George", "C.",
                12345, 12345, "M",
                "Rev. George C. Heckman served as the ninth president of Hanover College from 1870-1879. " +
                        "Heckman saw financial distress during his time as president. While student " +
                        "enrollment increased, faculty and their salaries increased as well, causing a " +
                        "financial strain on the College (the Board of Trustees barely voted to not close " +
                        "the College during this time). The College was able to collect the amount of " +
                        "money to pay off immediate debts, but this led to Heckman's resignation in 1879. " +
                        "One legacy that Heckman left was the first President's House: a three-story " +
                        "Victorian house that stood where the current President's House now sits.");
        insertPeople(db, "Daniel Fisher",
                "Fisher", "Daniel", "NONE",
                12345, 12345, "M",
                "Rev. Daniel Fisher served as the tenth president of Hanover College from 1879-1907. " +
                        "Fisher oversaw the first period of increased stability at the College. During " +
                        "his presidency, Fisher oversaw the full admittance of women to Hanover College " +
                        "in 1881, saw the construction of five buildings including College Point House " +
                        "(the first residence hall) in 1882, the first collegiate Y.M.C.A. " +
                        "building in the nation in 1883 (building still stands today), the College's first " +
                        "gymnasium and Old Science Hall in 1896, and Hendricks Library (known today as " +
                        "Hendricks Hall) in 1903, named after the U.S. Vice President and Hanover College " +
                        "alum Thomas A. Hendricks. Fisher also increased student enrollment and faculty " +
                        "along with expanding the College's curriculum.");
        insertPeople(db, "William A. Millis",
                "Millis", "William", "Alfred",
                1868, 1942, "M",
                "William Alfred Millis served as the eleventh president of Hanover College from 1908-1929. " +
                        "Originally from Paoli, Indiana, Millis attended Indiana University, " +
                        "graduating in 1889 with his bachelor's degree and the next year with his " +
                        "master's. Millis witnessed and oversaw many drastic changes at Hanover " +
                        "College including the discontinuation of the Hanover Preparatory School (" +
                        "allowing the College to focus on undergraduate studies) in 1916, enrollment " +
                        "surpassing 500 students in 1923, faculty doubling in size to 25 members, " +
                        "the expansion of the College's curriculum in the sciences and arts, " +
                        "accreditation by the North Central and American Association of Colleges (NCAAC) " +
                        "in 1926, improved finances, and the installation of electric lights, plumbing, " +
                        "and heat in all campus buildings.");
        insertPeople(db, "Albert G. Parker",
                "Parker", "Albert", "G.",
                1900, 1958, "M",
                "Albert G. Parker served as the twelfth president of Hanover College from 1929-1958. " +
                        "At the start of his time at the College, the stock market had recently " +
                        "crashed and the Great Depression had begun. During his time as president, " +
                        "Parker had to work with tight finances, the effects of World War II " +
                        "(plummeting enrollment), and a fire that destroyed the main academic " +
                        "building on campus: Old Classic Hall. However, these hard times led to " +
                        "generous support from two charitable and influential individuals: William " +
                        "Henry Donner and James Graham Brown. With their donations and the charitable " +
                        "giving of many others, Parker was able to build 25 buildings during his " +
                        "presidency, creating a new campus plan with the academic buildings centered around " +
                        "the quad, the central grassy area. Some buildings erected during this time include " +
                        "Classic Hall, Goodrich Hall (now a part of Science Center), Lynn Hall, " +
                        "Parker Auditorium, a new campus library (now Science Hall), Donner and Ide " +
                        "Residence Halls, a new President's House, and Brown Chapel along with other " +
                        "dormitories, Greek housing, and administration buildings. Parker died in March " +
                        "before his planned retirement later in the fall of 1958.");
        insertPeople(db, "John E. Horner",
                "Horner", "John", "Edward",
                1921, 2014, "M",
                "John Edward Horner served as the thirteenth president of Hanover College from 1958-1987. " +
                        "Horner oversaw many changes and expansions at Hanover College " +
                        "including a new curriculum entitled the Hanover Plan, outlining two 14-week " +
                        "terms with a 5-week \"Spring Term\" where students took a study-intensive course " +
                        "(a modified system is still used today). As well, campus enrollment reached " +
                        "a new high of over 1,000 students, campus acreage increased to more than 500 " +
                        "acres, and new buildings were erected including the Administration Building " +
                        "(now Long Administration Building), Wiley Residence Hall, Katherine Parker " +
                        "Residence Hall, the James Graham Brown Campus Center (known as the CC), and " +
                        "L.S. Ayres Athletic Complex (the first complete athletic venue for the College), " +
                        "Duggan Library, and the Lynn Center for the Fine Arts (known as CFA). One tragic " +
                        "occurrence during Horner's presidency was the 1974 tornado that caused over $10 " +
                        "million in damage to nearly all campus buildings. The Hanover College campus was " +
                        "fully repaired less than one year later.");
        insertPeople(db, "Russell L. Nichols",
                "Nichols", "Rusty", "NONE",
                1900, 12345, "M",
                "Russell L. Nichols served as the fourteenth president of Hanover College from 1987-2007. " +
                        "Nichols oversaw many improvements and additions to the Hanover College experience " +
                        "including an increase in full-time faculty, expansion of off-campus study programs " +
                        "in foreign countries, the adoption of the Academic Vision Plan to better support " +
                        "the liberal arts, the creation of the Center for Business Preparation (known as " +
                        "the Career Center), and the installation of a fiber-optic network for Internet " +
                        "usage. As well, he oversaw the construction of Science Center, the Horner Health and " +
                        "Recreation Center, Ogle Center, Greenwood Suites, and the Shoebox. During Nichols' " +
                        "presidency, Hanover College was ranked in the U.S. News and World Report's annual " +
                        "\"America's Best College's\" competition.");
        insertPeople(db, "Sue DeWine",
                "DeWine", "Sue", "NONE",
                1900, 12345, "F",
                "Description");
        insertPeople(db, "Lake Lambert III",
                "Lambert", "Lake", "NONE",
                1900, 12345, "M",
                "Description");

        //OTHER PEOPLE
        insertPeople(db, "James Graham Brown",
                "Brown", "James", "Graham",
                1881, 1969, "M",
                "Description");
        insertPeople(db, "Mr. & Mrs. William Pool Brown",
                "Brown", "William", "Pool",
                1900, 2000, "M",
                "Brown Chapel -> James's father" +
                        "Mary Craig Graham Brown");
        insertPeople(db, "Coulter",
                "Coulter", "NONE", "NONE",
                1900, 2000, "M",
                "Description");
        insertPeople(db, "John Finley Crowe",
                "Crowe", "John", "Finley",
                1900, 2000, "M",
                "Description");
        insertPeople(db, "Glenn Culbertson",
                "Culbertson", "Glenn", "NONE",
                1863, 1927, "M",
                "Glenn taught astronomy, physics, and geology at Hanover College for 34 years from " +
                        "1893-1927. He was very involved on campus. He also served as a state " +
                        "legislator. Culbertson was a Hanover College alum, class of 1888.");
        insertPeople(db, "William H. Donner",
                "Donner", "William", "Henry",
                1864, 1953, "M",
                "William Henry Donner was born and raised in Columbus, Indiana. He attended Hanover " +
                        "College for only one semester in the fall of 1882 and then returned home to help " +
                        "his family. After participating in many business ventures, " +
                        "he amassed a small fortune. After retirement, Donner " +
                        "began giving to many organizations including Hanover College. Through a " +
                        "series of small financial challenges, Donner donated more than $2,000,000. " +
                        " The money was used to " +
                        "pay off debts, create a campus plan, and build new campus buildings including " +
                        "Donner Hall, Classic Hall, Parker Auditorium, Ide Hall, and the President's House.");
        insertPeople(db, "Agnus L. Brown Duggan",
                "Duggan", "A.", "L.",
                1900, 2000, "F",
                "Description");
        insertPeople(db, "Vice President Thomas A. Hendricks",
                "Hendricks", "Thomas", "A.",
                1819, 1885, "M",
                "Thomas A. Hendricks was an 1841 graduate of Hanover College. He served in the U.S. " +
                        "House of Representatives from 1851-1855 and in the U.S. Senate from 1863-69, " +
                        "both for Indiana. He served as Vice President of the United States under " +
                        "Grover Cleveland from 1885 until his death later that year. His wife " +
                        "donated money to Hanover College to erect a library in his honor, which " +
                        "still stands today.");
        insertPeople(db, "Mrs. John J. Ide",
                "Ide", "John", "J.",
                1900, 2000, "F",
                "Description");
        insertPeople(db, "Henry C. Long",
                "Long", "Henry", "C.",
                1843, 1901,  "M",
                "Henry C. Long was an Indianapolis lumber dealer. He supported and advocated for women's " +
                        "education in Indiana. Through donations from his estate, Long College for Women " +
                        "of Hanover College (1947-1978) was founded and many campus buildings were constructed, " +
                        "including Long Gymnasium (1957-93). The Administration Building was renamed in his " +
                        "honor in 1994.");
        insertPeople(db, "Charles J. Lynn",
                "Lynn", "Charles", "J.",
                1874, 1958, "M",
                "Charles J. Lynn was a Hanover College Board of Trustee from 1938-1958. He was an " +
                        "execute with the Eli Lilly pharmaceutical company. He and his wife " +
                        "Dorothy B. Lynn made many generous gifts to Hanover College including an " +
                        "organ for Old Classic Hall, a collection of rare books for the library, " +
                        "and a large endowment following both of their deaths.");
        insertPeople(db, "Dorothy B. Lynn",
                "Lynn", "Dorothy", "Black",
                1902, 1989, "F",
                "Dorothy Black Lynn was a Hanover College Board of Trustee from 1958-1983. She and " +
                        "her husband Charles J. Lynn made many generous gifts to Hanover College " +
                        "including an organ for Old Classic Hall, a collection of rare books for the " +
                        "library, and a large endowment following both of their deaths.");
        insertPeople(db, "Arthur C. Newby",
                "Newby", "Arthur", "C.",
                1900, 2000, "M",
                "Friend of college trustee John W. Suverkrup (trustee for 19 years) " +
                        "and donated funds to construct building");
        //UNKNOWN
        insertPeople(db, "Josephine Ogle",
                "Ogle", "Josephine", "NONE",
                1905, 1983, "F",
                "Josephine Ogle was born, raised, and lived her life in Vevay, Indiana, less than " +
                        "an hour drive away from Hanover College. She was the brother of Paul Ogle, a " +
                        "philanthropist and founder of The Paul W. Ogle Foundation.");
        insertPeople(db, "Katherine M. Parker",
                "Parker", "Katherine", "McAfee",
                1896, 1980, "F",
                "Katherine McAfee Parker was the wife of Albert G. Parker, the 12th president of Hanover " +
                        "College from 1929-1958. She was the daughter of Presbyterian ministers. Some " +
                        "interesting facts about her include she traveled to Europe in 1946 to study " +
                        "the effects of World War II on Protestantism, wrote a biography detailing " +
                        "her father's life, and wrote the words to the Hanover College Alma Mater.");
        insertPeople(db, "Harvey W. Wiley",
                "Wiley", "Harvey", "W.",
                1844, 1930, "M",
                "Harvey Washington Wiley was born in Kent, Indiana, less than an hour drive away from " +
                        "Hanover College. He attended Hanover College from 1963-1967. He attended the " +
                        "Indiana Medical College in 1871. He was the chief chemist for the U.S. " +
                        "Department of Agriculture during the turn of the century and led the movement " +
                        "which produced the Pure Food and Drug Act of 1906.");


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

        //
        //INSERT SITES_TO_SITE_TYPE
        //
        //ACADEMIC_BUILDING
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

        //STUDENT_HOUSING
        insertSitesToSiteType(db, "Donner Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Ide Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Katherine Parker Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Blythe Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Crowe Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Wiley Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Coulter Residence Hall", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Ogle Suites", "N", "Y", "N", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Greenwood Suites", "N", "Y", "N", "N", "N", "N", "N", "N");

        //GREEK_HOUSING
        insertSitesToSiteType(db, "Lambda Chi Alpha Fraternity House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Phi Delta Theta Fraternity House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Phi Gamma Delta Fraternity House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Sigma Chi Fraternity House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Alpha Delta Pi Sorority House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Chi Omega Sorority House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Kappa Alpha Theta Sorority House", "N", "N", "Y", "N", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Phi Mu Sorority House", "N", "N", "Y", "N", "N", "N", "N", "N");

        //ADMINISTRATION
        insertSitesToSiteType(db, "Long Administration Building", "N", "N", "N", "Y", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Admissions Building", "N", "N", "N", "Y", "N", "N", "N", "N");
        insertSitesToSiteType(db, "Academic Computing Center", "N", "N", "N", "Y", "N", "N", "N", "N");

        //CAMPUS_LIFE
        insertSitesToSiteType(db, "Brown Memorial Chapel", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "President's House", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Brown Campus Center", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Horner Health and Recreational Center", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Shoebox", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "YMCA", "N", "N", "N", "N", "Y", "N", "N", "N");
        insertSitesToSiteType(db, "Culbertson Observatory", "N", "N", "N", "N", "Y", "N", "N", "N");

        //OLD_CAMPUS
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
        insertSiteType(db, "ACADEMIC_BUILDING", "Academic Building", "Buildings that are mainly used " +
                "for purposes relating to the academic nature of the college. ");
        insertSiteType(db, "STUDENT_HOUSING", "Student Housing", "Buildings that house students during " +
                "the college's academic year.");
        insertSiteType(db, "GREEK_HOUSING", "Greek Housing", "Buildings that house students of a " +
                "particular Greek affiliation during the college's academic year.");
        insertSiteType(db, "ADMINISTRATION", "Administration", "Buildings that house " +
                "offices and meeting spaces used for managing the college's official business " +
                "and other administrative purposes.");
        insertSiteType(db, "CAMPUS_LIFE", "Campus Life", "Buildings that add to and supplement the " +
                "college experience both on a daily or semi-regularly basis.");
        insertSiteType(db, "OUTSIDE", "Outside", "Locations not located inside campus buildings that " +
                "hold historical significance to the college.");
        insertSiteType(db, "MEMORIAL", "Memorial", "Specific monuments to individuals, times, or " +
                "movements in the college's history.");
        insertSiteType(db, "OLD_CAMPUS", "Old Campus", "Buildings and locations at the college that " +
                "no longer exist or have been majorly renovated beyond their original intent that " +
                "held major significance to the college at one time.");


        //
        //CREATE TABLE SITES_TO_PEOPLE
        //
        db.execSQL("CREATE TABLE SITES_TO_PEOPLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "SITE_NAME TEXT, "
                + "NAMESAKE TEXT);");

        //INSERT SITES_TO_PEOPLE
        insertSitesToPeople(db, "Classic Hall", "NONE");
        insertSitesToPeople(db, "Hendricks Hall", "Vice President Thomas A. Hendricks");
        insertSitesToPeople(db, "Parker Auditorium", "Albert G. Parker");
        insertSitesToPeople(db, "Science Center", "NONE");
        insertSitesToPeople(db, "Science Hall", "NONE");
        insertSitesToPeople(db, "Newby Hall", "Arthur C. Newby");
        insertSitesToPeople(db, "Faculty Office Building", "NONE");
        insertSitesToPeople(db, "Lynn Hall", "Charles J. Lynn");
        insertSitesToPeople(db, "Duggan Library", "Agnus L. Brown Duggan");
        insertSitesToPeople(db, "Lynn Center for the Fine Arts", "Charles J. Lynn");

        insertSitesToPeople(db, "Donner Residence Hall", "William H. Donner");
        insertSitesToPeople(db, "Ide Residence Hall", "Mrs. John J. Ide");
        insertSitesToPeople(db, "Katherine Parker Residence Hall", "Katherine Parker");
        insertSitesToPeople(db, "Blythe Residence Hall", "James Blythe");
        insertSitesToPeople(db, "Crowe Residence Hall", "John Finley Crowe");
        insertSitesToPeople(db, "Wiley Residence Hall", "Harvey W. Wiley");
        insertSitesToPeople(db, "Coulter Residence Hall", "Coulter");
        insertSitesToPeople(db, "Ogle Suites", "Josephine Ogle");
        insertSitesToPeople(db, "Greenwood Suites", "NONE");

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

        insertSitesToPeople(db, "Brown Memorial Chapel", "William Pool Brown");
        insertSitesToPeople(db, "President's House", "NONE");
        insertSitesToPeople(db, "Brown Campus Center", "James Graham Brown");
        insertSitesToPeople(db, "Horner Health and Recreational Center", "John E. Horner");
        insertSitesToPeople(db, "Shoebox", "NONE");
        insertSitesToPeople(db, "YMCA", "NONE");
        insertSitesToPeople(db, "Culbertson Observatory", "Glenn Culbertson");

        insertSitesToPeople(db, "Old Classic Hall", "NONE");
        insertSitesToPeople(db, "Old Science Hall", "NONE");
        insertSitesToPeople(db, "College Point House", "NONE");
        insertSitesToPeople(db, "Long Gymnasium", "Henry C. Long");



        //
        //CREATE TABLE PEOPLE_TO_PEOPLE_TYPE
        db.execSQL("CREATE TABLE PEOPLE_TO_PEOPLE_TYPE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAMESAKE TEXT, "
                + "ALUMNUS TEXT, "
                + "FACULTY_STAFF TEXT, "
                + "PRESIDENT TEXT, "
                + "RELATIVE TEXT, "
                + "LOCAL TEXT,"
                + "OTHER TEXT);");

        //
        //INSERT PEOPLE_TO_PEOPLE_TYPE
        //
        //PRESIDENTS
        insertPeopleToPeopleType(db, "James Blythe",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Duncan McAuley",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Eramus D. MacMaster",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Sylvester Scovel",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Thomas E. Thomas",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Jonathan Edwards",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "James Wood",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "George D. Archibald",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "George C. Heckman",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Daniel Fisher",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "William A. Millis",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Albert G. Parker",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "John E. Horner",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Russell Nichols",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Sue DeWine",
                "N", "N", "Y", "N", "N", "N");
        insertPeopleToPeopleType(db, "Lake Lambert III",
                "N", "N", "Y", "N", "N", "N");

        //OTHER PEOPLE
        insertPeopleToPeopleType(db, "James Graham Brown",
                "Y", "N", "N", "N", "Y", "N");
        insertPeopleToPeopleType(db, "Mr. & Mrs. William Pool Brown",
                "N", "N", "N", "Y", "N", "N");
        insertPeopleToPeopleType(db, "Coulter",
                "N", "N", "N", "N", "N", "Y");
        insertPeopleToPeopleType(db, "John Finley Crowe",
                "N", "Y", "N", "N", "Y", "N");
        insertPeopleToPeopleType(db, "Glenn Culbertson",
                "N", "Y", "N", "N", "N", "N");
        insertPeopleToPeopleType(db, "William H. Donner",
                "Y", "N", "N", "N", "N", "N");
        insertPeopleToPeopleType(db, "Agnus L. Brown Duggan",
                "N", "N", "N", "Y", "N", "N");
        insertPeopleToPeopleType(db, "Vice President Thomas A. Hendricks",
                "Y", "N", "N", "N", "N", "N");
        insertPeopleToPeopleType(db, "Mrs. John J. Ide",
                "N", "N", "N", "Y", "N", "N");
        insertPeopleToPeopleType(db, "Henry C. Long",
                "N", "N", "N", "N", "N", "Y");
        insertPeopleToPeopleType(db, "Charles J. Lynn",
                "Y", "Y", "N", "N", "N", "N");
        insertPeopleToPeopleType(db, "Dorothy B. Lynn",
                "N", "Y", "N", "N", "N", "N");
        insertPeopleToPeopleType(db, "Arthur C. Newby",
                "N", "N", "N", "N", "N", "Y");
        insertPeopleToPeopleType(db, "Josephine Ogle",
                "N", "N", "N", "N", "N", "Y");
        insertPeopleToPeopleType(db, "Katherine Parker",
                "N", "N", "N", "Y", "N", "N");
        insertPeopleToPeopleType(db, "Harvey W. Wiley",
                "Y", "N", "N", "N", "N", "N");



        //
        //CREATE TABLE PEOPLE_TYPE
        //
        db.execSQL("CREATE TABLE PEOPLE_TYPE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "CATEGORY TEXT, "
                + "CATEGORY_PROPER TEXT, "
                + "DESCRIPTION TEXT);");

        //INSERT PEOPLE_TYPE
        insertPeopleType(db, "ALUMNUS", "Alumnus", "A person who graduated from or claims academic " +
                "connections with Hanover College.");
        insertPeopleType(db, "FACULTY_STAFF", "Faculty & Staff", "A person who has worked " +
                "at Hanover College.");
        insertPeopleType(db, "PRESIDENT", "President", "A person who has served as president of " +
                "Hanover College.");
        insertPeopleType(db, "RELATIVE", "Relative", "A person who is related to someone " +
                "connected with Hanover College");
        insertPeopleType(db, "LOCAL", "Local", "A person who lives in the local area or community " +
                "and claims a connection with Hanover College.");


        //
        //CREATE TABLE IMAGES_SITES
        //
        db.execSQL("CREATE TABLE IMAGES_SITES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "SITE_NAME TEXT, "
                + "IMAGE_ID INTEGER);");

        //INSERT IMAGES_SITES
        insertImageSites(db, "Long Administration Building", R.drawable.admin);
        insertImageSites(db, "Brown Memorial Chapel", R.drawable.brown_chapel);
        insertImageSites(db, "Brown Campus Center", R.drawable.cc);
        insertImageSites(db, "Lynn Center for the Fine Arts", R.drawable.cfa);
        insertImageSites(db, "Classic Hall", R.drawable.classic);
        insertImageSites(db, "Coulter Residence Hall", R.drawable.coulter);
        insertImageSites(db, "Donner Residence Hall", R.drawable.donner);
        insertImageSites(db, "Faculty Office Building", R.drawable.fob);
        insertImageSites(db, "Hendricks Hall", R.drawable.hendricks_hall);
        insertImageSites(db, "Horner Health and Recreation Center", R.drawable.horner);
        insertImageSites(db, "Ide Residence Hall", R.drawable.ide);
        insertImageSites(db, "Katherine Parker Residence Hall", R.drawable.kp);
        insertImageSites(db, "Duggan Library", R.drawable.library);
        insertImageSites(db, "Lynn Hall", R.drawable.lynn);
        insertImageSites(db, "Newby Hall", R.drawable.newby);
        insertImageSites(db, "Parker Auditorium", R.drawable.parker);
        insertImageSites(db, "Science Center", R.drawable.sc);
        insertImageSites(db, "Shoebox", R.drawable.shoebox);
        insertImageSites(db, "Wiley Residence Hall", R.drawable.wiley);


        //CREATE TABLE IMAGES_PEOPLE
        db.execSQL("CREATE TABLE IMAGES_PEOPLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAMESAKE TEXT, "
                + "IMAGE_ID INTEGER);");

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
                                     String gender, String description) {
        ContentValues peopleValues = new ContentValues();
        peopleValues.put("NAMESAKE", namesake);
        peopleValues.put("NAME_LAST", name_last);
        peopleValues.put("NAME_FIRST", name_first);
        peopleValues.put("NAME_MIDDLE", name_middle);
        peopleValues.put("DATE_BIRTH", date_birth);
        peopleValues.put("DATE_DEATH", date_death);
        peopleValues.put("GENDER", gender);
        peopleValues.put("DESCRIPTION", description);
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

    private static void insertPeopleToPeopleType(SQLiteDatabase db, String namesake,
                                                 String alumnus, String faculty_staff,
                                                 String president, String relative,
                                                 String local, String other) {
        ContentValues peopleValues = new ContentValues();
        peopleValues.put("NAMESAKE", namesake);
        peopleValues.put("ALUMNUS", alumnus);
        peopleValues.put("FACULTY_STAFF", faculty_staff);
        peopleValues.put("PRESIDENT", president);
        peopleValues.put("RELATIVE", relative);
        peopleValues.put("LOCAL", local);
        peopleValues.put("OTHER", other);
        db.insert("PEOPLE_TO_PEOPLE_TYPE", null, peopleValues);
    }

    private static void insertPeopleType(SQLiteDatabase db, String category,
                                               String category_proper, String description) {
        ContentValues peopleTypeValues = new ContentValues();
        peopleTypeValues.put("CATEGORY", category);
        peopleTypeValues.put("CATEGORY_PROPER", category_proper);
        peopleTypeValues.put("DESCRIPTION", description);
        db.insert("PEOPLE_TYPE", null, peopleTypeValues);
    }

    private static void insertImageSites(SQLiteDatabase db, String site_name,
                                         int image_id) {
        ContentValues imageSitesValues = new ContentValues();
        imageSitesValues.put("SITE_NAME", site_name);
        imageSitesValues.put("IMAGE_ID", image_id);
        db.insert("IMAGES_SITES", null, imageSitesValues);
    }

    private static void insertImagesPeople(SQLiteDatabase db, String person_name,
                                           int image_id) {
        ContentValues imagePeopleValues = new ContentValues();
        imagePeopleValues.put("SITE_NAME", person_name);
        imagePeopleValues.put("IMAGE_ID", image_id);
        db.insert("IMAGES_PEOPLE", null, imagePeopleValues);
    }
}
