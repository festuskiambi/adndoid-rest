package com.example.festus.resttuitorial.di;

import com.example.festus.resttuitorial.reposlist.ListActivity;
import com.example.festus.resttuitorial.reposlist.ViewInterface;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Festus Kiambi on 11/24/18.
 */
@Module
public abstract class RepositoryListModule {

    @Binds
    abstract ViewInterface provideFeatureView(ListActivity listActivity);
}
