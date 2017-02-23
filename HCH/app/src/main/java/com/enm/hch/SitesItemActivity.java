package com.enm.hch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SitesItemActivity extends Activity {

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites_item);

        int item = (Integer)getIntent().getExtras().get(ITEM);

        String siteNameGlobal = "";

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();


            Cursor cursor = db.query ("SITES",
                    new String[] {"_id", "SITE_NAME", "DATE_BUILT", "DATE_DESTROYED", "DESCRIPTION", "NAMESAKE"},
                    "_id = ?",
                    new String[] {Integer.toString(item)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                //Get details
                String siteNameText = cursor.getString(1);
                siteNameGlobal = siteNameText;
                String dateBuiltText = Integer.toString(cursor.getInt(2));
                String dateDestroyedText = Integer.toString(cursor.getInt(3));
                String descriptionText = cursor.getString(4);
                String namesakeText = cursor.getString(5);
                String temp;

                //Populate Site_Name
                TextView siteName = (TextView) findViewById(R.id.site_name);
                siteName.setText(siteNameText);

                //Populate Date_Built
                TextView dateBuilt = (TextView) findViewById(R.id.date_built);
                //12345 = DATE BUILT UNKNOWN
                if (dateBuiltText.equals("12345")) {
                    dateBuilt.setText("Build: Unknown");
                }
                //DATE BUILT KNOWN
                else {
                    temp = "Built: " + dateBuiltText;
                    dateBuilt.setText(temp);
                }

                //Populate Date_Destroyed
                TextView dateDestroyed = (TextView) findViewById(R.id.date_destroyed);
                //12345 = NOT DATE DESTROYED - STILL STANDING
                if (dateDestroyedText.equals("12345")) {
                    temp = "Currently Standing";
                    dateDestroyed.setText(temp);
                }
                //54321 = DATE DESTROYED UNKNOWN
                else if (dateDestroyedText.equals("54321")) {
                    dateDestroyed.setText("Destroyed: Unknown");
                }
                //DATE DESTROYED KNOWN
                else {
                    temp = "Destroyed: " + dateDestroyedText;
                    dateDestroyed.setText(temp);
                }

                //Populate Description
                TextView description = (TextView) findViewById(R.id.description);
                temp = "\n" + descriptionText;
                description.setText(temp);

                //Populate Namesake
                TextView namesake = (TextView) findViewById(R.id.namesake);
                //NAMESAKE KNOWN
                if (!(namesakeText.equals("None"))) {
                    temp = "Namesake: " + namesakeText;
                    namesake.setText(temp);
                }
                //NAMESAKE NOT KNOWN
                //DOES NOT FILL IN AREA
            }

            cursor.close();

            Cursor cursor_image = db.query ("IMAGES_SITES",
                    new String[] {"_id", "SITE_NAME", "IMAGE_ID"},
                    "SITE_NAME = ?",
                    new String[] {siteNameGlobal},
                    null, null,
                    "SITE_NAME ASC");

            if (cursor_image.moveToFirst()) {
                //Get details
                int imageSiteID = cursor_image.getInt(2);

                //Populate Date_Destroyed
                ImageView imageSite = (ImageView) findViewById(R.id.image_site);
                imageSite.setImageResource(imageSiteID);
                imageSite.setContentDescription(siteNameGlobal);
            }

            cursor_image.close();
            db.close();

        } catch(SQLiteException e) {
            Log.v("SQLiteException", "..........SQLITE_EXCEPTION..........");
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
