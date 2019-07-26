package com.farzam.rest.ui.main;

import com.farzam.rest.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cmos on 05/04/2018.
 */
@Module
public class MainActivityModule {
    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager) {
        return new MainViewModel(dataManager);
    }
}
