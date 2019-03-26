package com.seek.app.sample.model;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.List;

@AutoValue
public abstract class SearchJobResult {
    public abstract String title();

    public abstract ArrayList<JobItem> jobItems();

    public static SearchJobResult create(SearchJobResultResponse searchJobResultResponse) {
        ArrayList<JobItem> jobItemsList = new ArrayList<>();
        for (SearchJobResultResponse.Job item : searchJobResultResponse.jobs) {
            jobItemsList.add(JobItem.create(item.jobId, item.listingDate, item.title, item.teaser, item.advertiser
            ));
        }
        return new AutoValue_SearchJobResult(searchJobResultResponse.title, jobItemsList);
    }
}
