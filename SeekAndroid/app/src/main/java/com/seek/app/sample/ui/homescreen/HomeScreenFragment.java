package com.seek.app.sample.ui.homescreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.seek.app.sample.R;
import com.seek.app.sample.arch.BaseFragment;
import com.seek.app.sample.arch.navigation.MainNavigationController;
import com.seek.app.sample.di.ViewModelFactory;
import com.seek.app.sample.model.JobItem;
import com.seek.app.sample.ui.MainActivity;
import com.seek.app.sample.utils.UiUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by vermas12 on 25/3/19.
 */

public class HomeScreenFragment extends BaseFragment {
    public static final String TAG = "HomeScreenFragment";

    @BindView(R.id.edittext_what)
    EditText whatEditText;
    @BindView(R.id.edittext_where)
    EditText whereEditText;
    @BindView(R.id.button_seek)
    Button seekButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    MainNavigationController navigationController;

    private HomeScreenViewModel homeScreenViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getComponent().inject(this);
        super.onCreate(savedInstanceState);

        homeScreenViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeScreenViewModelImpl.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @OnClick(R.id.button_seek)
    void onSeekJobClick() {
        homeScreenViewModel.seekJobs(whatEditText.getText().toString(), whereEditText.getText().toString());
    }

    @OnTextChanged(R.id.edittext_what)
    void onWhatTextChange() {
        homeScreenViewModel.onWhatChange(whatEditText.getText().toString());
    }

    @OnTextChanged(R.id.edittext_where)
    void onWhereTextChange() {
        homeScreenViewModel.onWhereChange(whereEditText.getText().toString());
    }

    @Override
    protected void onSubscribeObservableData() {

        homeScreenViewModel.getLoadingStateObservable().observe(this.getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer != null) {
                    switch (integer) {
                        case HomeScreenViewModel.LOADING_STATE_INPROGRESS:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case HomeScreenViewModel.LOADING_STATE_INACTIVE:
                        default:
                            progressBar.setVisibility(View.GONE);
                            break;
                    }
                }
            }
        });

        homeScreenViewModel.isSeekJobEnabled().observe(this.getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean != null) {
                    seekButton.setEnabled(aBoolean);
                }
            }
        });

        homeScreenViewModel.getSearchResultObservable().observe(this.getViewLifecycleOwner(), new Observer<ArrayList<JobItem>>() {
            @Override
            public void onChanged(ArrayList<JobItem> searchJobResult) {
                if (searchJobResult != null) {
                    Log.w("List size", searchJobResult.size() + "");
                    if (searchJobResult.size() > 0) {
                        UiUtils.hideKeyBoard(whereEditText);
                        navigationController.navigateToSerpFragment(searchJobResult);
                    } else
                        Snackbar.make(seekButton, getString(R.string.no_search_results), BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Fragment newInsatnce() {
        return new HomeScreenFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (homeScreenViewModel == null) return;
        homeScreenViewModel.getLoadingStateObservable().removeObservers(this);
        homeScreenViewModel.getSearchResultObservable().removeObservers(this);
        homeScreenViewModel.isSeekJobEnabled().removeObservers(this);
    }
}
