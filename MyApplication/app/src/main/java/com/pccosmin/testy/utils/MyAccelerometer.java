package com.pccosmin.testy.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;

import com.pccosmin.testy.activities.CameraActivity;

/**
 * Created by LenovoM on 11/12/2016.
 */

public class MyAccelerometer implements SensorEventListener {

    private float lastX;

    private Context mContext;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float deltaXMax = 0;
    private CameraActivity cameraAct;
    private float deltaX = 0;

    public MyAccelerometer(Context context,SensorManager sm) {

        cameraAct=(CameraActivity) context;
        mContext = context;
        sensorManager=sm;
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
        {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        CameraActivity gl=(CameraActivity) mContext;
        // get the change of the x values of the accelerometer
        deltaX = (lastX - event.values[0]);
        lastX=event.values[0];

        // if the change is below 2, it is just plain noise
//        if (deltaX < 2)
//            deltaX = 0;
        cameraAct.changeMove(deltaX,lastX);//deltaX,lastX);
    }
}

