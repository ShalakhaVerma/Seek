package com.seek.app.sample.repository;


import com.seek.app.sample.model.SearchJobResult;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import io.reactivex.annotations.SchedulerSupport;

import static io.reactivex.annotations.SchedulerSupport.NONE;

public interface SearchJobsRepository {

    /**
     * Get search result for input fields. This will make an API call to SEEk job search api.
     *
     * @param what      : The keywords for search
     * @param where     : The region for search
     * @param page      : the page no
     * @param pageSize: No of results to be returned per page
     * @return the search results
     */
    @NonNull
    @SchedulerSupport(NONE)
    Single<SearchJobResult> getSearchJobResults(@NonNull String what, @NonNull String where, int page, int pageSize);

}
