package com.example.festus.resttuitorial.data;

import com.example.festus.resttuitorial.viewmodel.ListViewModel;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
public interface RepositoryDataSourceInterface {

    Flowable <ListViewModel> getUserRepositories(String user);
}
