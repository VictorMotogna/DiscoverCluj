package com.pccosmin.testy.fragments;

import android.hardware.Camera;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pccosmin.testy.R;
import com.pccosmin.testy.activities.CameraActivity;

import com.pccosmin.testy.views.CameraViews.CameraPreview;
import com.pccosmin.testy.views.GLViews.MyGLSurfaceViewTreasureHunt;


/**
 * Created by CristianCosmin on 12.11.2016.
 */

public class CameraFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private Camera mCamera;
    private SensorManager sensorManager;
    private LocationManager locManager;
    private CameraPreview mPreview;
    private GLSurfaceView glView;
    private MyGLSurfaceViewTreasureHunt glViewTreasureHunt;

    public static CameraFragment newInstance(){
        return new CameraFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_camera_fragment);
        CameraActivity.newIntent(getContext());
        return rootView;
    }
}
