package com.lethalskillzz.mytaxi.data;

import android.content.Context;

import com.lethalskillzz.mytaxi.data.local.prefs.PreferencesHelper;
import com.lethalskillzz.mytaxi.data.model.Data;
import com.lethalskillzz.mytaxi.di.ApplicationContext;
import com.lethalskillzz.mytaxi.di.Local;
import com.lethalskillzz.mytaxi.di.Remote;
import com.lethalskillzz.mytaxi.utils.RxUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@Singleton
public class AppRepository implements AppDataSource {

    private final Context mContext;
    private final AppDataSource mAppRemoteDataSource;
    private final AppDataSource mAppLocalDataSource;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppRepository(
            @ApplicationContext Context context,
            @Remote AppDataSource appRemoteDataSource,
            @Local AppDataSource appLocalDataSource,
            PreferencesHelper preferencesHelper) {

        mContext = context;
        mAppRemoteDataSource = appRemoteDataSource;
        mAppLocalDataSource = appLocalDataSource;
        mPreferencesHelper = preferencesHelper;
    }


    @Override
    public Observable<Data> getData() {

        if (!mPreferencesHelper.isSynced()) {
            return mAppRemoteDataSource
                    .getData()
                    .compose(RxUtils.applySchedulers())
                    .doOnNext(mAppLocalDataSource::saveData);
        } else {
            return mAppLocalDataSource
                    .getData()
                    .compose(RxUtils.applySchedulers());
        }
    }


    @Override
    public void saveData(Data data) {
        mAppLocalDataSource.saveData(data);
    }


    public void markRepoAsSynced(boolean synced) {
        mPreferencesHelper.setIsSynced(synced);
    }

}
