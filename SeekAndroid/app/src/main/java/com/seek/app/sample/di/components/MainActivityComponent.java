package com.seek.app.sample.di.components;

/**
 * Created by vermas12 on 26/3/19.
 */

import com.seek.app.sample.arch.SeekApplication;
import com.seek.app.sample.arch.navigation.MainNavigationController;
import com.seek.app.sample.di.modules.MainActivityModule;
import com.seek.app.sample.di.modules.NavigationModule;
import com.seek.app.sample.di.scopes.ActivityContext;
import com.seek.app.sample.ui.MainActivity;
import com.seek.app.sample.ui.homescreen.HomeScreenFragment;
import com.seek.app.sample.ui.serp.SerpFragment;

import dagger.Subcomponent;

@ActivityContext
@Subcomponent(modules = {MainActivityModule.class, NavigationModule.class})
public interface MainActivityComponent {

    void inject(HomeScreenFragment f);

    void inject(SerpFragment f);


    MainNavigationController getNavigationController();

    final class Initializer {
        private Initializer() {
        } // No instances.

        public static MainActivityComponent init(MainActivity activity) {
            AppComponent appComponent = ((SeekApplication) activity.getApplicationContext()).getComponent();
            if (appComponent != null) {
                return appComponent.plus(new MainActivityModule(activity));
            }

            throw new IllegalArgumentException("Application must provide AppComponent");
        }
    }
}
