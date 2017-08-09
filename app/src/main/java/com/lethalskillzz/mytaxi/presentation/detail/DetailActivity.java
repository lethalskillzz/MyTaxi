package com.lethalskillzz.mytaxi.presentation.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.data.model.Placemark;
import com.lethalskillzz.mytaxi.presentation.base.BaseActivity;
import com.lethalskillzz.mytaxi.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.lethalskillzz.mytaxi.utils.AppConstants.BUNDLE_FRAGMENT_KEY;
import static com.lethalskillzz.mytaxi.utils.AppConstants.BUNDLE_PLACEMARK;
import static com.lethalskillzz.mytaxi.utils.AppConstants.BUNDLE_PLACEMARKS;

public class DetailActivity extends BaseActivity implements DetailMvpView {

    @Inject
    DetailMvpPresenter<DetailMvpView> mPresenter;


    private List<Placemark> mPlacemarks;
    private Placemark mPlacemark;

    public static Intent getStartIntent(Context context, List<Placemark> placemarks, Placemark placemark) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putParcelableArrayListExtra(BUNDLE_PLACEMARKS, (ArrayList<? extends Parcelable>) placemarks);
        intent.putExtra(BUNDLE_PLACEMARK, placemark);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(DetailActivity.this);

        mPlacemark = getIntent().getExtras().getParcelable(BUNDLE_PLACEMARK);
        mPlacemarks = getIntent().getExtras().getParcelableArrayList(BUNDLE_PLACEMARKS);

        DetailFragment detailFragment =
                (DetailFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.detail_fragment_container);

        if (detailFragment != null) {
            detailFragment = (DetailFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, BUNDLE_FRAGMENT_KEY);
        } else {
            detailFragment = DetailFragment.newInstance(mPlacemarks, mPlacemark);
            FragmentUtils.addFragmentTo(getSupportFragmentManager(), detailFragment,
                    R.id.detail_fragment_container);
        }
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
