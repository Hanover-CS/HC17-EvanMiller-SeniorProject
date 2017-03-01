package com.enm.hch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);
    }

    //Alphabetical Listing selected
    public void alphabeticalSitesClicked(View view) {
        Intent intent = new Intent (this, SitesAlphabeticalListingActivity.class);
        startActivity(intent);
    }

    //Categories selected
    public void categoriesSitesClicked(View view) {
        Intent intent = new Intent (this, SitesCategoryListingActivity.class);
        startActivity(intent);
    }
}
