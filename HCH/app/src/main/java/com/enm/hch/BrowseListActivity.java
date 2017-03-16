package com.enm.hch;

import android.app.ActionBar;
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

    //Sites selected
    public void sitesClicked(View view) {
        Intent intent = new Intent (this, SitesActivity.class);
        startActivity(intent);
    }

    //People selected
    public void peopleClicked(View view) {
        Intent intent = new Intent (this, PeopleActivity.class);
        startActivity(intent);
    }
}
