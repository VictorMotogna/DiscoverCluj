package com.pccosmin.testy.model;

/**
 * Created by LenovoM on 11/12/2016.
 */

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.pccosmin.testy.R;
/*
 * Define the vertices for a representative face.
 * Render the cube by translating and rotating the face.
 */
//public class TextureCube {
//    private FloatBuffer vertexBuffer; // Buffer for vertex-array
//    private FloatBuffer texBuffer;    // Buffer for texture-coords-array (NEW)
//    private ByteBuffer indexBuffer;
//
//    private float[] vertices = { // Vertices for a face at z=0
//            1.0f, 1.0f, 0.0f,  // 0. left-bottom-front
//            0.5f, 1.0f, 0.0f,  // 1. right-bottom-front
//            1.0f,  0.5f, 0.0f,  // 2. left-top-front
//            0.5f,  0.5f, 0.0f   // 3. right-top-front
//    };
//
//    float[] texCoords = { // Texture coords for the above face (NEW)
//            0.0f, 0.5f,  // A. left-bottom (NEW)
//            0.5f, 0.5f,  // B. right-bottom (NEW)
//            0.0f, 0.0f,  // C. left-top (NEW)
//            0.5f, 0.0f   // D. right-top (NEW)
//    };
//    int[] textureIDs = new int[6];   // Array for 1 texture-ID (NEW)
//
//    private float[][] colors = {  // Colors of the 6 faces
//            {0.5f, 0.5f, 0.0f, 0.5f},  // 0. orange
//            {0.5f, 0.0f, 0.5f, 0.5f},  // 1. violet
//            {0.0f, 0.5f, 0.0f, 0.5f},  // 2. green
//            {0.0f, 0.0f, 0.5f, 0.5f},  // 3. blue
//            {0.5f, 0.0f, 0.0f, 0.5f},  // 4. red
//            {0.5f, 0.5f, 0.0f, 0.5f}   // 5. yellow
//
//
//    };
//
//    private int[] resourceIds = new int[]{
//            R.drawable.face,
//            R.drawable.face,
//            R.drawable.face,
//            R.drawable.face,
//            R.drawable.bottom,
//            R.drawable.top};
//
//    // Constructor - Set up the buffers
//    public TextureCube() {
//        // Setup vertex-array buffer. Vertices in float. An float has 4 bytes
//        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
//        vbb.order(ByteOrder.nativeOrder()); // Use native byte order
//        vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
//        vertexBuffer.put(vertices);         // Copy data into buffer
//        vertexBuffer.position(0);           // Rewind
//
//        ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
//        tbb.order(ByteOrder.nativeOrder());
//        texBuffer = tbb.asFloatBuffer();
//        texBuffer.put(texCoords);
//        texBuffer.position(0);
//    }
//
//    // Draw the shape
//    public void draw(GL10 gl) {
//        gl.glFrontFace(GL10.GL_CCW);    // Front face in counter-clockwise orientation
//        gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face
//        gl.glCullFace(GL10.GL_BACK);    // Cull the back face (don't display)
//
//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
//        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  // Enable texture-coords-array (NEW)
//        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer); // Define texture-coords buffer (NEW)
//
//        // front
//        gl.glPushMatrix();
//        gl.glTranslatef(0.0f, 0.0f, 0.5f);
//        gl.glColor4f(colors[0][0], colors[0][1], colors[0][2], colors[0][3]);
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//        gl.glPopMatrix();
//
//        // left
//        gl.glPushMatrix();
//        gl.glRotatef(270.0f, 0.0f, 0.5f, 0.0f);
//        gl.glTranslatef(0.0f, 0.0f, 0.5f);
//        gl.glColor4f(colors[1][0], colors[1][1], colors[1][2], colors[1][3]);
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//        gl.glPopMatrix();
//
//        // back
//        gl.glPushMatrix();
//        gl.glRotatef(180.0f, 0.0f, 0.5f, 0.0f);
//        gl.glTranslatef(0.0f, 0.0f, 0.5f);
//        gl.glColor4f(colors[2][0], colors[2][1], colors[2][2], colors[2][3]);
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//        gl.glPopMatrix();
//
//        // right
//        gl.glPushMatrix();
//        gl.glRotatef(90.0f, 0.0f, 0.5f, 0.0f);
//        gl.glTranslatef(0.0f, 0.0f, 0.5f);
//        gl.glColor4f(colors[3][0], colors[3][1], colors[3][2], colors[3][3]);
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//        gl.glPopMatrix();
//
//        // top
//        gl.glPushMatrix();
//        gl.glRotatef(270.0f, 0.5f, 0.0f, 0.0f);
//        gl.glTranslatef(0.0f, 0.0f, 0.5f);
//        gl.glColor4f(colors[4][0], colors[4][1], colors[4][2], colors[4][3]);
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//        gl.glPopMatrix();
//
//        // bottom
//        gl.glPushMatrix();
//        gl.glRotatef(90.0f, 0.5f, 0.0f, 0.0f);
//        gl.glTranslatef(0.0f, 0.0f, 0.5f);
//        gl.glColor4f(colors[5][0], colors[5][1], colors[5][2], colors[5][3]);
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//        gl.glPopMatrix();
//
//        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  // Disable texture-coords-array (NEW)
//        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glDisable(GL10.GL_CULL_FACE);
//
//
//    }
//
//    public void loadTexture(GL10 gl, Context context) {
//        gl.glGenTextures(6, textureIDs, 0); // Generate texture-ID array
//
//        //Bitmap bitmap=null;
//
//        for(int i=0;i<6;i++) {
//
//           // bitmap = BitmapFactory.decodeResource(context.getResources(), resourceIds[i]);
//
//
//            gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[i]);   // Bind to texture ID
//            // Set up texture filters
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
//
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
//
//            // Construct an input stream to texture image "res\drawable\nehe.png"
//
//
//            InputStream istream = context.getResources().openRawResource(resourceIds[i]);
//            Bitmap bitmap;
//
//            try {
//                // Read and decode input as bitmap
//                bitmap = BitmapFactory.decodeStream(istream);
//            } finally {
//                try {
//                    istream.close();
//                } catch (IOException e) {
//                }
//            }
//
//            // Build Texture from loaded bitmap for the currently-bind texture ID
//            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
//            bitmap=null;
//           // bitmap.recycle();
//        }
//    }
//}

