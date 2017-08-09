package com.lethalskillzz.mytaxi.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lethalskillzz.mytaxi.BuildConfig;
import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.data.AppDataSource;
import com.lethalskillzz.mytaxi.data.local.AppLocalDataSource;
import com.lethalskillzz.mytaxi.data.local.db.DbHelper;
import com.lethalskillzz.mytaxi.data.local.prefs.PreferencesHelper;
import com.lethalskillzz.mytaxi.data.remote.AppRemoteDataSource;
import com.lethalskillzz.mytaxi.data.remote.AppService;
import com.lethalskillzz.mytaxi.data.remote.ServiceFactory;
import com.lethalskillzz.mytaxi.di.ApplicationContext;
import com.lethalskillzz.mytaxi.di.DatabaseInfo;
import com.lethalskillzz.mytaxi.di.Local;
import com.lethalskillzz.mytaxi.di.Remote;
import com.lethalskillzz.mytaxi.utils.AppConstants;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    AppService provideAppService() {
        return ServiceFactory.createFrom(AppService.class, BuildConfig.BASE_URL);
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }



    @Singleton
    @Provides
    @Local
    AppDataSource provideAppLocalDataSource(BriteDatabase briteDatabase) {
        return new AppLocalDataSource(briteDatabase);
    }

    @Singleton
    @Provides
    @Remote
    AppDataSource provideAppRemoteDataSource(AppService service, PreferencesHelper preferencesHelper) {
        return new AppRemoteDataSource(service, preferencesHelper);
    }

    @Singleton
    @Provides
    @NonNull
    BriteDatabase provideBriteDatabase(SqlBrite sqlBrite, DbHelper dbHelper, Scheduler scheduler) {
        return sqlBrite.wrapDatabaseHelper(dbHelper, scheduler);
    }

    @Singleton
    @Provides
    @NonNull
    SqlBrite provideSqlBrite() {
        return new SqlBrite.Builder().build();
    }

    @Singleton
    @Provides
    @NonNull
    DbHelper provideDbHelper(@ApplicationContext Context context) {
        return new DbHelper(context);
    }

    @Provides
    @NonNull
    Scheduler provideScheduler() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @NonNull
    PreferencesHelper providePreferencesHelper(@ApplicationContext Context context) {
        return new PreferencesHelper(context);
    }
}

