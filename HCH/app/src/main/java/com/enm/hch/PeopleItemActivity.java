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

                //Populate Date_Born
                TextView dateBorn = (TextView) findViewById(R.id.date_born_people);
                //12345 = BORN DATE UNKNOWN
                if (dateBornText.equals("12345")) {
                    dateBorn.setText("Born: Unknown");
                }
                //BORN DATE KNOWN
                else {
                    String temp = "Born: " + dateBornText;
                    dateBorn.setText(temp);
                }

                //Populate Date_Death
                TextView dateDeath = (TextView) findViewById(R.id.date_death_people);
                //12345 = DEATH DATE UNKNOWN
                if (dateDiedText.equals("12345")) {
                    dateDeath.setText("Died: Unknown");
                }
                //54321 = NO DEATH DATE - STILL LIVING
                else if (dateDiedText.equals("54321")) {
                    dateDeath.setText("Living");
                }
                //DEATH DATE KNOWN
                else {
                    String temp = "Died: " + dateDiedText;
                    dateBorn.setText(temp);
                }

                //Populate Description
                TextView description = (TextView) findViewById(R.id.description_people);
                description.setText(descriptionText);
            }

            Cursor cursor_image = db.query ("IMAGES_PEOPLE",
                    new String[] {"_id", "NAMESAKE", "IMAGE_ID"},
                    "NAMESAKE = ?",
                    new String[] {personNameGlobal},
                    null, null, null);

            if (cursor_image.moveToFirst()) {
                //Get details
                int imageSiteTemp = cursor.getInt(2);

                //Populate Date_Destroyed
                ImageView imageSite = (ImageView) findViewById(R.id.image_people);
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
