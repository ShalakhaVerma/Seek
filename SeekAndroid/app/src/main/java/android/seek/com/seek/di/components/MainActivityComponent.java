package android.seek.com.seek.di.components;

/**
 * Created by vermas12 on 26/3/19.
 */

import android.seek.com.seek.arch.SeekApplication;
import android.seek.com.seek.di.modules.MainActivityModule;
import android.seek.com.seek.di.modules.NavigationModule;
import android.seek.com.seek.di.scopes.ActivityContext;
import android.seek.com.seek.ui.MainActivity;
import android.seek.com.seek.ui.homescreen.HomeScreenFragment;

import dagger.Subcomponent;

@ActivityContext
@Subcomponent(modules = {MainActivityModule.class, NavigationModule.class})
public interface MainActivityComponent {

    void inject(HomeScreenFragment f);

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
