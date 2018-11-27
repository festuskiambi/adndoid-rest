package com.example.festus.resttuitorial.di;

import com.example.festus.resttuitorial.RestTuitorialApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Festus Kiambi on 11/24/18.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        BuildersModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(RestTuitorialApplication application);

        ApplicationComponent build();
    }

    void inject(RestTuitorialApplication application);
}
