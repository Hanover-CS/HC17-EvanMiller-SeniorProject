package com.enm.hch;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.LocationListener;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.enm.hch.R.id.map;

public class RoamCampusMapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnInfoWindowLongClickListener,
        GoogleMap.OnInfoWindowCloseListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roam_campus_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Create Map
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Display Zoom Buttons
        UiSettings mapSettings;
        mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);

        //Set listeners for marker and info window events
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnInfoWindowCloseListener(this);
        mMap.setOnInfoWindowLongClickListener(this);

        mMap.setMyLocationEnabled(true);

        addSiteMarkers();

        //Code needed to pull device's current location
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location myLocation = locationManager.getLastKnownLocation(provider);

        //Find latitude & longitude
        double latitude = myLocation.getLatitude();
        double longitude = myLocation.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        //Handler to continuously find device's current location and realign camera
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Code needed to pull device's current location
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, true);
                Location myLocation = locationManager.getLastKnownLocation(provider);

                //Find latitude & longitude
                double latitude = myLocation.getLatitude();
                double longitude = myLocation.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                handler.postDelayed(this, 1000);
            }
        });
    }

    private void addSiteMarkers() {
        //Pull LATITUDE and LONGITUDE from each SITE_NAME in SITES and populate markers for each
        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query ("SITES",
                    new String[] {"_id", "SITE_NAME", "LATITUDE", "LONGITUDE"},
                    null, null, null, null,
                    "SITE_NAME ASC");

            cursor.moveToFirst();
            String siteNameText = cursor.getString(1);
            double latitudeText = cursor.getDouble(2);
            double longitudeText = cursor.getDouble(3);
            LatLng latLng = new LatLng(latitudeText, longitudeText);
            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(siteNameText)
                    .snippet("Hanover College")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.hch_marker)));
            //Sets Tag as SITE_NAME -> pass on to pull info from Database
            marker.setTag(siteNameText);

            while (cursor.moveToNext()){
                siteNameText = cursor.getString(1);
                latitudeText = cursor.getDouble(2);
                longitudeText = cursor.getDouble(3);
                latLng = new LatLng(latitudeText, longitudeText);

                //Creates Marker
                marker = mMap.addMarker(new MarkerOptions().position(latLng).title(siteNameText)
                        .snippet("Hanover College")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.hch_marker)));
                //Sets Tag as SITE_NAME -> pass on to pull info from Database
                marker.setTag(siteNameText);
            }

            cursor.close();
            db.close();

        } catch(SQLiteException e) {
            Log.v("SQLiteException", "..........SQLITE_EXCEPTION..........");
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        //Returning 'false' falls back to default (displaying default window)
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String site_name = marker.getTag().toString();
        Intent intent = new Intent(RoamCampusMapsActivity.this, RoamCampusSitesItemActivity.class);
        intent.putExtra(RoamCampusSitesItemActivity.ITEM, site_name);
        startActivity(intent);

    }

    @Override
    public void onInfoWindowClose(Marker marker) {
        //Do nothing
    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
        //Do nothing
    }
}