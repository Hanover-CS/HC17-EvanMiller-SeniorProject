package com.enm.hch;

import android.app.Activity;
import android.os.Bundle;
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

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query ("SITES",
                    new String[] {"_id", "SITE_NAME", "DATE_BUILT", "DATE_DESTROYED", "DESCRIPTION", "NAMESAKE"},
                    "_id = ?",
                    new String[] {Integer.toString(item)},
                    null, null, null);

            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get details
                String siteNameText = cursor.getString(1);
                String dateBuiltText = Integer.toString(cursor.getInt(2));
                String dateDestroyedText = Integer.toString(cursor.getInt(3));
                String descriptionText = cursor.getString(4);
                String namesakeText = cursor.getString(5);

                //Populate Site_Name
                TextView siteName = (TextView) findViewById(R.id.site_name);
                siteName.setText(siteNameText);

                //Populate Date_Built
                TextView dateBuilt = (TextView) findViewById(R.id.date_built);
                dateBuilt.setText("Built: " + dateBuiltText);

                //Populate Date_Destroyed
                if(dateDestroyedText.equals("12345")) {
                    TextView dateDestroyed = (TextView) findViewById(R.id.date_destroyed);
                    dateDestroyed.setText("Currently Standing");
                }
                else {
                    TextView dateDestroyed = (TextView) findViewById(R.id.date_destroyed);
                    dateDestroyed.setText("Destroyed: " + dateDestroyedText);
                }

                //Populate Description
                TextView description = (TextView) findViewById(R.id.description);
                description.setText("\n" + descriptionText);

                //Populate Namesake
                if (namesakeText != "None") {
                    TextView namesake = (TextView) findViewById(R.id.namesake);
                    namesake.setText("Namesake: " + namesakeText);
                }

            }

            cursor.close();
            db.close();

        } catch(SQLiteException e) {
            Log.v("SQLiteException", "..........SQLITE_EXCEPTION..........");
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
