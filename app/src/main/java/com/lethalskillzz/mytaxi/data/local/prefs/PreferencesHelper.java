package com.lethalskillzz.mytaxi.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.lethalskillzz.mytaxi.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.lethalskillzz.mytaxi.utils.AppConstants.PREFS_FILE_NAME;


/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

@Singleton
public class PreferencesHelper {


    private static final String PREFERENCE_IS_SYNCED = "isSynced";

    private final SharedPreferences mSharedPreferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }


    public void setIsSynced(boolean flag) {
        mSharedPreferences.edit().putBoolean(PREFERENCE_IS_SYNCED, flag).apply();
    }

    public boolean isSynced() {
        return mSharedPreferences.getBoolean(PREFERENCE_IS_SYNCED, false);
    }


}
