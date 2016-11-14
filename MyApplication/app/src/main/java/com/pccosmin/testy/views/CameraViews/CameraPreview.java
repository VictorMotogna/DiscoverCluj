package com.pccosmin.testy.views.CameraViews;


import android.content.Context;
import android.location.Location;
import android.hardware.Camera;
import android.location.LocationManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pccosmin.testy.R;
import com.pccosmin.testy.activities.CameraActivity;
import com.pccosmin.testy.model.PointOfInterest;
import com.pccosmin.testy.utils.Globals;
import com.pccosmin.testy.utils.MyCurrentAzimuth;
import com.pccosmin.testy.utils.MyCurrentLocation;
import com.pccosmin.testy.utils.OnAzimuthChangedListener;
import com.pccosmin.testy.utils.OnLocationChangedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by LenovoM on 11/11/2016.
 */


public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback, OnLocationChangedListener, OnAzimuthChangedListener {
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private Context ctxt;
    private boolean isCameraviewOn = false;
    private PointOfInterest mPoi;
    private PointOfInterest mPoiTreasureHunt;

    private double mAzimuthReal = 0;
    private double mAzimuthTeoretical = 0;
    private static double AZIMUTH_ACCURACY = 55;
    private double mMyLatitude = 0;
    private double mMyLongitude = 0;

    private MyCurrentAzimuth myCurrentAzimuth;
    private MyCurrentLocation myCurrentLocation;
    private TextView iv;
    TextView descriptionTextView;
    GifView pointerIcon;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.ctxt=context;
        mCamera = camera;
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        setAugmentedRealityPoint();
        //Listeners:
        CameraActivity ca=(CameraActivity)this.ctxt;
        myCurrentLocation = new MyCurrentLocation(this, ca.getLocationManager());
        myCurrentLocation.buildGoogleApiClient(this.ctxt);
        myCurrentLocation.start();


        myCurrentAzimuth = new MyCurrentAzimuth(this, this.ctxt);
        myCurrentAzimuth.start();

        CameraActivity cact=(CameraActivity)this.ctxt;
        //cact.getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, myCurrentLocation);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }


    private void updateDescription() {
//        descriptionTextView.setText(mPoi.getPoiName() + " azimuthTeoretical "
//                + mAzimuthTeoretical + " azimuthReal " + mAzimuthReal + " latitude "
//                + mMyLatitude + " longitude " + mMyLongitude);
    }

    @Override
    public void onLocationChanged(Location location) {
        mMyLatitude = location.getLatitude();
        mMyLongitude = location.getLongitude();
        mAzimuthTeoretical = calculateTeoreticalAzimuth();
        // Toast.makeText(this.ctxt,"latitude: "+location.getLatitude()+" longitude: "+location.getLongitude(), Toast.LENGTH_LONG).show();
        // updateDescription();
    }

    @Override
    public void onAzimuthChanged(float azimuthChangedFrom, float azimuthChangedTo) {
        if(Globals.gTreasureHuntMode==false) {

            mAzimuthReal = azimuthChangedTo;
            mAzimuthTeoretical = calculateTeoreticalAzimuth();

            FrameLayout preview=(FrameLayout)findViewById(R.id.camera_preview);




            double minAngle = calculateAzimuthAccuracy(mAzimuthTeoretical).get(0);
            double maxAngle = calculateAzimuthAccuracy(mAzimuthTeoretical).get(1);
            double dista = distance(mMyLatitude, mMyLongitude, mPoi.getPoiLatitude(), mPoi.getPoiLongitude(), "K");
            if (mMyLatitude != 0 || mMyLongitude != 0) {
                dista++;
            }
            if (isBetween(minAngle, maxAngle, mAzimuthReal)) {//&&(dista<5)&&(mMyLatitude!=0||mMyLongitude!=0)) {
                // pointerIcon.setVisibility(View.VISIBLE);
                //iv.setVisibility(View.VISIBLE);
                //    Toast.makeText(this.ctxt,"VIZIBIL",Toast.LENGTH_SHORT).show();
                CameraActivity ca = (CameraActivity) this.ctxt;
                try {
                    ca.setVisible();
                } catch (InterruptedException e) {

                }
            } else {
                // pointerIcon.setVisibility(View.INVISIBLE);
                // iv.setVisibility(View.INVISIBLE);

                CameraActivity ca = (CameraActivity) this.ctxt;
                ca.setInvisible();
            }
        }
        else{

            mAzimuthReal = azimuthChangedTo;
            mAzimuthTeoretical = calculateTeoreticalAzimuthTreasureHunt();

            FrameLayout preview=(FrameLayout)findViewById(R.id.camera_preview);




            double minAngle = calculateAzimuthAccuracy(mAzimuthTeoretical).get(0);
            double maxAngle = calculateAzimuthAccuracy(mAzimuthTeoretical).get(1);

            double dista = distance(mMyLatitude, mMyLongitude, mPoiTreasureHunt.getPoiLatitude(), mPoiTreasureHunt.getPoiLongitude(), "K");
            if (mMyLatitude != 0 || mMyLongitude != 0) {
                dista++;
            }
            if (isBetween(minAngle, maxAngle, mAzimuthReal)&&(dista<4)&&(mMyLatitude!=0||mMyLongitude!=0)) {
                // pointerIcon.setVisibility(View.VISIBLE);
                //iv.setVisibility(View.VISIBLE);
                //    Toast.makeText(this.ctxt,"VIZIBIL",Toast.LENGTH_SHORT).show();
                CameraActivity ca = (CameraActivity) this.ctxt;
                try {
                    ca.setVisible();
                } catch (InterruptedException e) {

                }
            } else {
                // pointerIcon.setVisibility(View.INVISIBLE);
                // iv.setVisibility(View.INVISIBLE);

                CameraActivity ca = (CameraActivity) this.ctxt;
                ca.setInvisible();
            }
        }
        updateDescription();
    }

    private void setAugmentedRealityPoint() {
        mPoi = new PointOfInterest(
                "Iulius Mall",
                "MALL",
                46.771681,
                23.625329
        );
        mPoiTreasureHunt=new PointOfInterest(
                "Treasure Hunt Hint",
                "#1",
                46.771748,
                23.625319
        );
    }
    //degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    //radians to degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    public double calculateTeoreticalAzimuth() {
        double dX = mPoi.getPoiLatitude() - mMyLatitude;
        double dY = mPoi.getPoiLongitude() - mMyLongitude;

        double phiAngle;
        double tanPhi;
        double azimuth = 0;

        tanPhi = Math.abs(dY / dX);
        phiAngle = Math.atan(tanPhi);
        phiAngle = Math.toDegrees(phiAngle);

        if (dX > 0 && dY > 0) { // I quater
            return azimuth = phiAngle;
        } else if (dX < 0 && dY > 0) { // II
            return azimuth = 180 - phiAngle;
        } else if (dX < 0 && dY < 0) { // III
            return azimuth = 180 + phiAngle;
        } else if (dX > 0 && dY < 0) { // IV
            return azimuth = 360 - phiAngle;
        }

        return phiAngle;
    }

    public double calculateTeoreticalAzimuthTreasureHunt() {
        double dX = mPoiTreasureHunt.getPoiLatitude() - mMyLatitude;
        double dY = mPoiTreasureHunt.getPoiLongitude() - mMyLongitude;

        double phiAngle;
        double tanPhi;
        double azimuth = 0;

        tanPhi = Math.abs(dY / dX);
        phiAngle = Math.atan(tanPhi);
        phiAngle = Math.toDegrees(phiAngle);

        if (dX > 0 && dY > 0) { // I quater
            return azimuth = phiAngle;
        } else if (dX < 0 && dY > 0) { // II
            return azimuth = 180 - phiAngle;
        } else if (dX < 0 && dY < 0) { // III
            return azimuth = 180 + phiAngle;
        } else if (dX > 0 && dY < 0) { // IV
            return azimuth = 360 - phiAngle;
        }

        return phiAngle;
    }
    private List<Double> calculateAzimuthAccuracy(double azimuth) {
        double minAngle = azimuth - AZIMUTH_ACCURACY;
        double maxAngle = azimuth + AZIMUTH_ACCURACY;
        List<Double> minMax = new ArrayList<Double>();

        if (minAngle < 0)
            minAngle += 360;

        if (maxAngle >= 360)
            maxAngle -= 360;

        minMax.clear();
        minMax.add(minAngle);
        minMax.add(maxAngle);

        return minMax;
    }

    private boolean isBetween(double minAngle, double maxAngle, double azimuth) {
        if (minAngle > maxAngle) {
            if (isBetween(0, maxAngle, azimuth) && isBetween(minAngle, 360, azimuth))
                return true;
        } else {
            if (azimuth > minAngle && azimuth < maxAngle)
                return true;
        }
        return false;
    }




}