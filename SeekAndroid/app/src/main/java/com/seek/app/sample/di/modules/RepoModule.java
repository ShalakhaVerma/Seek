package com.seek.app.sample.di.modules;

import com.seek.app.sample.repository.SearchJobsRepository;
import com.seek.app.sample.repository.SearchJobsRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by vermas12 on 26/3/19.
 */

@Module
public interface RepoModule {

    @Binds
    @Singleton
    SearchJobsRepository bindsSearchJobsRepository(SearchJobsRepositoryImpl repo);


}

