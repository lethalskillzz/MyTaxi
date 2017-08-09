package com.lethalskillzz.mytaxi.presentation.detail;

import com.lethalskillzz.mytaxi.data.AppRepository;
import com.lethalskillzz.mytaxi.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

public class DetailPresenter <V extends DetailMvpView> extends BasePresenter<V>
        implements DetailMvpPresenter<V> {

    private static final String TAG = "DetailPresenter";


    @Inject
    public DetailPresenter(AppRepository appRepository,
                           CompositeDisposable compositeDisposable) {
        super(appRepository, compositeDisposable);
    }


}