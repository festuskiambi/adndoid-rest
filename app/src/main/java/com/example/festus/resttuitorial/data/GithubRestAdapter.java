package com.example.festus.resttuitorial.data;

import com.example.festus.resttuitorial.datamodel.RepositoryDataModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
public class GithubRestAdapter {

    private final GithubService github;

    @Inject
    public GithubRestAdapter(Retrofit retrofit) {
        this.github = retrofit.create(GithubService.class);
    }

    public interface GithubService {

        @GET(UrlManager.REPOS_URL)
        Flowable<List<RepositoryDataModel>> getUserPublicRepositories(@Path("user") String user);
    }

    public Flowable<List<RepositoryDataModel>> getUserRepositories(String user) {
        return github.getUserPublicRepositories(user);
    }
}
