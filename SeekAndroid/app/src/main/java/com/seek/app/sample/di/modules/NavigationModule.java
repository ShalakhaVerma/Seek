package com.seek.app.sample.di.modules;

import com.seek.app.sample.arch.navigation.MainNavigationController;
import com.seek.app.sample.arch.navigation.MainNavigationControllerImpl;
import com.seek.app.sample.di.scopes.ActivityContext;
import com.seek.app.sample.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vermas12 on 26/3/19.
 */

@Module
public class NavigationModule {

    @ActivityContext
    @Provides
    MainNavigationController providesMainNavigationController(MainActivity activity) {
        return new MainNavigationControllerImpl(activity);
    }
}
