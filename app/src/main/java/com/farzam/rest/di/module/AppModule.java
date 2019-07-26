package com.farzam.rest.di.module;

import android.app.Application;
import android.content.Context;

import com.farzam.rest.data.AppDataManager;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.data.pref.AppPreferencesHelper;
import com.farzam.rest.data.pref.PreferencesHelper;
import com.farzam.rest.di.PreferenceInfo;
import com.farzam.rest.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * behnaz on 10/29/2017.
 */

@Module
public class AppModule implements AppConstants {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

}
