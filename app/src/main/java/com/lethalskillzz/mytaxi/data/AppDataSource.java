package com.lethalskillzz.mytaxi.data;

import com.lethalskillzz.mytaxi.data.model.Data;

import io.reactivex.Observable;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

public interface AppDataSource {

    Observable<Data> getData();

    void saveData(Data data);
}
