package com.lethalskillzz.mytaxi.presentation.detail;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.data.model.Placemark;
import com.lethalskillzz.mytaxi.di.component.ActivityComponent;
import com.lethalskillzz.mytaxi.presentation.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.ButterKnife;

import static com.lethalskillzz.mytaxi.utils.AppConstants.BUNDLE_PLACEMARK;
import static com.lethalskillzz.mytaxi.utils.AppConstants.BUNDLE_PLACEMARKS;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

public class DetailFragment extends BaseFragment implements
        DetailMvpView, OnMapReadyCallback {

    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;


    @BindBool(R.bool.master_detail_mode)
    boolean masterDetailMode;

    private List<Placemark> mPlacemarks;
    private Placemark mPlacemark;


    public static DetailFragment newInstance(List<Placemark> placemarks, Placemark placemark) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(BUNDLE_PLACEMARKS, (ArrayList<? extends Parcelable>) placemarks);
        args.putParcelable(BUNDLE_PLACEMARK, placemark);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlacemarks = getArguments().getParcelableArrayList(BUNDLE_PLACEMARKS);
        mPlacemark = getArguments().getParcelable(BUNDLE_PLACEMARK);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

        }
        return view;
    }


    @Override
    protected void setUp(View view) {

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        for(Placemark placemark : mPlacemarks) {

            MarkerOptions markerOptions;
            markerOptions = new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(placemark.coordinates().get(1)),
                            Double.parseDouble(placemark.coordinates().get(0)))
            ).flat(true).title(placemark.name()).snippet(placemark.vin());

            if(placemark.name().equals(mPlacemark.name())) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            } else {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }

            googleMap.addMarker(markerOptions);
            googleMap.animateCamera(CameraUpdateFactory
                    .newLatLngZoom(new LatLng(Double.parseDouble(mPlacemark.coordinates().get(1)),
                            Double.parseDouble(mPlacemark.coordinates().get(0))), 15.0f));
        }
    }



}
