package com.seek.app.sample.di.modules;

import com.seek.app.sample.services.NetworkHub;
import com.seek.app.sample.services.NetworkHubImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public abstract class ServicesModule {

    @Provides
    @Singleton
    static NetworkHub providesNetworkHub(NetworkHubImpl hub) {
        return hub;
    }

    @Provides
    static OkHttpClient providesOkHttpClient() {
        return new OkHttpClient();
    }
}
