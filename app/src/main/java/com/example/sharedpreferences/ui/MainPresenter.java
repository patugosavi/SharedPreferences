package com.example.sharedpreferences.ui;

import android.util.Log;

import com.example.sharedpreferences.NetworkService.ApiInterface;
import com.example.sharedpreferences.utils.ErrorUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private static final String TAG = "MainPresenter";
    private MainView mainView;
    private ApiInterface apiInterface;
    private Disposable disposable;

    public MainPresenter(MainView mainView, ApiInterface apiInterface) {
        this.mainView = mainView;
        this.apiInterface = apiInterface;
    }

    public void dispose() {

        if (disposable != null)
            disposable.dispose();
    }


    public void doLogin(String username, String password) {

        Observable<MainModelResponse> observable = apiInterface.getLogin(username, password);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainModelResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(MainModelResponse mainModelResponse) {
                        Log.d(TAG, "onNext: " + mainModelResponse);
                        if (mainView != null)
                            mainView.DoLoginOnSuccess(mainModelResponse);
                    }


                    @Override
                    public void onError(Throwable e) {
                        if (null != mainView) {
                            mainView.onFailure(ErrorUtil.onError(e));
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
