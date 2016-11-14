package com.pccosmin.testy.views.AboutUsViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pccosmin.testy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsListHeaderViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.simple_header_textView)
    TextView headerText;

    public AboutUsListHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(String header){
        headerText.setText(header);
    }
}
