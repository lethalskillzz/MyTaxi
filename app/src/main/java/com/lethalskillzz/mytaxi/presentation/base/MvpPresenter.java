package com.lethalskillzz.mytaxi.presentation.base;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

import retrofit2.HttpException;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(HttpException e);
}
