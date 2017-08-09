package com.lethalskillzz.mytaxi.data.remote;

import com.lethalskillzz.mytaxi.data.model.Data;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

public interface AppService {

    @GET("/car2go/vehicles.json")
    Observable<Data> loadDataFromServer();

}