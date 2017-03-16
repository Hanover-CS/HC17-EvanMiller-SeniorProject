package com.enm.hch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
    }

    //Alphabetical Listing selected
    public void alphabeticalPeopleClicked(View view) {
        Intent intent = new Intent (this, PeopleAlphabeticalListingActivity.class);
        startActivity(intent);
    }

    //Categories selected
    public void categoriesPeopleClicked(View view) {
        Intent intent = new Intent (this, PeopleCategoryListingActivity.class);
        startActivity(intent);
    }
}
