package com.lethalskillzz.mytaxi.presentation.master;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.data.idlingresource.AppIdlingResource;
import com.lethalskillzz.mytaxi.presentation.base.BaseActivity;
import com.lethalskillzz.mytaxi.utils.AppLogger;
import com.lethalskillzz.mytaxi.utils.FragmentUtils;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lethalskillzz.mytaxi.utils.AppConstants.BUNDLE_FRAGMENT_KEY;

public class MasterActivity extends BaseActivity {


    @BindView(R.id.master_toolbar)
    Toolbar mToolbar;

    @BindBool(R.bool.master_detail_mode)
    boolean masterDetailMode;

    @Nullable
    public AppIdlingResource mIdlingResource;

    private MasterFragment masterFragment;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MasterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        getIdlingResource();

        setUp();


        if (savedInstanceState != null) {

            AppLogger.d("notnull");
            masterFragment = (MasterFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, BUNDLE_FRAGMENT_KEY);
        } else {
            AppLogger.d("isnull");
            masterFragment =
                    (MasterFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.master_fragment_container);

            if (masterFragment != null) {
                masterFragment = (MasterFragment) getSupportFragmentManager()
                        .getFragment(savedInstanceState, BUNDLE_FRAGMENT_KEY);
            } else {
                masterFragment = MasterFragment.newInstance();
                FragmentUtils.addFragmentTo(getSupportFragmentManager(), masterFragment,
                        R.id.master_fragment_container);
            }
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (masterFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, BUNDLE_FRAGMENT_KEY, masterFragment);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void setUp() {

        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
            mToolbar.setTitle(R.string.master_label);
        }
    }


    @VisibleForTesting
    @NonNull
    public AppIdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new AppIdlingResource();
        }
        return mIdlingResource;
    }

}
