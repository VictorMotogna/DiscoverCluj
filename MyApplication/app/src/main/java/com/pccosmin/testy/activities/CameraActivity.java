package com.pccosmin.testy.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.pccosmin.testy.R;
import com.pccosmin.testy.utils.Globals;
import com.pccosmin.testy.views.CameraViews.CameraPreview;
import com.pccosmin.testy.views.CameraViews.GifView;
import com.pccosmin.testy.views.GLViews.MyGLSurfaceView;
import com.pccosmin.testy.views.GLViews.MyGLSurfaceViewTreasureHunt;

public class CameraActivity extends BaseActivity {
    private Camera mCamera;
    private SensorManager sensorManager;
    private LocationManager locManager;
    private ImageView textIulius;
    private CameraPreview mPreview;
    private GLSurfaceView glView;
    private MyGLSurfaceViewTreasureHunt glViewTreasureHunt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //get permission for camera
        String[] PERMISSIONS = {Manifest.permission.CAMERA,Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(!checkCameraHardware(getApplicationContext())){

            finish();
        }

        ///
        while(!hasPermissions(this, PERMISSIONS));


        setContentView(R.layout.activity_camera);


//        glView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        //  setContentView(glView);
        mCamera=getCameraInstance();
        mPreview=new CameraPreview(this,mCamera);
        mPreview.setVisibility(View.VISIBLE);
        textIulius=(ImageView)findViewById(R.id.imaginetext) ;
        FrameLayout preview=(FrameLayout)findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        glView = (GLSurfaceView)findViewById(R.id.surfaceviewclass);
        glView.setVisibility(View.INVISIBLE);
        glViewTreasureHunt = (MyGLSurfaceViewTreasureHunt) findViewById(R.id.surfaceviewtreasureclass);
        glViewTreasureHunt.setVisibility(View.INVISIBLE);


        // ImageView iv=(ImageView)findViewById(R.id.icon);
        ////

    }


    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
    public SensorManager getSensorManager(){
        return sensorManager;
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {

            c = Camera.open(); // attempt to get a CameraActivity instance

        }
        catch (Exception e){
            // CameraActivity is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setVisible() throws InterruptedException{
        //  ImageView iv=(ImageView)findViewById(R.id.iconn);

        // pointerIcon.setVisibility(View.VISIBLE);

        //   iv.setVisibility(View.VISIBLE);
        //   calut.setVisibility(View.VISIBLE);
        if(Globals.gTreasureHuntMode==false) {
            glView.setVisibility(View.VISIBLE);
            textIulius.setVisibility(View.VISIBLE);
            glViewTreasureHunt.setVisibility(View.INVISIBLE);
//            Intent intent = BrotherPagerActivity.newIntent(this, new Brother(
//                    33,
//                    "Brother " + 33,
//                    "Brother " + 33 + " for this reason",
//                    "http://www.gravatar.com/avatar/" + 33 + "?d=identicon",
//                    "Mumu Engineering",
//                    "Autumn 2016",
//                    "Cool fact"));
//            startActivity(intent);
        }
        else{
            glView.setVisibility(View.INVISIBLE);
            //    textIulius.setVisibility(View.INVISIBLE);
            glViewTreasureHunt.setVisibility(View.VISIBLE);
//            finish();
        }
        // glViewTreasureHunt.setVisibility(View.VISIBLE);

    }


    public void setInvisible(){
        //  ImageView iv=(ImageView)findViewById(R.id.iconn)
        //  pointerIcon.setVisibility(View.INVISIBLE);

        //  calut.setVisibility(View.INVISIBLE);
        //   iv.setVisibility(View.INVISIBLE);

        glView.setVisibility(View.INVISIBLE);
        glViewTreasureHunt.setVisibility(View.INVISIBLE);
        textIulius.setVisibility(View.INVISIBLE);
        // glViewTreasureHunt.setVisibility(View.INVISIBLE);
    }

    public LocationManager getLocationManager(){
        return locManager;
    }


    public void changeMove(float deltaX,float x){
        MyGLSurfaceView mgl=(MyGLSurfaceView)glView;
        mgl.changeMove(deltaX,x);
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, CameraActivity.class);
        return intent;
    }
}