package com.lethalskillzz.mytaxi.master;

import com.lethalskillzz.mytaxi.data.AppRepository;
import com.lethalskillzz.mytaxi.data.model.Data;
import com.lethalskillzz.mytaxi.presentation.master.MasterMvpView;
import com.lethalskillzz.mytaxi.presentation.master.MasterPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by ibrahimabdulkadir on 06/08/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MasterPresenterTest {

    @Mock
    MasterMvpView mMockMasterMvpView;
    @Mock
    AppRepository mMockAppRepository;

    private MasterPresenter<MasterMvpView> mMasterPresenter;
    private TestScheduler mTestScheduler;

    private Data data;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        mMasterPresenter = new MasterPresenter<>(
                mMockAppRepository,
                compositeDisposable);
        mMasterPresenter.onAttach(mMockMasterMvpView);
    }


    @Test
    public void testLoadPlacemarkFromRepoSuccess() {

        doReturn(Observable.just(data))
                .when(mMockAppRepository)
                .getData();

        mTestScheduler.triggerActions();

        verify(mMockMasterMvpView).showLoading();
        verify(mMockMasterMvpView).hideLoading();
        verify(mMockMasterMvpView).showPlacemarksList(data.placemarks());
    }


    @After
    public void tearDown() throws Exception {
        mMasterPresenter.onDetach();
    }

}
