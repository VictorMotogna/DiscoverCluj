package com.pccosmin.testy.utils;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by LenovoM on 11/11/2016.
 */

public class MyCurrentLocation implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationManager locManager;
    private LocationRequest mLocationRequest;
    private OnLocationChangedListener onLocationChangedListener;
    private static final int LOCATION_MIN_TIME = 30 * 1000;
    private static final int LOCATION_MIN_DISTANCE = 10;
    private String provider;

    public MyCurrentLocation(OnLocationChangedListener onLocationChangedListener,LocationManager loc) {
        this.onLocationChangedListener = onLocationChangedListener;
//        locManager=loc;
//        Criteria crta = new Criteria();
//        crta.setAccuracy(Criteria.ACCURACY_FINE);
//        crta.setAltitudeRequired(false);
//        crta.setBearingRequired(false);
//        crta.setCostAllowed(true);
//        crta.setPowerRequirement(Criteria.POWER_LOW);
//        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, this);
//        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, this);
//        provider = locManager.getBestProvider(crta, true);
//        mLastLocation = locManager.getLastKnownLocation(provider);

    }

    public synchronized void buildGoogleApiClient(Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
    }

    public void start(){
        mGoogleApiClient.connect();
    }

    public void stop(){
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            onLocationChangedListener.onLocationChanged(mLastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("MyApp", "Location services connection failed with code " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            onLocationChangedListener.onLocationChanged(mLastLocation);
        }
    }
}
