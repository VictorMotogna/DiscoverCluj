package com.pccosmin.testy.views.MeetABroViews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pccosmin.testy.R;
import com.pccosmin.testy.activities.BaseActivity;
import com.pccosmin.testy.model.Location;

import java.util.ArrayList;

public class ShowLocationAdapter extends RecyclerView.Adapter<ShowLocationViewHolder> implements View.OnClickListener{
    private LayoutInflater inflater;
    private BaseActivity activity;
    private OnLocationClicked listener;
    private ArrayList<Location> locations;


    public ShowLocationAdapter(OnLocationClicked listener, BaseActivity activity) {
        this.activity = activity;
        this.listener = listener;
        inflater = activity.getLayoutInflater();
        locations = new ArrayList<>();

    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    @Override
    public ShowLocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listView = inflater.inflate(R.layout.list_meet_a_bro, parent, false);
        listView.setOnClickListener(this);
        return new ShowLocationViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(ShowLocationViewHolder holder, int position) {
        holder.populate(activity, locations.get(position));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public void onClick(View view) {
        if(view.getTag() instanceof Location){
            Location brother = (Location) view.getTag();
            listener.OnLocationClicked(brother);
        }
    }

    public interface OnLocationClicked{
        void OnLocationClicked(Location location);
    }
}
