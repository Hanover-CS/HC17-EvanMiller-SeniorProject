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

public class PeopleItemActivity extends Activity {

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_item);

        int item = (Integer)getIntent().getExtras().get(ITEM);

        String personNameGlobal = "";

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query ("PEOPLE",
                    new String[] {"_id", "NAMESAKE", "DATE_BIRTH", "DATE_DEATH", "DESCRIPTION"},
                    "_id = ?",
                    new String[] {Integer.toString(item)},
                    null, null, null);

            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get details
                String namesakeText = cursor.getString(1);
                String dateBornText = Integer.toString(cursor.getInt(2));
                String dateDiedText = Integer.toString(cursor.getInt(3));
                String descriptionText = cursor.getString(4);

                //Populate Site_Name
                TextView namesakePeople = (TextView) findViewById(R.id.namesake_people);
                namesakePeople.setText(namesakeText);

                //Populate Date_Built
                TextView dateBorn = (TextView) findViewById(R.id.date_born_people);
                dateBorn.setText(dateBornText);

                //Populate Date_Destroyed
                TextView dateDeath = (TextView) findViewById(R.id.date_death_people);
                dateDeath.setText(dateDiedText);

                //Populate Description
                TextView description = (TextView) findViewById(R.id.description_people);
                description.setText(descriptionText);
            }

            Cursor cursor_image = db.query ("IMAGES_PEOPLE",
                    new String[] {"_id", "SITE_NAME", "IMAGE_ID"},
                    "SITE_NAME = ?",
                    new String[] {personNameGlobal},
                    null, null, null);

            if (cursor_image.moveToFirst()) {
                //Get details
                int imageSiteTemp = cursor.getInt(2);

                //Populate Date_Destroyed
                ImageView imageSite = (ImageView) findViewById(R.id.image_site);
                imageSite.setImageResource(imageSiteTemp);
                imageSite.setContentDescription(personNameGlobal);
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
