package com.example.festus.resttuitorial.reposlist;

import android.util.Log;

import com.example.festus.resttuitorial.data.RepositoryDataSourceInterface;
import com.example.festus.resttuitorial.util.BaseSchedulerProvider;
import com.example.festus.resttuitorial.viewmodel.ListViewModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
public class ListPresenter {

    private ViewInterface view;
    private RepositoryDataSourceInterface dataSource;
    private CompositeDisposable disposables;
    private BaseSchedulerProvider scheduler;

    @Inject
    public ListPresenter(ViewInterface view,
                         RepositoryDataSourceInterface dataSource,
                         BaseSchedulerProvider scheduler) {
        this.view = view;
        this.dataSource = dataSource;
        this.disposables = new CompositeDisposable();
        this.scheduler = scheduler;
    }

    public void start(String user) {
        getListFromDataSource(user);
    }

    public void stop() {
        disposables.clear();
    }


    private void getListFromDataSource(String user) {
        disposables.add(
                dataSource.getUserRepositories(user)
                        .observeOn(scheduler.getUiScheduler())
                        .startWith(
                                ListViewModel.loading()
                        )
                        .onErrorReturn(
                                //handle exceptions which occur outside of the response object from retrofit
                                new Function<Throwable, ListViewModel>() {
                                    @Override
                                    public ListViewModel apply(Throwable throwable) throws Exception {

                                        return ListViewModel.error(throwable.getMessage());
                                    }
                                }
                        )
                        .subscribeWith(new DisposableSubscriber<ListViewModel>() {
                            @Override
                            public void onNext(ListViewModel uiModel) {
                                if (uiModel.hasError()) {
                                    view.showErrorMessage(uiModel.getErrorMessage());
                                    view.startMainActivity();
                                } else if (uiModel.isLoading()) {
                                    view.showLoadingIndicator();
                                } else {
                                    view.setUpAdapterAndView(uiModel.getRepoList());
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.d("ERROR", t.getMessage() + " " + t.getLocalizedMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        })

        );


    }


}
