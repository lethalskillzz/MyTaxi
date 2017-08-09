package com.lethalskillzz.mytaxi.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.lethalskillzz.mytaxi.di.ActivityContext;
import com.lethalskillzz.mytaxi.di.PerActivity;
import com.lethalskillzz.mytaxi.presentation.detail.DetailMvpPresenter;
import com.lethalskillzz.mytaxi.presentation.detail.DetailMvpView;
import com.lethalskillzz.mytaxi.presentation.detail.DetailPresenter;
import com.lethalskillzz.mytaxi.presentation.master.MasterAdapter;
import com.lethalskillzz.mytaxi.presentation.master.MasterMvpPresenter;
import com.lethalskillzz.mytaxi.presentation.master.MasterMvpView;
import com.lethalskillzz.mytaxi.presentation.master.MasterPresenter;
import com.lethalskillzz.mytaxi.presentation.splash.SplashMvpPresenter;
import com.lethalskillzz.mytaxi.presentation.splash.SplashMvpView;
import com.lethalskillzz.mytaxi.presentation.splash.SplashPresenter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    MasterMvpPresenter<MasterMvpView> provideMasterPresenter(
            MasterPresenter<MasterMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    DetailMvpPresenter<DetailMvpView> provideDetailPresenter(
            DetailPresenter<DetailMvpView> presenter) {
        return presenter;
    }


    @Provides
    MasterAdapter provideMasterAdapter() {
        return new MasterAdapter(new ArrayList<>(0));
    }


    @Provides
    DividerItemDecoration provideDividerItemDecoration(AppCompatActivity activity) {
        return new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }


}