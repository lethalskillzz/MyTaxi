package com.lethalskillzz.mytaxi.presentation.detail;

import team.chronus.amona.di.PerActivity;
import team.chronus.amona.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

@PerActivity
public interface DetailMvpPresenter <V extends DetailMvpView> extends MvpPresenter<V> {

}