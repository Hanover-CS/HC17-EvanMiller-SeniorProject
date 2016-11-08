package com.enm.hch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BrowseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_list);
    }

    public void alphabeticalListClicked(View view) {
        Intent intent = new Intent (this, AlphabeticalListingActivity.class);
        startActivity(intent);
    }

    public void categoriesClicked(View view) {
        Intent intent = new Intent (this, AlphabeticalListingActivity.class);
        startActivity(intent);
    }
}
