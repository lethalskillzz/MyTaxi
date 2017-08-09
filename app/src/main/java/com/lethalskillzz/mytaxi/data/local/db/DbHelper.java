package com.lethalskillzz.mytaxi.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.lethalskillzz.mytaxi.utils.AppConstants.DB_NAME;


/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;

  public DbHelper(Context context) {
    super(context, DB_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(PlacemarkPersistenceContract.SQL_QUERY_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + PlacemarkPersistenceContract.PlacemarkEntry.TABLE_NAME);
    onCreate(db);
  }
}
