package com.seek.app.sample.arch;


import android.app.Application;

import com.seek.app.sample.di.components.AppComponent;

/**
 * Created by vermas12 on 25/3/19.
 */

public class SeekApplication extends Application {
    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (appComponent == null) {
            appComponent = AppComponent.Factory.create(this);
        }
        appComponent.inject(this);
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
