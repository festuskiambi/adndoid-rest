package com.example.festus.resttuitorial.di;

import android.content.Context;

import com.example.festus.resttuitorial.RestTuitorialApplication;
import com.example.festus.resttuitorial.util.BaseSchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Festus Kiambi on 11/24/18.
 */
@Module(subcomponents = {RepositoryListSubModuleComponent.class})
public class ApplicationModule {

    private static final String REPOS = "/users/{user}/repos";

    @Provides
    Context provideContext(RestTuitorialApplication application){
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    RepositoryDataSourceInterface provideDataSource(GitHubRestAdapter adapter){
        return new RepositoryDataSourceImpl(adapter);
    }

    @Provides
    @Singleton
    GitHubRestAdapter provideRestAdapter(Retrofit retrofit){
        return new GitHubRestAdapter(retrofit);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        OkHttpClient client =
                new OkHttpClient.Builder()
                        .addInterceptor(
                                new ErrorInterceptor()
                        )
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(UrlManager.API_HOST)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    BaseSchedulerProvider providerScheduler(){
        return new SchedulerProvider();
    }

}