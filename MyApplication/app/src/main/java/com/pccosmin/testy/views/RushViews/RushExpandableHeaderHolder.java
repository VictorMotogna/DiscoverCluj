package com.pccosmin.testy.views.RushViews;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pccosmin.testy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RushExpandableHeaderHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.list_expandable_header_layout)
    View backgroundView;

    @BindView(R.id.list_expandable_header_name)
    TextView headerTitle;

    @BindView(R.id.list_expandable_header_buttonToggle)
    ImageView buttonToggle;

    public Item referalItem;

    public RushExpandableHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
