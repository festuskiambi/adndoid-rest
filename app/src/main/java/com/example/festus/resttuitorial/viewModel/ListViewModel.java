package com.example.festus.resttuitorial.viewModel;

import java.util.List;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
public class ListViewModel {

    final boolean hasError;
    final boolean isLoading;
    final String errorMessage;
    private List<RepositoryListItem> repoList;

    private ListViewModel(boolean hasError,
                          boolean isLoading,
                          String errorMessage,
                          List<RepositoryListItem> repoList) {
        this.hasError = hasError;
        this.isLoading = isLoading;
        this.errorMessage = errorMessage;
        this.repoList = repoList;
    }

    public boolean hasError() {
        return hasError;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<RepositoryListItem> getRepoList() {
        return repoList;
    }

    public static ListViewModel loading(){
        return new ListViewModel(false, true, null, null);
    }

    public static ListViewModel success(List<RepositoryListItem> repoList){
        return new ListViewModel(false, false, null, repoList);
    }

    public static ListViewModel error(String errorMessage){
        return new ListViewModel(true, false, errorMessage, null);
    }

}
