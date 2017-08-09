package com.lethalskillzz.mytaxi.presentation.master;

import com.lethalskillzz.mytaxi.data.AppRepository;
import com.lethalskillzz.mytaxi.data.idlingresource.AppIdlingResource;
import com.lethalskillzz.mytaxi.presentation.base.BasePresenter;
import com.lethalskillzz.mytaxi.utils.AppLogger;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */


public class MasterPresenter <V extends MasterMvpView> extends BasePresenter<V>
        implements MasterMvpPresenter<V> {

    private static final String TAG = "MasterPresenter";

    @Inject
    public MasterPresenter(AppRepository appRepository,
                           CompositeDisposable compositeDisposable) {
        super(appRepository, compositeDisposable);
    }


    @Override
    public void loadPlacemarkFromRepo(boolean forcedSync, AppIdlingResource resource) {

        if (forcedSync) {
            getAppRepository().markRepoAsSynced(false);
        }

        getCompositeDisposable().add(getAppRepository()
                .getData()
                .doOnSubscribe(disposable -> {
                    getMvpView().showLoading();
                    if (resource != null) resource.setIdleState(false);
                })
                .subscribe(
                        //OnNext
                        data -> {
                            getAppRepository().markRepoAsSynced(true);
                            getMvpView().hideLoading();
                            getMvpView().showPlacemarksList(data.placemarks());
                            if (resource != null) resource.setIdleState(true);
                        },
                        // OnError
                        throwable -> {
                            getMvpView().hideLoading();
                            getMvpView().refreshPlacemark(false);

                            AppLogger.d(throwable.getMessage());
                            if (throwable instanceof HttpException) {
                                HttpException e = (HttpException) throwable;
                                handleApiError(e);
                            }

                            getAppRepository().markRepoAsSynced(false);
                        }));
    }



}