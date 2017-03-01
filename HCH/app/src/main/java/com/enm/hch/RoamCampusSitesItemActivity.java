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

public class RoamCampusSitesItemActivity extends Activity {

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites_item);

        String item = (String)getIntent().getExtras().get(ITEM);

        //Save SITE_NAME to use for IMAGE_SITES cursor
        String siteNameGlobal = "";

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();

            //SITES table
            Cursor cursor = db.query ("SITES",
                    new String[] {"_id", "SITE_NAME", "DATE_BUILT", "DATE_DESTROYED", "DESCRIPTION", "NAMESAKE"},
                    "SITE_NAME = ?",
                    new String[] {item},
                    null, null, null);

            if (cursor.moveToFirst()) {
                //SITE_NAME
                String siteNameText = cursor.getString(1);
                //Save SITE_NAME to use for IMAGE_SITES cursor
                siteNameGlobal = siteNameText;
                //DATE_BUILT
                String dateBuiltText = Integer.toString(cursor.getInt(2));
                //DATE_DESTROYED
                String dateDestroyedText = Integer.toString(cursor.getInt(3));
                //DESCRIPTION
                String descriptionText = cursor.getString(4);
                //NAMESAKE
                String namesakeText = cursor.getString(5);

                //Temp Variable
                String temp;

                //Populate SITE_NAME
                TextView siteName = (TextView) findViewById(R.id.site_name);
                siteName.setText(siteNameText);

                //Populate DATE_BUILT
                TextView dateBuilt = (TextView) findViewById(R.id.date_built);
                //12345 = DATE_BUILT Unknown
                if (dateBuiltText.equals("12345")) {
                    dateBuilt.setText("Built: Unknown");
                }
                //DATE_BUILT Known
                else {
                    temp = "Built: " + dateBuiltText;
                    dateBuilt.setText(temp);
                }

                //Populate DATE_DESTROYED
                TextView dateDestroyed = (TextView) findViewById(R.id.date_destroyed);
                //12345 = No DATE_DESTROYED - Still Standing
                if (dateDestroyedText.equals("12345")) {
                    temp = "Currently Standing";
                    dateDestroyed.setText(temp);
                }
                //54321 = DATE_DESTROYED Unknown
                else if (dateDestroyedText.equals("54321")) {
                    dateDestroyed.setText("Destroyed: Unknown");
                }
                //DATE_DESTROYED Known
                else {
                    temp = "Destroyed: " + dateDestroyedText;
                    dateDestroyed.setText(temp);
                }

                //Populate DESCRIPTION
                TextView description = (TextView) findViewById(R.id.description);
                temp = "\n" + descriptionText;
                description.setText(temp);

                //Populate NAMESAKE
                TextView namesake = (TextView) findViewById(R.id.namesake);
                //NAMESAKE KNOWN
                if (!(namesakeText.equals("None"))) {
                    temp = "Namesake: " + namesakeText;
                    namesake.setText(temp);
                }
                //No NAMESAKE - Does not populate
            }

            cursor.close();

            //IMAGE_SITES table
            Cursor cursor_image = db.query ("IMAGES_SITES",
                    new String[] {"_id", "SITE_NAME", "IMAGE_ID"},
                    "SITE_NAME = ?",
                    new String[] {siteNameGlobal},
                    null, null,
                    "SITE_NAME ASC");

            if (cursor_image.moveToFirst()) {
                //IMAGE_ID
                int imageSiteID = cursor_image.getInt(2);

                //Populate IMAGE_ID
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
