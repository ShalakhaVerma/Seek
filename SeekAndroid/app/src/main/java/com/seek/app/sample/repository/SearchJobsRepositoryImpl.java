package com.seek.app.sample.repository;

import com.seek.app.sample.model.SearchJobResult;
import com.seek.app.sample.services.NetworkHub;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Single;

public class SearchJobsRepositoryImpl implements SearchJobsRepository {

    @NonNull
    private final NetworkHub mNetworkHub;

    @Inject
    public SearchJobsRepositoryImpl(@NonNull NetworkHub networkHub) {
        this.mNetworkHub = networkHub;
    }

    @Override
    public Single<SearchJobResult> getSearchJobResults(String what, String where, int page, int pageSize) {
        return mNetworkHub.getSearchJobResults(what, where, page, pageSize);
    }

}
