package com.example.festus.resttuitorial.di;

import com.example.festus.resttuitorial.reposlist.ListActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Festus Kiambi on 11/24/18.
 */

@Subcomponent(modules = RepositoryListModule.class)
public interface RepositoryListSubComponent extends AndroidInjector<ListActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ListActivity> {
    }
}
