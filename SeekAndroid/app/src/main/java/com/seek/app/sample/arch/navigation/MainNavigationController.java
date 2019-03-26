package com.seek.app.sample.arch.navigation;

import com.seek.app.sample.model.JobItem;

import java.util.ArrayList;

public interface MainNavigationController {

    void navigateToHomeScreen();

    void navigateToSerpFragment(ArrayList<JobItem> jobItems);
}
