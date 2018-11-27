package com.example.festus.resttuitorial.reposlist;

/**
 * Created by Festus Kiambi on 11/24/18.
 */
public interface ViewInterface {

    void setUpAdapterAndView(List<RepositoryListItem> listOfData);

    void showErrorMessage(String error);

    void startMainActivity();

    void showLoadingIndicator();
}
