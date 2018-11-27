package com.example.festus.resttuitorial.data;

import com.example.festus.resttuitorial.datamodel.RepositoryDataModel;
import com.example.festus.resttuitorial.viewmodel.ListViewModel;
import com.example.festus.resttuitorial.viewmodel.RepositoryListItem;

import org.reactivestreams.Publisher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
public class RepositoryDataSourceImpl implements RepositoryDataSourceInterface {

    private GithubRestAdapter restAdapter;

    @Inject
    public RepositoryDataSourceImpl(GithubRestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    @Override
    public Flowable<ListViewModel> getUserRepositories(String user) {
        return
                restAdapter.getUserRepositories(user)
                        .flatMap(
                                new Function<List<RepositoryDataModel>, Publisher<ListViewModel>>() {
                                    @Override
                                    public Publisher<ListViewModel> apply(List<RepositoryDataModel> repositoryDataModels) throws Exception {
                                        List<RepositoryListItem> listItems = new ArrayList<>();

                                        if (repositoryDataModels.size() == 0) {
                                            throw new IOException("Empty data set");
                                        }

                                        for (RepositoryDataModel repo : repositoryDataModels) {
                                            listItems.add(
                                                    new RepositoryListItem(
                                                            repo.getDescription(),
                                                            repo.getCreated_at(),
                                                            repo.getAvatarUrl()
                                                    )
                                            );
                                        }
                                        return Flowable.just(ListViewModel.success(listItems));

                                    }
                                }
                        ).subscribeOn(Schedulers.io());

    }
}
