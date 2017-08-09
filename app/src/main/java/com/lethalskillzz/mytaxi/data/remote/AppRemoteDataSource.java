package com.lethalskillzz.mytaxi.data.remote;

import com.lethalskillzz.mytaxi.data.AppDataSource;
import com.lethalskillzz.mytaxi.data.local.prefs.PreferencesHelper;
import com.lethalskillzz.mytaxi.data.model.Data;
import com.lethalskillzz.mytaxi.utils.AppLogger;
import com.lethalskillzz.mytaxi.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

public class AppRemoteDataSource implements AppDataSource {

    private final AppService mService;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppRemoteDataSource(AppService service, PreferencesHelper preferencesHelper) {
        mService = service;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Observable<Data> getData() {
        return mService
                .loadDataFromServer()
                .compose(RxUtils.applySchedulers())
                .doOnSubscribe(disposable -> AppLogger.d("Sync started..."))
                .doOnError(throwable -> AppLogger.d("Sync failed!"))
                .doOnComplete(() -> AppLogger.d("Sync completed."));
    }


    @Override
    public void saveData(Data data) {
        throw new UnsupportedOperationException("saveData in AppRemoteDataSource is not implemented!");
    }


}
