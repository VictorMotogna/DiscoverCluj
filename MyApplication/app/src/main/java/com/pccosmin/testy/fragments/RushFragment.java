package com.pccosmin.testy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.pccosmin.testy.R;
import com.pccosmin.testy.activities.BaseActivity;
import com.pccosmin.testy.activities.CameraActivity;
import com.pccosmin.testy.activities.CampusMapActivity;
import com.pccosmin.testy.activities.MapsActivity;
import com.pccosmin.testy.model.RushEvent;
import com.pccosmin.testy.services.RushEventsService;
import com.pccosmin.testy.utils.Globals;
import com.pccosmin.testy.views.RushViews.Item;
import com.pccosmin.testy.views.RushViews.RushEventAdapter;
import com.squareup.otto.Subscribe;


import java.util.ArrayList;
import java.util.List;

public class RushFragment extends BaseFragment implements RushEventAdapter.RushEventListener{

    private RushEventAdapter adapter;

    private ArrayList<RushEvent> socialEvents;
    private ArrayList<RushEvent> communityEvents;

    private Item social;
    private Item community;

    private RecyclerView recyclerView;

    public static RushFragment newInstance(){
        return new RushFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rush, container, false);
        adapter = new RushEventAdapter(this, (BaseActivity) getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_rush_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        socialEvents = new ArrayList<>();
        communityEvents = new ArrayList<>();

        List<Item> data = adapter.getData();

        social = new Item(RushEventAdapter.VIEW_TYPE_EXPANDABLE_LIST_HEADER, "Social Events");
        social.invisibleChildren = new ArrayList<>();
        community = new Item(RushEventAdapter.VIEW_TYPE_EXPANDABLE_LIST_HEADER, "Community Events");
        community.invisibleChildren = new ArrayList<>();

        bus.post(new RushEventsService.SearchRushEventsCommunityRequest("Hello"));
        bus.post(new RushEventsService.SearchRushEventsSocialRequest("Hello"));

        setUpAdapter();
        data.add(community);
        data.add(social);

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

    private void setUpAdapter() {
        if (isAdded()) {
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onRushEventClicked(RushEvent rushEvent) {
        if(!rushEvent.isOnCampus()){
            Intent intent = MapsActivity.newIntent(getActivity(), rushEvent);
            startActivity(intent);
        } else {
            Intent intent = CampusMapActivity.newIntent(getActivity(), rushEvent);
            startActivity(intent);
        }
    }

    @Subscribe
    public void getCommunityServiceEvents(RushEventsService.SearchRushEventsCommunityResponse communityResponse){
        communityEvents.clear();
        communityEvents.addAll(communityResponse.communityRushEvents);
        for(RushEvent rushEvent: communityEvents){
            community.invisibleChildren.add(new Item(RushEventAdapter.VIEW_TYPE_EXPANDABLE_LIST_CHILD, rushEvent));
        }
    }

    @Subscribe
    public void getSocialServiceEvents(RushEventsService.SearchRushEventsSocialResponse socialResponse){
        socialEvents.clear();
        socialEvents.addAll(socialResponse.socialRushEvents);
        for(RushEvent rushEvent: socialEvents){
            social.invisibleChildren.add(new Item(RushEventAdapter.VIEW_TYPE_EXPANDABLE_LIST_CHILD, rushEvent));
        }
    }
}
