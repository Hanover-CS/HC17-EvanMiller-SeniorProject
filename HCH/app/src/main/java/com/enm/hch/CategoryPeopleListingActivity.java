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

public class CategoryPeopleListingActivity extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listSites = getListView();

        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            db = HCHDatabaseHelper.getReadableDatabase();

            cursor = db.query("CONNECTION_PEOPLE",
                    new String[]{"_id", "CATEGORY", "CATEGORY_PROPER"},
                    null, null, null, null,
                    "CATEGORY ASC");

            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"CATEGORY_PROPER"},
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
        Intent intent = new Intent(CategoryPeopleListingActivity.this, CategoryPeopleItemActivity.class);
        intent.putExtra(CategoryPeopleItemActivity.ITEM, (int) id);
        startActivity(intent);
    }
}

