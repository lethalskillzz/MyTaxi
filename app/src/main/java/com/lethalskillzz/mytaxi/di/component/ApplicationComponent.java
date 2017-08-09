package com.lethalskillzz.mytaxi.di.component;

import android.app.Application;
import android.content.Context;

import com.lethalskillzz.mytaxi.App;
import com.lethalskillzz.mytaxi.data.AppRepository;
import com.lethalskillzz.mytaxi.di.ApplicationContext;
import com.lethalskillzz.mytaxi.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    AppRepository getAppRepository();
}