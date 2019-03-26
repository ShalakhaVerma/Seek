package android.seek.com.seek.di.components;

import android.app.Application;
import android.seek.com.seek.di.graphs.AppGraph;
import android.seek.com.seek.di.modules.AACViewModelModule;
import android.seek.com.seek.di.modules.ApplicationModule;
import android.seek.com.seek.di.modules.MainActivityModule;
import android.seek.com.seek.di.modules.RepoModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by vermas12 on 26/3/19.
 */

@Singleton
@Component(modules = {ApplicationModule.class, AACViewModelModule.class, RepoModule.class})
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
