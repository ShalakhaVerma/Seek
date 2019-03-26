package android.seek.com.seek.ui.homescreen;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

/**
 * Created by vermas12 on 25/3/19.
 */

public interface HomeScreenViewModel {

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * state
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @NonNull
    LiveData<Boolean> isSeekJobEnabled();

    @NonNull
    LiveData<Integer> getLoadingStateObservable();


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * actions
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    void seekJobs(@Nullable String what, @Nullable String where);

    void onWhatChange(@Nullable String what);

    void onWhereChange(@Nullable String where);


    int LOADING_STATE_INACTIVE = 0;
    int LOADING_STATE_INPROGRESS = 1;


    @IntDef({
            LOADING_STATE_INACTIVE,
            LOADING_STATE_INPROGRESS
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface LoadingState {
    }

}
