package com.seek.app.sample.di.components;


import android.app.Application;

import com.seek.app.sample.di.graphs.AppGraph;
import com.seek.app.sample.di.modules.AACViewModelModule;
import com.seek.app.sample.di.modules.ApplicationModule;
import com.seek.app.sample.di.modules.MainActivityModule;
import com.seek.app.sample.di.modules.RepoModule;
import com.seek.app.sample.di.modules.ServicesModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by vermas12 on 26/3/19.
 */

@Singleton
@Component(modules = {ApplicationModule.class, AACViewModelModule.class, RepoModule.class, ServicesModule.class})
public interface AppComponent extends AppGraph {

    MainActivityComponent plus(MainActivityModule module);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    final class Factory {

        private Factory() {
        }//No instances

        public static AppComponent create(Application application) {
            return DaggerAppComponent.builder().application(application).build();
        }
    }

}
