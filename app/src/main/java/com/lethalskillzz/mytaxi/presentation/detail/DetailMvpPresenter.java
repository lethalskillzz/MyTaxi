package com.lethalskillzz.mytaxi.presentation.detail;


import com.lethalskillzz.mytaxi.di.PerActivity;
import com.lethalskillzz.mytaxi.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

@PerActivity
public interface DetailMvpPresenter <V extends DetailMvpView> extends MvpPresenter<V> {

}