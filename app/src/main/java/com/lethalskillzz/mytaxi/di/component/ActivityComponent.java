package com.lethalskillzz.mytaxi.di.component;

import com.lethalskillzz.mytaxi.di.PerActivity;
import com.lethalskillzz.mytaxi.di.module.ActivityModule;
import com.lethalskillzz.mytaxi.presentation.detail.DetailActivity;
import com.lethalskillzz.mytaxi.presentation.detail.DetailFragment;
import com.lethalskillzz.mytaxi.presentation.master.MasterActivity;
import com.lethalskillzz.mytaxi.presentation.master.MasterFragment;
import com.lethalskillzz.mytaxi.presentation.splash.SplashActivity;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(MasterActivity activity);

    void inject(DetailActivity activity);

    void inject(MasterFragment fragment);

    void inject(DetailFragment fragment);

}
