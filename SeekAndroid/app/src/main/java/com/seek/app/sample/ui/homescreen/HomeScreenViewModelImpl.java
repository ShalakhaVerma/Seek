package com.seek.app.sample.ui.homescreen;


import com.seek.app.sample.model.JobItem;
import com.seek.app.sample.model.SearchJobResult;
import com.seek.app.sample.repository.SearchJobsRepository;
import com.seek.app.sample.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vermas12 on 25/3/19.
 */

public class HomeScreenViewModelImpl extends ViewModel implements HomeScreenViewModel {

    @NonNull
    SearchJobsRepository searchJobsRepository;

    private String what, where;

    @NonNull
    private final MutableLiveData<Boolean> seekJobsEnableObservable;
    @NonNull
    private final MutableLiveData<Integer> loadingStateObservable;
    @NonNull
    private final MutableLiveData<List<JobItem>> searchResultObserbale;

    private Disposable searchJobsDisposable = null;

    @Inject
    HomeScreenViewModelImpl(@NonNull SearchJobsRepository searchJobsRepository) {
        this.searchJobsRepository = searchJobsRepository;
        seekJobsEnableObservable = new MutableLiveData<>();
        loadingStateObservable = new MutableLiveData<>();
        searchResultObserbale = new MutableLiveData<>();

        loadingStateObservable.setValue(LOADING_STATE_INACTIVE);
    }


    @Override
    public void onWhatChange(String what) {
        this.what = what;
        seekJobsEnableObservable.setValue(!StringUtils.isEmpty(this.what) && !StringUtils.isEmpty(this.where));
    }

    @Override
    public void onWhereChange(String where) {
        this.where = where;
        seekJobsEnableObservable.setValue(!StringUtils.isEmpty(this.what) && !StringUtils.isEmpty(this.where));
    }

    @Override
    public LiveData<Boolean> isSeekJobEnabled() {
        return seekJobsEnableObservable;
    }

    @Override
    public LiveData<Integer> getLoadingStateObservable() {
        return loadingStateObservable;
    }


    @Override
    public LiveData<List<JobItem>> getSearchResultObservable() {
        return searchResultObserbale;
    }

    @Override
    public void seekJobs(String what, String where) {
        searchJobsRepository.getSearchJobResults(what, where, 1, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SearchJobResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        searchJobsDisposable = d;
                        loadingStateObservable.setValue(LOADING_STATE_INPROGRESS);
                    }

                    @Override
                    public void onSuccess(SearchJobResult searchJobResult) {
                        loadingStateObservable.setValue(LOADING_STATE_INACTIVE);
                        searchResultObserbale.setValue(searchJobResult.jobItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        searchJobsDisposable = null;
                        loadingStateObservable.setValue(LOADING_STATE_INACTIVE);
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (searchJobsDisposable != null) {
            searchJobsDisposable.dispose();
            searchJobsDisposable = null;
        }
    }
}
