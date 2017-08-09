package com.lethalskillzz.mytaxi.presentation.splash;

import android.os.SystemClock;

import com.lethalskillzz.mytaxi.data.AppRepository;
import com.lethalskillzz.mytaxi.presentation.base.BasePresenter;
import com.lethalskillzz.mytaxi.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    private static final String TAG = "SplashPresenter";

    // Splash screen timeout
    private static int SPLASH_TIME_OUT = 3000;

    @Inject
    public SplashPresenter(AppRepository appRepository,
                           CompositeDisposable compositeDisposable) {
        super(appRepository, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);


        getCompositeDisposable().add(Observable.create(emitter -> {
            SystemClock.sleep(SPLASH_TIME_OUT); // simulate delay
            emitter.onNext(5);
            emitter.onComplete();
        }).compose(RxUtils.applySchedulers()).subscribe(integer ->
                getMvpView().openMasterActivity()));

    }


}
