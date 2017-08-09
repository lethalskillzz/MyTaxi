package com.lethalskillzz.mytaxi.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lethalskillzz.mytaxi.data.local.db.PlacemarkPersistenceContract.PlacemarkEntry;
import com.lethalskillzz.mytaxi.data.model.Data;
import com.lethalskillzz.mytaxi.data.model.Placemark;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

public class DbUtils {

  public static String getSelectAllQuery(String tableName) {
    return "SELECT * FROM " + tableName;
  }

  public static String getSelectByIdQuery(String tableName, String column) {
    return "SELECT * FROM " + tableName + " WHERE " + column + " = ?";
  }


  public static ContentValues placemarkToContentValues(@NonNull Placemark placemark) {
    ContentValues cv = new ContentValues();

    Gson gson = new Gson();
    String coordinatesString= gson.toJson(placemark.coordinates());

    cv.put(PlacemarkEntry.COLUMN_ADDRESS, placemark.address());
    cv.put(PlacemarkEntry.COLUMN_COORDINATES, coordinatesString);
    cv.put(PlacemarkEntry.COLUMN_ENGINE_TYPE, placemark.engineType());
    cv.put(PlacemarkEntry.COLUMN_EXTERIOR, placemark.exterior());
    cv.put(PlacemarkEntry.COLUMN_FUEL, placemark.fuel());
    cv.put(PlacemarkEntry.COLUMN_INTERIOR, placemark.interior());
    cv.put(PlacemarkEntry.COLUMN_NAME, placemark.name());
    cv.put(PlacemarkEntry.COLUMN_VIN, placemark.vin());

    return cv;
  }


  public static Data placemarksFromCursor(@NonNull Cursor cursor) {

    List<Placemark> placemarks = new ArrayList<>();

    if (cursor.getCount() > 0) {
      cursor.moveToPosition(-1);

      while (cursor.moveToNext()) {

        String address = cursor.getString(cursor.getColumnIndexOrThrow(PlacemarkEntry.COLUMN_ADDRESS));
        String coordinatesString = cursor.getString(cursor.getColumnIndexOrThrow(PlacemarkEntry.COLUMN_COORDINATES));
        String engineType = cursor.getString(cursor.getColumnIndexOrThrow(PlacemarkEntry.COLUMN_ENGINE_TYPE));
        String exterior = cursor.getString(cursor.getColumnIndexOrThrow(PlacemarkEntry.COLUMN_EXTERIOR));
        int fuel = cursor.getInt(cursor.getColumnIndexOrThrow(PlacemarkEntry.COLUMN_FUEL));
        String interior = cursor.getString(cursor.getColumnIndexOrThrow(PlacemarkEntry.COLUMN_INTERIOR));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(PlacemarkEntry.COLUMN_NAME));
        String vin = cursor.getString(cursor.getColumnIndexOrThrow(PlacemarkEntry.COLUMN_VIN));


        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> coordinates = gson.fromJson(coordinatesString, type);

        Placemark placemark = Placemark.builder()
                .address(address)
                .coordinates(coordinates)
                .engineType(engineType)
                .exterior(exterior)
                .fuel(fuel)
                .interior(interior)
                .name(name)
                .vin(vin)
                .build();

        placemarks.add(placemark);
      }
    }

    return Data.builder()
            .placemarks(placemarks)
            .build();
  }

}
