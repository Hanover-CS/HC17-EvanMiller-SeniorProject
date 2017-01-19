package com.enm.hch;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SitesCategoryItemListingActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listSites = getListView();

        int item = (Integer)getIntent().getExtras().get(ITEM);

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            db = HCHDatabaseHelper.getReadableDatabase();

            cursor = db.query("SITE_TYPE",
                    new String[]{"_id", "CATEGORY"},
                    "_id = ?",
                    new String[] {Integer.toString(item)},
                    null, null, null);

            cursor.moveToFirst();
            String category = cursor.getString(1);

            String category_input = category + " = ?";

            cursor = db.query("SITES_TO_SITE_TYPE",
                    null,
                    category_input,
                    new String[]{"Y"},
                    null,
                    null,
                    "SITE_NAME ASC");

            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"SITE_NAME"},
                    new int[]{android.R.id.text1},
                    0);

            listSites.setAdapter(listAdapter);

        } catch(SQLiteException error) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id) {
        Intent intent = new Intent(SitesCategoryItemListingActivity.this, SitesItemActivity.class);
        intent.putExtra(SitesItemActivity.ITEM, (int) id);
        startActivity(intent);
    }

/*
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

public class SitesCategoryItemListingActivity extends Activity {

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites_category_item_listing);

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
*/
}