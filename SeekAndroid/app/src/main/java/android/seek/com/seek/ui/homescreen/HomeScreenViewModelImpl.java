package android.seek.com.seek.ui.homescreen;


import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by vermas12 on 24/3/19.
 */

public class HomeScreenViewModelImpl extends ViewModel implements HomeScreenViewModel {

    @NonNull
    private final MutableLiveData<Boolean> seekJobsEnableObservable;
    @NonNull
    private final MutableLiveData<Integer> loadingStateObservable;

    @Inject
    HomeScreenViewModelImpl() {
        seekJobsEnableObservable = new MutableLiveData<>();
        loadingStateObservable = new MutableLiveData<>();
        loadingStateObservable.setValue(LOADING_STATE_INACTIVE);
    }


    @Override
    public void onWhatChange(String what) {
    }

    @Override
    public void onWhereChange(String where) {
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
    public void seekJobs(String what, String where) {
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
