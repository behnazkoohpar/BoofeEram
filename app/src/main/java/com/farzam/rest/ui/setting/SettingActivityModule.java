package com.farzam.rest.ui.setting;

import com.farzam.rest.data.DataManager;

import dagger.Module;
import dagger.Provides;
@Module
public class SettingActivityModule {
    @Provides
    SettingViewModel provideSettingViewModel(DataManager dataManager) {
        return new SettingViewModel(dataManager);
    }
}
