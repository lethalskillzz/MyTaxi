package com.lethalskillzz.mytaxi.data.local;

import com.lethalskillzz.mytaxi.data.AppDataSource;
import com.lethalskillzz.mytaxi.data.local.db.PlacemarkPersistenceContract.PlacemarkEntry;
import com.lethalskillzz.mytaxi.data.model.Data;
import com.lethalskillzz.mytaxi.data.model.Placemark;
import com.lethalskillzz.mytaxi.utils.DbUtils;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Observable;


/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@Singleton
public class AppLocalDataSource implements AppDataSource {

    private final BriteDatabase mDatabaseHelper;

    @Inject
    public AppLocalDataSource(BriteDatabase briteDatabase) {
        mDatabaseHelper = briteDatabase;
    }


    @Override
    public Observable<Data> getData() {

        rx.Observable<Data> listObservable = mDatabaseHelper
                .createQuery(PlacemarkEntry.TABLE_NAME, DbUtils.getSelectAllQuery(PlacemarkEntry.TABLE_NAME))
                .mapToOne(DbUtils::placemarksFromCursor);

        return RxJavaInterop.toV2Observable(listObservable);
    }


    @Override
    public void saveData(Data data) {
        BriteDatabase.Transaction transaction = mDatabaseHelper.newTransaction();

        try {
            deleteAllData();
            for (Placemark placemark : data.placemarks()) {
                mDatabaseHelper.insert(PlacemarkEntry.TABLE_NAME,
                        DbUtils.placemarkToContentValues(placemark));
            }
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

    private void deleteAllData() {
        mDatabaseHelper.delete(PlacemarkEntry.TABLE_NAME, null);
    }

}