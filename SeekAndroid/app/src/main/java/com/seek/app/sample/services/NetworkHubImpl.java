package com.seek.app.sample.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seek.app.sample.model.SearchJobResult;
import com.seek.app.sample.model.SearchJobResultResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Singleton
public class NetworkHubImpl implements NetworkHub {

    @NonNull
    private final Gson mGson;
    static final String BASE_URL = "https://jobsearch-api-mobile.cloud.seek.com.au";

    @NonNull
    private final SearchService mSearchService;

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * constructor
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Inject
    public NetworkHubImpl(@NonNull OkHttpClient httpClient) {
        mGson = new GsonBuilder().serializeNulls().create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(mGson);

        // construct an http client to house properties shared across all services
        OkHttpClient commonHttpClient = httpClient.newBuilder()
//                .addInterceptor(new NetworkConnectionInterceptor())
                .build();

        // construct a retrofit instance to house properties shared across all services
        Retrofit commonRetrofit = new Retrofit.Builder()
                .baseUrl("http://example.com")
                .client(commonHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        // construct the search service
        mSearchService = commonRetrofit.newBuilder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(SearchService.class);

    }

    @Override
    public Single<SearchJobResult> getSearchJobResults(String what, String where, int page, int pageSize) {
        return mSearchService.searchSeekJobs(what, where, page, pageSize).map(new Function<SearchJobResultResponse, SearchJobResult>() {
            @Override
            public SearchJobResult apply(SearchJobResultResponse jobSearchResultResponse) throws Exception {
                return SearchJobResult.create(jobSearchResultResponse);
            }
        });
    }


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * search service
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    private interface SearchService {
        @NonNull
        @GET("/search")
        Single<SearchJobResultResponse> searchSeekJobs(@Query("keywords") String keywords, @Query("where")
                String where, @Query("page") int page, @Query("pagesize") int pagesize);
    }
}
