package com.farzam.rest.ui.login;

import com.farzam.rest.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * behnaz on 11/3/17.
 */
@Module
public class LoginActivityModule {

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager) {
        return new LoginViewModel(dataManager);
    }

}
