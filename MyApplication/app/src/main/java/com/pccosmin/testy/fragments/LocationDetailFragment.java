package com.pccosmin.testy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pccosmin.testy.R;
import com.pccosmin.testy.model.Location;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationDetailFragment extends BaseFragment {
    @BindView(R.id.fragment_brother_detail_crossed)
    TextView brotherCrossed;

    @BindView(R.id.fragment_brother_detail_FunFact)
    TextView brotherFunFact;

    @BindView(R.id.fragment_brother_detail_name)
    TextView brotherName;

    @BindView(R.id.fragment_brother_detail_major)
    TextView brotherMajor;

    @BindView(R.id.fragment_brother_detail_ImageView)
    ImageView brotherPicture;

    @BindView(R.id.fragment_brother_detail_progressBar)
    ProgressBar brotherProgressBar;

    @BindView(R.id.fragment_brother_detail_WhyJoined)
    TextView brotherWhyJoined;

    private Location location;
    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";


    public static LocationDetailFragment newInstance(Location location){
        Bundle arguments = new Bundle();
        arguments.putParcelable(BROTHER_EXTRA_INFO, location);
        LocationDetailFragment locationDetailFragment = new LocationDetailFragment();
        locationDetailFragment.setArguments(arguments);
        return locationDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brother_details, container, false);
        ButterKnife.bind(this, rootView);
        brotherName.setText(location.getLocationName());
        brotherWhyJoined.setText(location.getAboutText());
        brotherMajor.setText(getString(R.string.major_intro, location.getAddress()));
        brotherFunFact.setText(getString(R.string.fun_fact, location.getFunFact()));
        brotherCrossed.setText(getString(R.string.crossed_semester_intro, location.getInauguration()));
        Picasso.with(getActivity()).load(location.getLocationPicture())
                .fit()
                .centerCrop()
                .into(brotherPicture, new Callback() {
                    @Override
                    public void onSuccess() {
                        brotherProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        location = getArguments().getParcelable(BROTHER_EXTRA_INFO);
    }
}
