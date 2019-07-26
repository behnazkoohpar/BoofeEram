package com.farzam.rest.di.component;

import android.app.Application;

import com.farzam.rest.App;
import com.farzam.rest.di.builder.ActivityBuilder;
import com.farzam.rest.di.module.AppModule;
import com.farzam.rest.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * behnaz on 10/29/2017.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class , AppModule.class, ActivityBuilder.class ,  NetworkModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(App app);

}
