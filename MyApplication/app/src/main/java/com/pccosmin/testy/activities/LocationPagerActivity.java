package com.pccosmin.testy.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.pccosmin.testy.R;
import com.pccosmin.testy.fragments.LocationDetailFragment;
//import com.pccosmin.testy.model.Brother;
import com.pccosmin.testy.model.Location;
//import com.pccosmin.testy.services.BrotherServices;
import com.pccosmin.testy.services.LocationServices;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationPagerActivity extends BaseActivity{
    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";
    private ArrayList<Location> locations;

    @BindView(R.id.activity_brother_pager_viewPager)
    ViewPager brotherViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brother_pager);
        ButterKnife.bind(this);
        locations = new ArrayList<>();
        bus.post(new LocationServices.SearchLocationRequest("Hello"));

        brotherViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Location location = locations.get(position);
                return LocationDetailFragment.newInstance(location);
            }

            @Override
            public int getCount() {
                return locations.size();
            }
        });

        Location location = getIntent().getParcelableExtra(BROTHER_EXTRA_INFO);
        int locationId = location.getLocationId();

        for(int i = 0; i < locations.size(); i++){
            if(locations.get(i).getLocationId() == locationId){
                brotherViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Subscribe
    public void getLocation(LocationServices.SearchLocationResponse response){
        locations.clear();
        locations.addAll(response.locations);
    }

    public static Intent newIntent(Context context, Location location){
        Intent intent = new Intent(context, LocationPagerActivity.class);
        intent.putExtra(BROTHER_EXTRA_INFO, location);
        return intent;
    }
}
