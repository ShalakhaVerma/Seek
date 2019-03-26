package android.seek.com.seek.arch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * Created by vermas12 on 25/3/19.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Subscribe to observables.
        onSubscribeObservableData();
    }

    protected abstract void onSubscribeObservableData();

}
