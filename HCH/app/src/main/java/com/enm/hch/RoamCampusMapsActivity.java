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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
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

        //Set listeners for marker and info window events
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnInfoWindowCloseListener(this);
        mMap.setOnInfoWindowLongClickListener(this);

        mMap.setMyLocationEnabled(true);

        /*
        LatLng northwest = new LatLng(38.724916, -85.487288);
        LatLng southeast = new LatLng(38.704825, -85.451196);
        LatLngBounds cameraBounds = new LatLngBounds(northwest, southeast);
        mMap.setLatLngBoundsForCameraTarget(cameraBounds);
        */

        //Display Zoom Buttons
        UiSettings mapSettings;
        mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);

        //Adds markers for each site on the map
        addSiteMarkers();


        //Handler to continuously find device's current location and realign camera
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Pull device's current location
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, true);
                Location myLocation = locationManager.getLastKnownLocation(provider);

                //Set camera
                CameraPosition position = CameraPosition.builder()
                        .target(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()))
                        .zoom(17)
                        .bearing(0.0f)
                        .tilt(0.0f)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);

                handler.postDelayed(this, 5000);
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
                    .snippet("Hanover College").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            //Sets Tag as SITE_NAME -> pass on to pull info from Database
            marker.setTag(siteNameText);

            while (cursor.moveToNext()){
                siteNameText = cursor.getString(1);
                latitudeText = cursor.getDouble(2);
                longitudeText = cursor.getDouble(3);
                latLng = new LatLng(latitudeText, longitudeText);

                //Creates Marker
                marker = mMap.addMarker(new MarkerOptions().position(latLng).title(siteNameText)
                        .snippet("Hanover College").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
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