package com.pccosmin.testy.views.CameraViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

public class GifView extends View {

    private InputStream gifInputStream;
    private Movie gifMovie;
    private int movieWidth, movieHeight;
    private long movieDuration;
    private long mMovieStart;
    private int rw;

    public GifView(Context context, int rw) {
        super(context);
        this.rw=rw;
        init(context);
    }

    public GifView(Context context, AttributeSet attrs, int rw) {
        super(context, attrs);
        this.rw=rw;
        init(context);
    }

    public GifView(Context context, AttributeSet attrs,
                   int defStyleAttr,int rw) {
        super(context, attrs, defStyleAttr);
        this.rw=rw;
        init(context);
    }

    private void init(Context context){
        setFocusable(true);
        gifInputStream = context.getResources()
                .openRawResource(rw);

        gifMovie = Movie.decodeStream(gifInputStream);
        movieWidth = gifMovie.width();
        movieHeight = gifMovie.height();
        movieDuration = gifMovie.duration();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        setMeasuredDimension(movieWidth, movieHeight);
    }

    public int getMovieWidth(){
        return movieWidth;
    }

    public int getMovieHeight(){
        return movieHeight;
    }

    public long getMovieDuration(){
        return movieDuration;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        long now = android.os.SystemClock.uptimeMillis();
        if (mMovieStart == 0) {   // first time
            mMovieStart = now;
        }

        if (gifMovie != null) {

            int dur = gifMovie.duration();
            if (dur == 0) {
                dur = 1000;
            }

            int relTime = (int)((now - mMovieStart) % dur);

            gifMovie.setTime(relTime);

            gifMovie.draw(canvas, 0, 0);
            invalidate();

        }

    }

}