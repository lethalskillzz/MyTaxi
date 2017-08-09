package com.lethalskillzz.mytaxi.presentation.master;


import com.lethalskillzz.mytaxi.data.model.Placemark;
import com.lethalskillzz.mytaxi.presentation.base.MvpView;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

public interface MasterMvpView extends MvpView {

    void showPlacemarksList(List<Placemark> placemarks);

    void showErrorMessage();

    void refreshPlacemark(boolean isRemote);
}