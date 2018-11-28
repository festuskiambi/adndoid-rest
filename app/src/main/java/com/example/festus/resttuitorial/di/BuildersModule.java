package com.example.festus.resttuitorial.di;

import android.app.Activity;

import com.example.festus.resttuitorial.reposlist.ListActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by Festus Kiambi on 11/24/18.
 */

@Module
public abstract class BuildersModule {
    //Add Bindings to Sub-Components here
    @Binds
    @IntoMap
    @ActivityKey(ListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindListActivityInjectorFactory(RepositoryListSubComponent.Builder builder);

}