public class TextureCube {

    /**
     * The buffer holding the vertices
     */
    private FloatBuffer vertexBuffer;
    /**
     * The buffer holding the texture coordinates
     */
    private FloatBuffer textureBuffer;
    /**
     * The buffer holding the indices
     */
    private ByteBuffer indexBuffer;

    /**
     * Our texture pointer
     */
    private int[] textures = new int[6];

    /**
     * Textures
     */
    private int[] resourceIds = new int[]{
            R.raw.face,
            R.raw.face,
            R.raw.face,
            R.raw.face,
            R.raw.top,
            R.raw.bottom};

    /**
     * The initial vertex definition
     * <p>
     * Note that each face is defined, even
     * if indices are available, because
     * of the texturing we want to achieve
     */
    private float vertices[] = {
            //Vertices according to faces
            -0.25f, 0.5f, 0.25f, //Vertex 0
            0.25f, 0.5f, 0.25f,  //v1
            -0.25f, 1.0f, 0.25f,  //v2
            0.25f, 1.0f, 0.25f,   //v3

            0.25f, 0.5f, 0.25f,  //...
            0.25f, 0.5f, -0.25f,
            0.25f, 1.0f, 0.25f,
            0.25f, 1.0f, -0.25f,

            0.25f, 0.5f, -0.25f,
            -0.25f, 0.5f, -0.25f,
            0.25f, 1.0f, -0.25f,
            -0.25f, 1.0f, -0.25f,

            -0.25f, 0.5f, -0.25f,
            -0.25f, 0.5f, 0.25f,
            -0.25f, 1.0f, -0.25f,
            -0.25f, 1.0f, 0.25f,

            -0.25f, 0.5f, -0.25f,
            0.25f, 0.5f, -0.25f,
            -0.25f, 0.5f, 0.25f,
            0.25f, 0.5f, 0.25f,

            -0.25f, 1.0f, 0.25f,
            0.25f, 1.0f, 0.25f,
            -0.25f, 1.0f, -0.25f,
            0.25f, 1.0f, -0.25f,
    };

    /**
     * The initial texture coordinates (u, v)
     */
    private float texture[] = {
            //Mapping coordinates for the vertices

            0.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,

    };

    /**
     * The initial indices definition
     */
    private byte indices[] = {
            //Faces definition
            0, 1, 3, 0, 3, 2,           //Face front
            4, 5, 7, 4, 7, 6,           //Face right
            8, 9, 11, 8, 11, 10,        //...
            12, 13, 15, 12, 15, 14,
            16, 17, 19, 16, 19, 18,
            20, 21, 23, 20, 23, 22,
    };

    /**
     * The Cube constructor.
     * <p>
     * Initiate the buffers.
     */
    public TextureCube() {
        //
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        //
        byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuf.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);

        //
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    /**
     * The object own drawing function.
     * Called from the renderer to redraw this instance
     * with possible changes in values.
     *
     * @param gl - The GL Context
     */
    public void draw(GL10 gl) {

        //Point to our buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        //Set the face rotation
        gl.glFrontFace(GL10.GL_CCW);

        //Enable the vertex and texture state
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);

        for (int i = 0; i < 6; i++) {
            //Bind our only previously generated texture in this case
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[i]);
            indexBuffer.position(6 * i);
            //Draw the vertices as triangles, based on the Index Buffer information
            gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, indexBuffer);
        }

        //Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    /**
     * Load the textures
     *
     * @param gl      - The GL Context
     * @param context - The Activity context
     */
    public void loadTexture(GL10 gl, Context context) {

        //Generate a 6 texture pointer...
        gl.glGenTextures(6, textures, 0);

        // Bitmap bitmap = null;

        for (int i = 0; i < 6; i++) {
            // Create a bitmap
            // bitmap = BitmapFactory.decodeResource(context.getResources(), resourceIds[i]);

            //...and bind it to our array
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[i]);

            //Create Nearest Filtered Texture
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

            //Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

            InputStream istream = context.getResources().openRawResource(resourceIds[i]);
            Bitmap bitmap;

            try {
                // Read and decode input as bitmap
                bitmap = BitmapFactory.decodeStream(istream);
            } finally {
                try {
                    istream.close();
                } catch (IOException e) {
                }
            }


            //Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

            //Clean up
            bitmap = null;
        }
    }
}
