package com.farzam.rest.di.builder;

import com.farzam.rest.ui.listFactor.ListFactorActivity;
import com.farzam.rest.ui.listFactor.ListFactorActivityModule;
import com.farzam.rest.ui.login.LoginActivity;
import com.farzam.rest.ui.login.LoginActivityModule;
import com.farzam.rest.ui.main.MainActivity;
import com.farzam.rest.ui.main.MainActivityModule;
import com.farzam.rest.ui.setting.SettingActivity;
import com.farzam.rest.ui.setting.SettingActivityModule;
import com.farzam.rest.ui.splash.SplashActivity;
import com.farzam.rest.ui.splash.SplashActivityModule;
import com.farzam.rest.ui.start.StartActivity;
import com.farzam.rest.ui.start.StartActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = StartActivityModule.class)
    abstract StartActivity bindStartActivity();

    @ContributesAndroidInjector(modules = SettingActivityModule.class)
    abstract SettingActivity bindSettingActivity();


  @ContributesAndroidInjector(modules = ListFactorActivityModule.class)
    abstract ListFactorActivity bindListFactorActivity();


}
