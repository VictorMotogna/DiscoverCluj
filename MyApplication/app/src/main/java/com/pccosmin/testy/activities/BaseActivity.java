package com.pccosmin.testy.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.pccosmin.testy.infrastructure.TestyApplication;
import com.squareup.otto.Bus;

public class BaseActivity extends AppCompatActivity {
    protected TestyApplication application;
    protected Bus bus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (TestyApplication) getApplication();
        bus = application.getBus();
        bus.register(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
