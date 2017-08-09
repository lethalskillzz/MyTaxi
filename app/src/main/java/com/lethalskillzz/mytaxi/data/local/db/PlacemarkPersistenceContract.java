package com.lethalskillzz.mytaxi.data.local.db;

/**
 * Created by ibrahimabdulkadir on 20/06/2017.
 */


public class PlacemarkPersistenceContract {

  private PlacemarkPersistenceContract() {
  }

  public static abstract class PlacemarkEntry {

    public static final String TABLE_NAME = "placemark";

    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_COORDINATES = "coordinates";
    public static final String COLUMN_ENGINE_TYPE = "engineType";
    public static final String COLUMN_EXTERIOR = "exterior";
    public static final String COLUMN_FUEL = "fuel";
    public static final String COLUMN_INTERIOR = "interior";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VIN = "vin";
  }

  static final String SQL_QUERY_CREATE =
      "CREATE TABLE " + PlacemarkEntry.TABLE_NAME + " ("
          + PlacemarkEntry.COLUMN_ADDRESS + " TEXT NOT NULL,"
          + PlacemarkEntry.COLUMN_COORDINATES + " TEXT NOT NULL,"
          + PlacemarkEntry.COLUMN_ENGINE_TYPE + " TEXT NOT NULL,"
          + PlacemarkEntry.COLUMN_EXTERIOR + " TEXT NOT NULL,"
          + PlacemarkEntry.COLUMN_FUEL + " INTEGER NOT NULL,"
          + PlacemarkEntry.COLUMN_INTERIOR + " TEXT NOT NULL,"
          + PlacemarkEntry.COLUMN_NAME + " TEXT NOT NULL,"
          + PlacemarkEntry.COLUMN_VIN + " TEXT NOT NULL"
          + ")";
}
