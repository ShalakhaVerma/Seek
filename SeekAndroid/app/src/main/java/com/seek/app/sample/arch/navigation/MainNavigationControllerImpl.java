package com.seek.app.sample.arch.navigation;

import android.content.Intent;
import android.net.Uri;

import com.seek.app.sample.R;
import com.seek.app.sample.model.JobItem;
import com.seek.app.sample.ui.MainActivity;
import com.seek.app.sample.ui.homescreen.HomeScreenFragment;
import com.seek.app.sample.ui.serp.SerpFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

public class MainNavigationControllerImpl implements MainNavigationController {
    MainActivity mainActivity;

    @Inject
    public MainNavigationControllerImpl(@NonNull MainActivity activity) {
        mainActivity = activity;
    }

    @Override
    public void navigateToHomeScreen() {
        final FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, HomeScreenFragment.newInsatnce(), HomeScreenFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void navigateToSerpFragment(ArrayList<JobItem> jobItems) {
        final FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_layout, SerpFragment.newInstance(jobItems), SerpFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void navigateToBrowser(String urlString) {
        mainActivity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(urlString)));
    }
}
