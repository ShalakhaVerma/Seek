package com.seek.app.sample.ui.serp;

import com.seek.app.sample.model.JobItem;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SerpViewModelImpl extends ViewModel implements SerpViewModel {
    @NonNull
    private final MutableLiveData<List<JobItem>> searchResultObserbale;

    @Inject
    SerpViewModelImpl() {
        searchResultObserbale = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<JobItem>> getSearchResultObservable() {
        return searchResultObserbale;
    }

    @Override
    public void configureItems(List<JobItem> jobItems) {
        searchResultObserbale.setValue(jobItems);
    }
}
