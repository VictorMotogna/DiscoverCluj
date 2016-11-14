package com.pccosmin.testy.views.MeetABroViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pccosmin.testy.R;
import com.pccosmin.testy.model.Location;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowLocationViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.list_meet_a_bro_ImageView)
    public ImageView brotherPic;

    @BindView(R.id.list_meet_a_bro_name)
    public TextView locationName;

    @BindView(R.id.list_meet_a_bro_location)
    public TextView locationLocation;

    @BindView(R.id.list_meet_a_bro_progressBar)
    ProgressBar progressBar;
    public ShowLocationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void populate(Context context, Location location){
        itemView.setTag(location);
        locationName.setText(location.getLocationName());
        locationLocation.setText(location.getAddress());
        Picasso.with(context).load(location.getLocationPicture())
                .fit()
                .centerCrop()
                .into(brotherPic, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }
}
