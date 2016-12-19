package com.enm.hch;

import android.content.ComponentName;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RoamCampusMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location locationLL;
    private boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roam_campus_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addSiteMarkers();
        mMap.addMarker(new MarkerOptions().position(new LatLng(38.71433, -85.48113)).title("Marker").snippet("Snippet"));

        /*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(38.71433, -85.48113);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        */

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                /*
                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                handler.postDelayed(this, 10000);
                */

                // Enable MyLocation Layer of Google Map
                mMap.setMyLocationEnabled(true);
                // Get LocationManager object from System Service LOCATION_SERVICE
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // Create a criteria object to retrieve provider
                Criteria criteria = new Criteria();
                // Get the name of the best provider
                String provider = locationManager.getBestProvider(criteria, true);
                // Get Current Location
                Location myLocation = locationManager.getLastKnownLocation(provider);
                // set map type
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                // Get latitude of the current location
                //double latitude = myLocation.getLatitude();
                double latitude = 38.71433;
                // Get longitude of the current location
                //double longitude = myLocation.getLongitude();
                double longitude = -85.48113;
                // Create a LatLng object for the current location
                LatLng latLng = new LatLng(latitude, longitude);
                // Show the current location in Google Map
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                // Zoom in the Google Map
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                mMap.addMarker(new MarkerOptions().position(latLng).title("Hanover College").snippet("Panther Pride"));
            }
        });
    }

    private void addSiteMarkers() {
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
            mMap.addMarker(new MarkerOptions().position(latLng).title(siteNameText).snippet(siteNameText));

            Boolean loop = true;
            while (loop == true){
                if(cursor.moveToNext() == false){
                    loop = false;
                }
                else {
                    //cursor.moveToNext())
                    siteNameText = cursor.getString(1);
                    latitudeText = cursor.getDouble(2);
                    longitudeText = cursor.getDouble(3);
                    latLng = new LatLng(latitudeText, longitudeText);
                    mMap.addMarker(new MarkerOptions().position(latLng).title(siteNameText).snippet(siteNameText));
                }
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
