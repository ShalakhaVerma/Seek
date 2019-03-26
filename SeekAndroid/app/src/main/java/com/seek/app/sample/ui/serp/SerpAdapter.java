package com.seek.app.sample.ui.serp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seek.app.sample.R;
import com.seek.app.sample.model.JobItem;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SerpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<JobItem> jobItems;

    public synchronized void setJobItems(List<JobItem> jobItems) {
        this.jobItems = jobItems;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_item, parent, false);
        return new SerpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        JobItem jobItem = jobItems.get(position);
        SerpViewHolder serpViewHolder = (SerpViewHolder) holder;
        if (serpViewHolder != null) {
            serpViewHolder.title.setText(jobItem.title());
            serpViewHolder.advertiserName.setText(jobItem.advertiser().description());
            serpViewHolder.teaser.setText(jobItem.teaser()
            );
        }
    }

    @Override
    public int getItemCount() {
        return jobItems.size();
    }

    private class SerpViewHolder extends RecyclerView.ViewHolder {

        public final TextView title;
        public final TextView teaser;
        public final TextView advertiserName;

        public SerpViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            teaser = itemView.findViewById(R.id.teaser);
            advertiserName = itemView.findViewById(R.id.advertiseName);

        }
    }
}
