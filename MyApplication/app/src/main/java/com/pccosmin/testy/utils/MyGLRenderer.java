package com.pccosmin.testy.utils;

/**
 * Created by LenovoM on 11/12/2016.
 */

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
/**
 *  OpenGL Custom renderer used with GLSurfaceView
 */
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.pccosmin.testy.model.TextureCube;


public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Context context;   // Application context needed to read image (NEW)
    private TextureCube cube;
    // private static float angleCube = 0;     // rotational angle in degree for cube
    //private static float speedCube = -1.5f; // rotational speed for cube
    public float angleX = 0;   // (NEW)
    public float angleY = 5.0f;   // (NEW)
    public float speedX = 0;   // (NEW)
    public float speedY = 0;   // (NEW)
    public float z = -6.0f;    // (NEW)

    // Constructor
    public MyGLRenderer(Context context) {
        this.context = context;   // Get the application context (NEW)
        cube = new TextureCube();
    }




    // Call back when the surface is first created or re-created
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glClearColor(0,0,0,0);
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_FASTEST);

        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance

        // You OpenGL|ES initialization code here
        // ......
        // cube.loadTexture(gl, context);    // Load image into Texture (NEW)
        //  gl.glEnable(GL10.GL_TEXTURE_2D);  // Enable texture (NEW)
        // You OpenGL|ES initialization code here
        // ......
        cube.loadTexture(gl, context);    // Load image into Texture (NEW)
        gl.glEnable(GL10.GL_TEXTURE_2D);  // Enable texture (NEW)
    }

    // Call back after onSurfaceCreated() or whenever the window's size changes
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float) width / height;

        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset

        // You OpenGL|ES display re-sizing code here
        // ......
    }

//    @Override
//    public void onDrawFrame(GL10 gl) {
//        // Clear color and depth buffers using clear-values set earlier
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//
//        gl.glLoadIdentity();                 // Reset model-view matrix ( NEW )
//        gl.glTranslatef(-1.5f, 0.0f, -6.0f); // Translate left and into the screen ( NEW )
//        gl.glRotatef(angleTriangle, 0.0f, 1.0f, 0.0f); // Rotate the triangle about the y-axis (NEW)
//        triangle.draw(gl);                   // Draw triangle ( NEW )
//
//        gl.glLoadIdentity();                 // Reset the mode-view matrix (NEW)
//        gl.glTranslatef(1.5f, 0.0f, -6.0f);  // Translate right and into the screen (NEW)
//        gl.glRotatef(angleQuad, 1.0f, 0.0f, 0.0f); // Rotate the square about the x-axis (NEW)
//
//        // Translate right, relative to the previous translation ( NEW )
//        gl.glTranslatef(3.0f, 0.0f, 0.0f);
//        quad.draw(gl);
//
//        // Update the rotational angle after each refresh (NEW)
//        angleTriangle += speedTriangle; // (NEW)
//        angleQuad += speedQuad;         // (NEW)
//    }
//
//    @Override
//    public void onDrawFrame(GL10 gl) {
//        // Clear color and depth buffers
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//
//        // ----- Render the Pyramid -----
//        gl.glLoadIdentity();                 // Reset the model-view matrix
//        gl.glTranslatef(-1.5f, 0.0f, -6.0f); // Translate left and into the screen
//        gl.glRotatef(anglePyramid, 0.1f, 1.0f, -0.1f); // Rotate (NEW)
//        pyramid.draw(gl);                              // Draw the pyramid (NEW)
//
//        // ----- Render the Color Cube -----
//        gl.glLoadIdentity();                // Reset the model-view matrix
//        gl.glTranslatef(1.5f, 0.0f, -6.0f); // Translate right and into the screen
//        gl.glScalef(0.8f, 0.8f, 0.8f);      // Scale down (NEW)
//        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // rotate about the axis (1,1,1) (NEW)
//        cube.draw(gl);                      // Draw the cube (NEW)
//
//        // Update the rotational angle after each refresh (NEW)
//        anglePyramid += speedPyramid;   // (NEW)
//        angleCube += speedCube;         // (NEW)
//    }

    // Call back to draw the current frame.
    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // ----- Render the Cube -----
        gl.glLoadIdentity();                  // Reset the current model-view matrix
        gl.glTranslatef(0.0f, 0.0f, z);   // Translate into the screen (NEW)
        gl.glRotatef(angleX, 1.0f, 0.0f, 0.0f); // Rotate (NEW)
        gl.glRotatef(angleY, 0.0f, 1.0f, 0.0f); // Rotate (NEW)
        cube.draw(gl);



        // Update the rotational angle after each refresh (NEW)
        //angleX += speedX;  // (NEW)
        angleY += speedY;  // (NEW)
    }
}


//    @Override
//    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        gl.glDisable(GL10.GL_DITHER);
//        gl.glClearColor(0,0,0,0);
//        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
//        gl.glEnable(GL10.GL_DEPTH_TEST);
//        gl.glEnable(GL10.GL_CULL_FACE);
//        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
//        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
//                GL10.GL_FASTEST);
//
//        gl.glShadeModel(GL10.GL_SMOOTH);
//        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance
//
//        // You OpenGL|ES initialization code here
//        // ......
//        // cube.loadTexture(gl, context);    // Load image into Texture (NEW)
//        //  gl.glEnable(GL10.GL_TEXTURE_2D);  // Enable texture (NEW)
//    }