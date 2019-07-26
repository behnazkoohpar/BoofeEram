package com.farzam.rest.ui.splash;

import com.farzam.rest.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * behnaz on 11/1/17.
 */
@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager) {
        return new SplashViewModel(dataManager);
    }

}
