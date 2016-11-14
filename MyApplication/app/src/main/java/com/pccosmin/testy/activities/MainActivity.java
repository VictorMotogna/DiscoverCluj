package com.pccosmin.testy.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pccosmin.testy.R;
import com.pccosmin.testy.views.MainActivityViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.activity_main_viewPager)
    ViewPager mainViewPager;
    @BindView(R.id.activity_main_tabLayout)
    TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        while(!hasPermissions(this, PERMISSIONS));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainActivityViewPagerAdapter adapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mainViewPager);
    }

    public void launchAr(View view)
    {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
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
    public void addNewActivity(View view) {
        Intent intent = new Intent(this, AddNewLocationActivity.class);
        startActivity(intent);
    }
}
