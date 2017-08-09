package com.lethalskillzz.mytaxi.presentation.master;


import com.lethalskillzz.mytaxi.data.idlingresource.AppIdlingResource;
import com.lethalskillzz.mytaxi.di.PerActivity;
import com.lethalskillzz.mytaxi.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

@PerActivity
public interface MasterMvpPresenter <V extends MasterMvpView> extends MvpPresenter<V> {

    void loadPlacemarkFromRepo(boolean forcedSync, AppIdlingResource resource);

}