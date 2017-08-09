package com.lethalskillzz.mytaxi.presentation.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.presentation.base.BaseActivity;
import com.lethalskillzz.mytaxi.presentation.master.MasterActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;


public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);
    }


    @Override
    public void openMasterActivity() {
        startActivity(MasterActivity.getStartIntent(SplashActivity.this));
        finish();
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
