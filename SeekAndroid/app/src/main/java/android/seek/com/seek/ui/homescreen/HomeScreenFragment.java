package android.seek.com.seek.ui.homescreen;

import android.os.Bundle;
import android.seek.com.seek.arch.BaseFragment;
import android.seek.com.seek.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by vermas12 on 25/3/19.
 */

public class HomeScreenFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void onSubscribeObservableData() {

    }
}
