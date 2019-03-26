package com.seek.app.sample.di.modules;
import com.seek.app.sample.di.scopes.ActivityContext;
import com.seek.app.sample.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vermas12 on 26/3/19.
 */

@Module
public class MainActivityModule {

    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @Provides
    MainActivity provideMainActivity() {
        return this.activity;
    }
}
