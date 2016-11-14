package com.pccosmin.testy.views.RushViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pccosmin.testy.R;
import com.pccosmin.testy.model.RushEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RushEventViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.list_rush_event_name)
    TextView rushEventName;

    @BindView(R.id.list_rush_event_location)
    TextView rushEventLocation;

    @BindView(R.id.list_rush_event_date)
    TextView rushEventDate;

    @BindView(R.id.list_rush_event_time)
    TextView rushEventTime;


    public RushEventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(RushEvent rushEvent){
        itemView.setTag(rushEvent);
        rushEventName.setText(rushEvent.getEventName());
        rushEventLocation.setText(rushEvent.getEventLocation());
        rushEventDate.setText(rushEvent.getEventDate());
        rushEventTime.setText(rushEvent.getEventTime());
    }
}
