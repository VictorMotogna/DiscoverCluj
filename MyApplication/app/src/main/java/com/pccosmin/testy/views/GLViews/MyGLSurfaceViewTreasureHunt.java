package com.pccosmin.testy.views.GLViews;

/**
 * Created by LenovoM on 11/12/2016.
 */

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.pccosmin.testy.activities.CameraActivity;
import com.pccosmin.testy.utils.MyAccelerometer;
import com.pccosmin.testy.utils.MyGLRendererTreasureHunt;

/*
 * Custom GL view by extending GLSurfaceView so as
 * to override event handlers such as onKeyUp(), onTouchEvent()
 */
public class MyGLSurfaceViewTreasureHunt extends GLSurfaceView {
    MyGLRendererTreasureHunt renderer;    // Custom GL Renderer
    private MyAccelerometer acc;

    // For touch event
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320.0f;
    private float previousX;
    private float previousY;

    // Constructor - Allocate and set the renderer
    public MyGLSurfaceViewTreasureHunt(Context context) {
        super(context);
        CameraActivity ac=(CameraActivity)context;
        renderer = new MyGLRendererTreasureHunt(context);
        this.setZOrderOnTop(true);
        this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.getHolder().setFormat(PixelFormat.RGBA_8888);
        this.setRenderer(renderer);
        // Request focus, otherwise key/button won't react
        this.requestFocus();
        this.setFocusableInTouchMode(true);
        acc=new MyAccelerometer(context,ac.getSensorManager());
    }

    public MyGLSurfaceViewTreasureHunt(Context context, AttributeSet attrs) {
        super(context,attrs);
        CameraActivity ac=(CameraActivity)context;
        renderer = new MyGLRendererTreasureHunt(context);

        this.setZOrderOnTop(true);
        this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.getHolder().setFormat(PixelFormat.RGBA_8888);
        this.setRenderer(renderer);
        // Request focus, otherwise key/button won't react
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }

    // Handler for key event
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent evt) {
        switch(keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:   // Decrease Y-rotational speed
                renderer.speedY -= 0.1f;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:  // Increase Y-rotational speed
                renderer.speedY += 0.1f;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:     // Decrease X-rotational speed
                renderer.speedX -= 0.1f;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:   // Increase X-rotational speed
                renderer.speedX += 0.1f;
                break;
            case KeyEvent.KEYCODE_A:           // Zoom out (decrease z)
                renderer.z -= 0.2f;
                break;
            case KeyEvent.KEYCODE_Z:           // Zoom in (increase z)
                renderer.z += 0.2f;
                break;
        }
        return true;  // Event handled
    }

    // Handler for touch event
    @Override
    public boolean onTouchEvent(final MotionEvent evt) {
        float currentX = evt.getX();
        float currentY = evt.getY();
        float deltaX, deltaY;
        switch (evt.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // Modify rotational angles according to movement
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
                renderer.angleX += deltaY * TOUCH_SCALE_FACTOR;
                renderer.angleY += deltaX * TOUCH_SCALE_FACTOR;
        }
        // Save current x, y
        previousX = currentX;
        previousY = currentY;
        return true;  // Event handled
    }



}

