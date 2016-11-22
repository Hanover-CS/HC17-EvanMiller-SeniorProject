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

public class PeopleItemActivity extends Activity {

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_item);

        int item = (Integer)getIntent().getExtras().get(ITEM);

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query ("PEOPLE",
                    new String[] {"_id", "NAMESAKE", "DATE_BIRTH", "DATE_DIED", "DESCRIPTION"},
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
                TextView dateDied = (TextView) findViewById(R.id.date_died_people);
                dateDied.setText(dateDiedText);

                //Populate Description
                TextView description = (TextView) findViewById(R.id.description_people);
                description.setText(descriptionText);
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
