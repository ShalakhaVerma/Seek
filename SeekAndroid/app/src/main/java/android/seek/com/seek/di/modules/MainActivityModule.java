package android.seek.com.seek.di.modules;

import android.seek.com.seek.di.scopes.ActivityContext;
import android.seek.com.seek.ui.MainActivity;

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
