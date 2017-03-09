package com.enm.hch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.enm.hch.R.id.map;

public class RoamCampusMapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnInfoWindowLongClickListener,
        GoogleMap.OnInfoWindowCloseListener {

    //System Permissions
    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int LOCATION_REQUEST = 1827;

    private double versionNum = 0.0;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roam_campus_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        String versionStr = Build.VERSION.RELEASE;
        versionStr = versionStr.substring(0, 1);
        versionNum = Double.parseDouble(versionStr);

        if (versionNum >= 6.0) {
            if (!canAccessLocation()) {
                requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Boolean to track if permissions are granted
        boolean location_permissions = true;

        //Create Map
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Set listeners for marker and info window events
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnInfoWindowCloseListener(this);
        mMap.setOnInfoWindowLongClickListener(this);

        //Display Zoom Buttons
        UiSettings mapSettings;
        mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);

        //Adds markers for each site on the map
        addSiteMarkers();

        //Checks for location permissions in Manifest file
        //Required for newer versions of Android
        //Prompts user to allow location permissions if haven't already
        if (versionNum >= 6.0) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        mMap.setMyLocationEnabled(true);

        //Current Location
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Longitude and Latitude for center of campus
        //Creates boundary if user not on campus
        LatLng northwest = new LatLng(38.709774, -85.470337);
        LatLng southeast = new LatLng(38.721092, -85.451368);
        LatLngBounds cameraBounds = new LatLngBounds(northwest, southeast);

        //Using map functionality NOT ON campus
        //Zooms camera onto center of campus
        if (!(cameraBounds.equals(myLocation))){
            //Set location bounds
            mMap.setLatLngBoundsForCameraTarget(cameraBounds);
            //Center coordinates of campus
            LatLng center = cameraBounds.getCenter();

            //Set camera on center coordinates of campus
            CameraPosition position = CameraPosition.builder()
                    .target(center)
                    .zoom(17)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        }
        //Using map functionality ON campus
        else {
            //Set camera on current location
            CameraPosition position = CameraPosition.builder()
                    .target(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()))
                    .zoom(17)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);

            /*
            //Handler to repeatedly find current location and realign camera
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Current location
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

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
            */
        }
    }

    //
    private void addSiteMarkers() {
        //Pull LATITUDE and LONGITUDE from each SITE_NAME in SITES and populate markers for each
        try{
            SQLiteOpenHelper HCHDatabaseHelper = new HCHDatabaseHelper(this);
            SQLiteDatabase db = HCHDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query ("SITES",
                    new String[] {"_id", "SITE_NAME", "LATITUDE", "LONGITUDE"},
                    null, null, null, null,
                    "SITE_NAME ASC");

            //First query item
            cursor.moveToFirst();
            String siteNameText = cursor.getString(1);
            double latitudeText = cursor.getDouble(2);
            double longitudeText = cursor.getDouble(3);
            LatLng latLng = new LatLng(latitudeText, longitudeText);

            //Creates Marker
            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(siteNameText)
                    .snippet("Hanover College").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            //Sets Tag as SITE_NAME -> pass on to pull info from Database
            marker.setTag(siteNameText);

            //Rest of query items
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

    //Helper function for Systems Permissions
    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    //Helper function for Systems Permissions
    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED == checkSelfPermission(perm));
    }

    //User clicks on marker - response
    @Override
    public boolean onMarkerClick(final Marker marker) {
        //Returning 'false' falls back to default (displaying default window)
        return false;
    }

    //User clicks on info window brought up by clicking on marker - response
    @Override
    public void onInfoWindowClick(Marker marker) {
        String site_name = marker.getTag().toString();
        Intent intent = new Intent(RoamCampusMapsActivity.this, RoamCampusSitesItemActivity.class);
        intent.putExtra(RoamCampusSitesItemActivity.ITEM, site_name);
        startActivity(intent);

    }

    //User clicks off of info window brought up by clicking on marker - response
    @Override
    public void onInfoWindowClose(Marker marker) {
        //Do nothing
    }

    //No long click option
    @Override
    public void onInfoWindowLongClick(Marker marker) {
        //Do nothing
    }
}