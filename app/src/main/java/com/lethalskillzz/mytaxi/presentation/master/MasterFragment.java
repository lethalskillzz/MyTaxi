package com.lethalskillzz.mytaxi.presentation.master;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.data.model.Placemark;
import com.lethalskillzz.mytaxi.di.component.ActivityComponent;
import com.lethalskillzz.mytaxi.presentation.base.BaseFragment;
import com.lethalskillzz.mytaxi.presentation.detail.DetailActivity;
import com.lethalskillzz.mytaxi.presentation.detail.DetailFragment;
import com.lethalskillzz.mytaxi.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lethalskillzz.mytaxi.utils.AppConstants.BUNDLE_PLACEMARKS;

/**
 * Created by ibrahimabdulkadir on 04/08/2017.
 */

public class MasterFragment extends BaseFragment implements
        MasterMvpView, MasterAdapter.Callback {

    @Inject
    MasterMvpPresenter<MasterMvpView> mPresenter;
    @Inject
    MasterAdapter mMasterAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    DividerItemDecoration mDividerItemDecoration;


    @BindView(R.id.fragment_master_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fragment_master_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_master_refresh_text)
    TextView mRefreshText;


    @BindBool(R.bool.master_detail_mode)
    boolean masterDetailMode;
    @BindString(R.string.error_default)
    String errorMessage;

    private List<Placemark> mPlacemarks;


    public static MasterFragment newInstance() {
        Bundle args = new Bundle();
        MasterFragment fragment = new MasterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            mMasterAdapter.setCallback(this);
            mMasterAdapter.setHasStableIds(true);

            if (savedInstanceState != null) {
                if(savedInstanceState.containsKey(BUNDLE_PLACEMARKS)) {
                    mPlacemarks = savedInstanceState.getParcelableArrayList(BUNDLE_PLACEMARKS);

                    showPlacemarksList(mPlacemarks);
                }
            } else {
                refreshPlacemark(true);
            }

        }
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(BUNDLE_PLACEMARKS, (ArrayList<? extends Parcelable>) mPlacemarks);
    }


    @Override
    protected void setUp(View view) {

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMasterAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(() -> refreshPlacemark(true));

    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onPlacemarkClick(Placemark placemark) {
        if (masterDetailMode) {
            DetailFragment fragment =
                    DetailFragment.newInstance(mPlacemarks, placemark);

            FragmentUtils.replaceFragmentIn(
                    getActivity().getSupportFragmentManager(),
                    fragment,
                    R.id.detail_fragment_container);
        } else {
            startActivity(DetailActivity.getStartIntent(getContext(), mPlacemarks, placemark));
        }
    }

    @Override
    public void refreshPlacemark(boolean isRemote) {

        if (isRemote && isNetworkConnected()) {

            mRefreshText.setVisibility(View.GONE);
            mPresenter.loadPlacemarkFromRepo(true, ((MasterActivity)getActivity()).mIdlingResource);

        } else {

            mPresenter.loadPlacemarkFromRepo(false, ((MasterActivity)getActivity()).mIdlingResource);
            //hideLoading();
            showMessage(getString(R.string.error_no_internet));
        }
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }



    @Override
    public void showPlacemarksList(List<Placemark> placemarks) {
        mRefreshText.setVisibility(View.GONE);
        mPlacemarks = placemarks;
        mMasterAdapter.refreshPlacemarks(placemarks);
    }



    @Override
    public void showErrorMessage() {
        // User should not see this
        onError(errorMessage);
    }


}
