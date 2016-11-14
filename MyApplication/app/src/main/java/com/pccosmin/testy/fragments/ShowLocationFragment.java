package com.pccosmin.testy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.pccosmin.testy.R;
import com.pccosmin.testy.activities.BaseActivity;
import com.pccosmin.testy.activities.CameraActivity;
import com.pccosmin.testy.activities.LocationPagerActivity;
import com.pccosmin.testy.model.Location;
import com.pccosmin.testy.services.LocationServices;
import com.pccosmin.testy.utils.Globals;
import com.pccosmin.testy.views.MeetABroViews.ShowLocationAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;

public class ShowLocationFragment extends BaseFragment implements ShowLocationAdapter.OnLocationClicked {
    private final String LOG_TAG = ShowLocationFragment.class.getSimpleName();
    private ShowLocationAdapter aLocationAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Location> locations;

    public static ShowLocationFragment newInstance(){
        return new ShowLocationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meet_a_bro, container, false);
        aLocationAdapter = new ShowLocationAdapter(this,(BaseActivity) getActivity());
//        recyclerView.setHasFixedSize(true);
        locations = aLocationAdapter.getLocations();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_meet_a_bro);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpAdapter();
        bus.post(new LocationServices.SearchLocationRequest("Hello"));
        FloatingActionButton arButton = (FloatingActionButton) rootView.findViewById(R.id.arButton);
        FloatingActionButton treasureHuntButton = (FloatingActionButton) rootView.findViewById(R.id.treasureHuntButton);
        arButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Globals.gTreasureHuntMode = false;
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }
        });
        treasureHuntButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Globals.gTreasureHuntMode = true;
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void setUpAdapter(){
        if(isAdded()){
            recyclerView.setAdapter(aLocationAdapter);
        }
    }

    @Override
    public void OnLocationClicked(Location location) {
        Intent intent = LocationPagerActivity.newIntent(getActivity(), location);
        startActivity(intent);
    }

    @Subscribe
    public void getLocations(LocationServices.SearchLocationResponse response){
        locations.clear();
        locations.addAll(response.locations);
    }
}
