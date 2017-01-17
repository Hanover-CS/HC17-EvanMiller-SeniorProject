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

    public void sitesClicked(View view) {
        Intent intent = new Intent (this, SitesActivity.class);
        startActivity(intent);
    }

    public void peopleClicked(View view) {
        Intent intent = new Intent (this, PeopleActivity.class);
        startActivity(intent);
    }
}
