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

            //SITE_TYPE table
            cursor = db.query("SITE_TYPE",
                    new String[]{"_id", "CATEGORY"},
                    "_id = ?",
                    new String[] {Integer.toString(item)},
                    null, null, null);

            cursor.moveToFirst();
            String category = cursor.getString(1);
            //For use in cursor
            String category_input = category + " = ?";

            //SITES_TO_SITE_TYPE table
            //Searches for "Y" (YES) in specific category's column
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

            //Displays all Sites of the specific Site category chosen
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
}