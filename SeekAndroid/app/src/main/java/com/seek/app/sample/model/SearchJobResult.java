package com.seek.app.sample.model;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.List;

@AutoValue
public abstract class SearchJobResult {
    public abstract String title();

    public abstract List<JobItem> jobItems();

    public static SearchJobResult create(SearchJobResultResponse searchJobResultResponse) {
        List<JobItem> jobItemsList = new ArrayList<>();
        for (SearchJobResultResponse.Job item : searchJobResultResponse.jobs) {
            jobItemsList.add(JobItem.create(item.jobId, item.listingDate));
        }
        return new AutoValue_SearchJobResult(searchJobResultResponse.title, jobItemsList);
    }
}
