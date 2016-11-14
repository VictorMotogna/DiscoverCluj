package com.pccosmin.testy.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pccosmin.testy.fragments.AboutUsFragment;
//import com.pccosmin.testy.fragments.MeetABroFragment;
import com.pccosmin.testy.fragments.RushFragment;
import com.pccosmin.testy.fragments.ShowLocationFragment;

/**
 * Created by CristianCosmin on 09.11.2016.
 */

public class MainActivityViewPagerAdapter extends FragmentStatePagerAdapter {

    public MainActivityViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;
        switch (position){
            case 0:
                returnFragment = AboutUsFragment.newInstance();
                break;
            case 1:
                returnFragment = ShowLocationFragment.newInstance();
                break;
            case 2:
                returnFragment = RushFragment.newInstance();
                break;
            default:
                return null;
        }
        return returnFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch (position){
            case 0:
                title = "Events";
                break;
            case 1:
                title = "Locations";
                break;
            case 2:
                title = "Visit";
                break;
            default:
                return null;
        }
        return title;
    }
}
