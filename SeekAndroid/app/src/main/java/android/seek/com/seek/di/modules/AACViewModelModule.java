package android.seek.com.seek.di.modules;

import android.seek.com.seek.di.ViewModelFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

/**
 * Created by vermas12 on 26/3/19.
 */

@Module
public abstract class AACViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory providesViewModelFactory(ViewModelFactory factory);
}
