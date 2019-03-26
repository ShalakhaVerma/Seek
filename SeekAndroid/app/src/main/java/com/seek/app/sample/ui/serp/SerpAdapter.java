package com.seek.app.sample.ui.serp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seek.app.sample.R;
import com.seek.app.sample.model.JobItem;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SerpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<JobItem> jobItems;

    private SearchItemClickListener searchItemClickListener;

    public SerpAdapter(SearchItemClickListener listener) {
        this.searchItemClickListener = listener;
    }

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
        final JobItem jobItem = jobItems.get(position);
        SerpViewHolder serpViewHolder = (SerpViewHolder) holder;
        if (serpViewHolder != null) {
            serpViewHolder.title.setText(jobItem.title());
            serpViewHolder.advertiserName.setText(jobItem.advertiser().description());
            serpViewHolder.teaser.setText(jobItem.teaser()
            );

            serpViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (searchItemClickListener != null) {
                        searchItemClickListener.onItemClickListener(jobItem.searchId());
                    }
                }
            });
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
        public final CardView itemView;

        public SerpViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView.findViewById(R.id.card_view);
            title = itemView.findViewById(R.id.title);
            teaser = itemView.findViewById(R.id.teaser);
            advertiserName = itemView.findViewById(R.id.advertiseName);

        }
    }

    public interface SearchItemClickListener {

        void onItemClickListener(String jobSearchId);
    }
}
