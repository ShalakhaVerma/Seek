package com.seek.app.sample.arch.navigation;

import com.seek.app.sample.R;
import com.seek.app.sample.ui.MainActivity;
import com.seek.app.sample.ui.homescreen.HomeScreenFragment;

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
}
