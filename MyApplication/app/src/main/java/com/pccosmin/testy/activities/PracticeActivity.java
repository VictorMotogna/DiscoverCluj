package com.pccosmin.testy.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.pccosmin.testy.R;
import com.pccosmin.testy.fragments.LocationDetailFragment;
import com.pccosmin.testy.model.Location;

public class PracticeActivity extends BaseActivity {
    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_practice_fragment_container);

        if(fragment == null){
            fragment = null;
            fragmentManager.beginTransaction()
                    .add(R.id.activity_practice_fragment_container, fragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context context, Location location){
        Intent intent = new Intent(context, PracticeActivity.class);
        intent.putExtra(BROTHER_EXTRA_INFO, location);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
