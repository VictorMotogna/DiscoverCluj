package com.pccosmin.testy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.pccosmin.testy.R;
import com.pccosmin.testy.activities.BaseActivity;
import com.pccosmin.testy.activities.CameraActivity;
import com.pccosmin.testy.activities.LocationPagerActivity;
import com.pccosmin.testy.model.EventCard;
import com.pccosmin.testy.services.EventCardService;
import com.pccosmin.testy.utils.Globals;
import com.pccosmin.testy.views.AboutUsViews.AboutUsAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by CristianCosmin on 09.11.2016.
 */

public class AboutUsFragment extends BaseFragment implements AboutUsAdapter.AboutUsListener{

    private ArrayList<EventCard> serviceCards;
    private ArrayList<EventCard> brotherhoodCards;
    private ArrayList<EventCard> socialCards;

    private RecyclerView recyclerView;
    private AboutUsAdapter aboutUsAdapter;

    public static AboutUsFragment newInstance(){
        return new AboutUsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        aboutUsAdapter = new AboutUsAdapter((BaseActivity) getActivity(), this);
        serviceCards = aboutUsAdapter.getCommunityServiceCards();
        brotherhoodCards = aboutUsAdapter.getBrotherhoodEventCards();
        socialCards = aboutUsAdapter.getSocialEventCards();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_about_us_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpAdapter();
        bus.post(new EventCardService.SearchCommunityServiceCardsRequest("Hello!"));
        bus.post(new EventCardService.SearchBrotherhoodCardsRequest("Hello!"));
        bus.post(new EventCardService.SearchSocialCardsRequest("Hello!"));

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
            recyclerView.setAdapter(aboutUsAdapter);
        }
    }

    @Override
    public void OnEventCardClicked(EventCard eventCard) {
        if(!eventCard.isVideo()){
            Log.i(AboutUsFragment.class.getSimpleName(), eventCard.getEventName() + "is a slideshow");
        } else {
            Log.i(AboutUsFragment.class.getSimpleName(), eventCard.getEventName() + "is a youtube video");
        }
    }

    @Subscribe
    public void getCommunityEvents(EventCardService.SearchCommunityServiceCardsResponse communityServiceCardsResponse){
        serviceCards.clear();
        serviceCards.addAll(communityServiceCardsResponse.communityServiceCards);
    }

    @Subscribe
    public void getBrotherhoodEvents(EventCardService.SearchBrotherhoodCardsResponse brotherhoodCardsResponse){
        brotherhoodCards.clear();
        brotherhoodCards.addAll(brotherhoodCardsResponse.brotherhoodCards);
    }

    @Subscribe
    public void getSocialEvents(EventCardService.SearchSocialCardsResponse socialCardsResponse){
        socialCards.clear();
        socialCards.addAll(socialCardsResponse.socialCards);
    }
}
