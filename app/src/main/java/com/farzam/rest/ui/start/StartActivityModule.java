package com.farzam.rest.ui.start;

import com.farzam.rest.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cmos on 07/09/2018.
 */
@Module
public class StartActivityModule {

    @Provides
    StartViewModel provideStartViewModel(DataManager dataManager) {
        return new StartViewModel(dataManager);
    }
}
