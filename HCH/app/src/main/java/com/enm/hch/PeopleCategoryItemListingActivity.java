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

public class PeopleCategoryItemListingActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listSites = getListView();

        int item = (Integer) getIntent().getExtras().get(ITEM);

        try {
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            db = HCHDatabaseHelper.getReadableDatabase();

            cursor = db.query("PEOPLE_TYPE",
                    new String[]{"_id", "CATEGORY", "CATEGORY_PROPER"},
                    "_id = ?",
                    new String[]{Integer.toString(item)},
                    null, null,
                    "CATEGORY ASC");

            cursor.moveToFirst();
            String category_input = cursor.getString(1) + " = ?";

            cursor = db.query("PEOPLE_TO_PEOPLE_TYPE",
                    null,
                    category_input,
                    new String[]{"Y"},
                    null,
                    null,
                    "NAMESAKE ASC");

            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAMESAKE"},
                    new int[]{android.R.id.text1},
                    0);

            listSites.setAdapter(listAdapter);

        } catch (SQLiteException error) {
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
        Intent intent = new Intent(PeopleCategoryItemListingActivity.this, PeopleItemActivity.class);
        intent.putExtra(PeopleItemActivity.ITEM, (int) id);
        startActivity(intent);
    }
}