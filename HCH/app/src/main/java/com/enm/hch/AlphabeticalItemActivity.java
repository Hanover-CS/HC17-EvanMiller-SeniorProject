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
import android.view.View;
import android.widget.CheckBox;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

public class AlphabeticalItemActivity extends Activity {

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabetical_item);

        //Get item from the intent
        int item = (Integer)getIntent().getExtras().get(ITEM);

        //Create cursor
        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query ("SITES",
                    new String[] {"SITE_NAME", "DATE_BUILT", "DATE_DESTROYED", "DESCRIPTION", "NAMESAKE"},
                    "_id = ?",
                    new String[] {Integer.toString(item)},
                    null, null, null);

            //Move to the first recrod in the Cursor
            if (cursor.moveToFirst()) {
                //Get details
                String siteNameText = cursor.getString(0);
                Integer dateBuiltText = cursor.getInt(1);
                Integer dateDestroyedText = cursor.getInt(2);
                String descriptionText = cursor.getString(3);
                String namesakeText = cursor.getString(5);

                //Populate Site_Name
                TextView siteName = (TextView) findViewById(R.id.site_name);
                siteName.setText(siteNameText);

                //Populate Date_Built
                TextView dateBuilt = (TextView) findViewById(R.id.date_built);
                dateBuilt.setText(dateBuiltText);

                //Populate Date_Destroyed
                TextView dateDestroyed = (TextView) findViewById(R.id.date_destroyed);
                dateDestroyed.setText(dateDestroyedText);

                //Populate Description
                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                //Populate Namesake
                TextView namesake = (TextView) findViewById(R.id.namesake);
                namesake.setText(namesakeText);
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Log.v("SQLiteException", "..........SQLITEEXCEPTION..........");
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
