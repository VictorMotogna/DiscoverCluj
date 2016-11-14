//package com.pccosmin.testy.views.CameraViews;
//
//import android.content.Context;
//import android.hardware.SensorManager;
//import android.location.LocationManager;
//import android.opengl.GLSurfaceView;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//
//import com.pccosmin.testy.R;
//import com.pccosmin.testy.model.Brother;
//import com.pccosmin.testy.views.GLViews.MyGLSurfaceView;
//import com.pccosmin.testy.views.GLViews.MyGLSurfaceViewTreasureHunt;
//import com.squareup.picasso.Callback;
//import com.squareup.picasso.Picasso;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by CristianCosmin on 12.11.2016.
// */
//
//public class CameraViewHolder extends RecyclerView.ViewHolder {
//    @BindView(R.id.surfaceviewclass)
//    MyGLSurfaceView myGLSurfaceView;
//
//    public CameraViewHolder(View itemView) {
//        super(itemView);
//        ButterKnife.bind(this,itemView);
//    }
//
//    public void populate(Context context, Brother brother){
//        itemView.setTag(brother);
//        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
//
//        ///
//        while(!hasPermissions(this, PERMISSIONS));
//
//
////        glView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
//        //  setContentView(glView);
//        mCamera=getCameraInstance();
//        mPreview=new CameraPreview(this,mCamera);
//        mPreview.setVisibility(View.VISIBLE);
//        FrameLayout preview=(FrameLayout)findViewById(R.id.camera_preview);
//        preview.addView(mPreview);
//        glView = (GLSurfaceView)findViewById(R.id.surfaceviewclass);
//        glView.setVisibility(View.INVISIBLE);
//        glViewTreasureHunt = (MyGLSurfaceViewTreasureHunt) findViewById(R.id.surfaceviewtreasureclass);
//        glViewTreasureHunt.setVisibility(View.INVISIBLE);
//
//
//        // ImageView iv=(ImageView)findViewById(R.id.icon);
//        ////
//
//
//        pointerIcon=new GifView(this,R.raw.chef);
//        pointerIcon.setVisibility(View.INVISIBLE);
//        pointerIcon.setTranslationX(600);
//        pointerIcon.setTranslationY(400);
//        calut=new GifView(this,R.raw.horse);
//        calut.setVisibility(View.INVISIBLE);
//        calut.setTranslationX(900);
//        calut.setTranslationY(400);
//        //   preview.addView(pointerIcon);
//        //   preview.addView(calut);
//    }
//}
