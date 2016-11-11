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

public class CategoryItemActivity extends Activity {

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);

        int item = (Integer)getIntent().getExtras().get(ITEM);

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query ("SITE_TYPE",
                    new String[] {"_id", "CATEGORY", "CATEGORY_PROPER"},
                    "_id = ?",
                    new String[] {Integer.toString(item)},
                    null, null, null);

            cursor.moveToFirst();
            String category = cursor.getString(1);
            String category_proper = Integer.toString(cursor.getInt(2));

            TextView siteName = (TextView) findViewById(R.id.category_view);
            siteName.setText(category);

            TextView dateBuilt = (TextView) findViewById(R.id.category_proper_view);
            dateBuilt.setText(category_proper);

            cursor.close();
            db.close();

        } catch(SQLiteException e) {
            Log.v("SQLiteException", "..........SQLITE_EXCEPTION..........");
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}