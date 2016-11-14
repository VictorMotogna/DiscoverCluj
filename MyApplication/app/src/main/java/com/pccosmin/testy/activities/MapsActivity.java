package com.pccosmin.testy.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pccosmin.testy.R;
import com.pccosmin.testy.model.RushEvent;
import com.pccosmin.testy.parsers.DirectionJSONParser;
import com.pccosmin.testy.utils.PermissionUtils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends BaseActivity implements GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private LocationRequest mLocationRequest;
    private LatLng latLng;
    private Marker currentLocationMarker;
    private SupportMapFragment mapFragment;
    private GoogleApiClient mClient;
    private GoogleMap map;
    private RushEvent rushEvent;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;
    ArrayList<LatLng> markerPoints;
    private boolean isTransit;
    private boolean isDrive;
    private boolean isWalk;

    public static final String RUSH_EVENT_INFO = "RUSH_EVENT_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_map);

        rushEvent = getIntent().getParcelableExtra(RUSH_EVENT_INFO);

        markerPoints = new ArrayList<>();
        Button btnDrawDrive = (Button) findViewById(R.id.activity_draw_map_btnDraw_driving);
        Button btnDrawWalk = (Button) findViewById(R.id.activity_draw_map_btnDraw_walking);
        Button btnDrawBus = (Button) findViewById(R.id.activity_draw_map_btnDraw_bus);

        btnDrawDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "drive", Toast.LENGTH_SHORT).show();
                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);
                    if(isWalk || isTransit) {
                        map.clear();
                        MarkerOptions options1 = new MarkerOptions();
                        options1.position(dest);
                        options1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        map.addMarker(options1);
                        for(int i = 2;i<markerPoints.size();i++){
                            MarkerOptions options2 = new MarkerOptions();
                            options2.position(markerPoints.get(i));
                            options2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                            map.addMarker(options2);
                        }
                    }
                    isTransit = false;
                    isWalk=false;
                    isDrive = true;

                    String url = getDirectionsUrl(origin, dest, "mode=driving");

                    DownloadTask downloadTask = new DownloadTask();

                    downloadTask.execute(url);
                }
            }
        });

        btnDrawWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "walk", Toast.LENGTH_SHORT).show();
                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);
                    if(isDrive || isTransit) {
                        map.clear();
                        MarkerOptions options1 = new MarkerOptions();
                        options1.position(dest);
                        options1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        map.addMarker(options1);
                        for(int i = 2;i<markerPoints.size();i++){
                            MarkerOptions options2 = new MarkerOptions();
                            options2.position(markerPoints.get(i));
                            options2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                            map.addMarker(options2);
                        }
                    }
                    isWalk = true;
                    isDrive = false;
                    isTransit = false;

                    String url = getDirectionsUrl(origin, dest, "mode=walking");

                    DownloadTask downloadTask = new DownloadTask();

                    downloadTask.execute(url);
                }
            }
        });

        btnDrawBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "bus", Toast.LENGTH_SHORT).show();

                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);
                    if(isDrive || isWalk) {
                        map.clear();
                        markerPoints.clear();
                        markerPoints.add(origin);
                        markerPoints.add(dest);
                        MarkerOptions options1 = new MarkerOptions();
                        options1.position(dest);
                        options1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        map.addMarker(options1);
                    }
                    isTransit = true;
                    isDrive = false;
                    isWalk = false;

                    String url = getDirectionsUrl(origin, dest, "mode=transit");

                    DownloadTask downloadTask = new DownloadTask();

                    downloadTask.execute(url);
                }
            }
        });

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_draw_map_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
        buildGoogleApiClient();
        mClient.connect();
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                if (markerPoints.size() >= 9) {
                    return;
                }else if(markerPoints.size() > 1 && isTransit == true){
                    Toast.makeText(getApplicationContext(), "No more than the destination", Toast.LENGTH_SHORT).show();
                    return;
                }

                markerPoints.add(point);

                MarkerOptions options = new MarkerOptions();

                options.position(point);

                if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                } else if(markerPoints.size() > 2){
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                }
                map.addMarker(options);
            }
        });
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {
                map.clear();
                markerPoints.clear();
                markerPoints.add(currentLocationMarker.getPosition());
                latLng = new LatLng(currentLocationMarker.getPosition().latitude, currentLocationMarker.getPosition().longitude);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        });
    }

    protected synchronized void buildGoogleApiClient(){
        Toast.makeText(this, "buildGoogleApiClient", Toast.LENGTH_SHORT).show();
        mClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (map != null) {
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest, String travelMode){
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";

        String waypoints = "";
        if(travelMode != "mode=transit") {
            for (int i = 2; i < markerPoints.size(); i++) {
                LatLng point = (LatLng) markerPoints.get(i);
                if (i == 2) {
                    waypoints = "waypoints=";
                }
                waypoints += point.latitude + "," + point.longitude + "|";
            }
        }
        String parameters = str_origin + "&"+str_dest+"&"+sensor+"&"+travelMode+"&"+waypoints;

        String output="json";

        String url="https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
        return url;
    }

    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while( (line = br.readLine()) !=null){
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        }catch (Exception e){
            Log.i("",e.toString());
        }finally {
            inputStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mClient);
        if (mLastLocation != null) {
            map.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            currentLocationMarker = map.addMarker(markerOptions);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            markerPoints.add(latLng);
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setSmallestDisplacement(0);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(currentLocationMarker != null){
            currentLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        markerPoints.set(0, latLng);

        Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT).show();

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(15).build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition((cameraPosition)));


    }

    private class DownloadTask extends AsyncTask<String, Void, String>{
        //Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try{
                //Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        //Executes in UI thread, after the execution of
        //doInBackground()
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();

            //Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    //A class to parse the Google Places in JSON format
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>>{

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
            JSONObject jsonObject;
            List<List<HashMap<String,String>>> routes = null;

            try{
                jsonObject = new JSONObject(jsonData[0]);
                DirectionJSONParser parser = new DirectionJSONParser();

                //Starts parsing data
                routes = parser.parse(jsonObject);
            }catch (Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result){
            ArrayList<LatLng> points = null;
            PolylineOptions polylineOptions = null;

            //Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<>();
                polylineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get(i);

                //Fetching i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat= Double.parseDouble(point.get("lat"));
                    double lng= Double.parseDouble(point.get("lng"));

                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }

                //Adding all the points in the route to LineOptions
                polylineOptions.addAll(points);
                polylineOptions.width(10);
                polylineOptions.color(Color.RED);
            }

            //Drawing polyline in the Google Map for the i-th route
            map.addPolyline(polylineOptions);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClient.disconnect();
    }

    public static Intent newIntent(Context context, RushEvent rushEvent){
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(RUSH_EVENT_INFO, rushEvent);
        return intent;
    }

}