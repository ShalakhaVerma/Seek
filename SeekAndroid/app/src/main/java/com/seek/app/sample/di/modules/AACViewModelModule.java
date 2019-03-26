package com.seek.app.sample.di.modules;

import com.seek.app.sample.di.ViewModelFactory;
import com.seek.app.sample.ui.homescreen.HomeScreenViewModelImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by vermas12 on 26/3/19.
 */

@Module
public abstract class AACViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }


    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenViewModelImpl.class)
    abstract ViewModel bindsHomeScreenViewModel(HomeScreenViewModelImpl model);
}
