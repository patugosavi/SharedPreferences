package com.example.sharedpreferences.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sharedpreferences.di.qualifier.ApplicationContext;
import com.example.sharedpreferences.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;


@Module(includes = ContextModule.class)
public class SharedPrefModule {

    @Provides
    @ApplicationScope
    SharedPreferences sharedPreferences(@ApplicationContext Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
