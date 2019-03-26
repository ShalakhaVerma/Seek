package com.seek.app.sample.ui.serp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seek.app.sample.R;
import com.seek.app.sample.arch.BaseFragment;
import com.seek.app.sample.di.ViewModelFactory;
import com.seek.app.sample.model.JobItem;
import com.seek.app.sample.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SerpFragment extends BaseFragment {

    public static final String TAG = "SerpFragment";

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private SerpAdapter adapter;
    @Inject
    ViewModelFactory viewModelFactory;

    private SerpViewModel serpViewModel;

    public static SerpFragment newInstance(ArrayList<JobItem> jobItems) {
        SerpFragment fragment = new SerpFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.KEY_JOB_ITEMS, jobItems);// some issue with auto value parcelable extension
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serpViewModel = ViewModelProviders.of(this, viewModelFactory).get(SerpViewModelImpl.class);

        adapter = new SerpAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.serp_fragment, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            ArrayList<JobItem> jobItems = new ArrayList<>();
            jobItems = getArguments().getParcelableArrayList(Constants.KEY_JOB_ITEMS);
            serpViewModel.configureItems(jobItems);
        }
    }

    @Override
    protected void onSubscribeObservableData() {

        serpViewModel.getSearchResultObservable().observe(this.getViewLifecycleOwner(), new Observer<List<JobItem>>() {
            @Override
            public void onChanged(List<JobItem> jobItems) {
                if (jobItems != null && jobItems.size() > 0)
                    adapter.setJobItems(jobItems);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (serpViewModel == null) return;
        serpViewModel.getSearchResultObservable().removeObservers(this);
    }

}
