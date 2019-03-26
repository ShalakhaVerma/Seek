package com.seek.app.sample.ui.serp;

import com.seek.app.sample.model.JobItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public interface SerpViewModel {

    @NonNull
    LiveData<List<JobItem>> getSearchResultObservable();

    void configureItems(List<JobItem> jobItems);
}
