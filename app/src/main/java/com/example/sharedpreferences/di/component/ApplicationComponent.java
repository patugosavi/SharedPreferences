package com.example.sharedpreferences.di.component;


import android.content.SharedPreferences;

import com.example.sharedpreferences.NetworkService.ApiInterface;
import com.example.sharedpreferences.di.module.RetrofitModule;
import com.example.sharedpreferences.di.module.SharedPrefModule;
import com.example.sharedpreferences.di.scope.ApplicationScope;
import com.example.sharedpreferences.ui.MainActivity;
import com.example.sharedpreferences.ui.TestActivity;

import dagger.Component;

@ApplicationScope
@Component(modules = {RetrofitModule.class, SharedPrefModule.class})
public interface ApplicationComponent {

    ApiInterface getNetworkService();

    SharedPreferences sharedPrefences();

    void inject(MainActivity mainActivity);

    void inject(TestActivity testActivity);


}
