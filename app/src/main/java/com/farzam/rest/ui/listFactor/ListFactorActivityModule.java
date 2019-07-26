package com.farzam.rest.ui.listFactor;

import com.farzam.rest.data.DataManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ListFactorActivityModule {
    @Provides
    ListFactorViewModel provideListFactorViewModel(DataManager dataManager) {
        return new ListFactorViewModel(dataManager);
    }
}
