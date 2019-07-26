package com.farzam.rest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.farzam.rest.di.component.DaggerAppComponent;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.AppLogger;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * behnaz on 10/29/2017.
 */

public class App extends Application implements HasActivityInjector {

    public static Context context;
    public static SharedPreferences preferences;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences(AppConstants.PREF_NAME, MODE_PRIVATE);
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        AppLogger.init();

    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

}
