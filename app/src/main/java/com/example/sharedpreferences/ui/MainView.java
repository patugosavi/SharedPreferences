package com.example.sharedpreferences.ui;


import java.util.List;

public interface MainView {

    void showProgressBar();

    void hideProgressBar();

    void onSuccess(String a);
    void onFailure(String onError);

    void DoLoginOnSuccess(MainModelResponse mainModelResponse);
}
