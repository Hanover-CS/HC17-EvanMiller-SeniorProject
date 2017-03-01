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

        //Save NAMESAKE to use for IMAGE_PEOPLE cursor
        String personNameGlobal = "";

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();

            //PEOPLE table
            Cursor cursor = db.query ("PEOPLE",
                    new String[] {"_id", "NAMESAKE", "DATE_BIRTH", "DATE_DEATH", "DESCRIPTION"},
                    "_id = ?",
                    new String[] {Integer.toString(item)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                //NAMESAKE
                String namesakeText = cursor.getString(1);
                //Save NAMESAKE to use for IMAGE_PEOPLE cursor
                personNameGlobal = namesakeText;
                //DATE_BIRTH
                String dateBornText = Integer.toString(cursor.getInt(2));
                //DATE_DEATH
                String dateDiedText = Integer.toString(cursor.getInt(3));
                //DESCRIPTION
                String descriptionText = cursor.getString(4);

                //Temp Variable
                String temp;

                //Populate NAMESAKE
                TextView namesakePeople = (TextView) findViewById(R.id.namesake_people);
                namesakePeople.setText(namesakeText);

                //Populate DATE_BIRTH
                TextView dateBorn = (TextView) findViewById(R.id.date_born);
                //12345 = BORN_BIRTH Unknown
                if (dateBornText.equals("12345")) {
                    dateBorn.setText("Born: Unknown");
                }
                //DATE_BIRTH Known
                else {
                    temp = "Born: " + dateBornText;
                    dateBorn.setText(temp);
                }

                //Populate DATE_DEATH
                TextView dateDeath = (TextView) findViewById(R.id.date_death);
                //12345 = DATE_DEATH Unknown
                if (dateDiedText.equals("12345")) {
                    dateDeath.setText("Died: Unknown");
                }
                //54321 = No DATE_DEATH - Still Living
                else if (dateDiedText.equals("54321")) {
                    dateDeath.setText("Living");
                }
                //DATE_DEATH Known
                else {
                    temp = "Died: " + dateDiedText;
                    dateDeath.setText(temp);
                }

                //Populate DESCRIPTION
                TextView description = (TextView) findViewById(R.id.description_people);
                description.setText(descriptionText);
            }

            cursor.close();

            //IMAGE_PEOPLE table
            Cursor cursor_image = db.query ("IMAGES_PEOPLE",
                    new String[] {"_id", "NAMESAKE", "IMAGE_ID"},
                    "NAMESAKE = ?",
                    new String[] {personNameGlobal},
                    null, null, null);

            if (cursor_image.moveToFirst()) {
                //IMAGE_ID
                int imageSiteTemp = cursor_image.getInt(2);

                //Populate IMAGE_ID
                ImageView imageSite = (ImageView) findViewById(R.id.image_people);
                imageSite.setImageResource(imageSiteTemp);
                imageSite.setContentDescription(personNameGlobal);
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
