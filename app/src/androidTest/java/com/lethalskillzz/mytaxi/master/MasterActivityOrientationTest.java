package com.lethalskillzz.mytaxi.master;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.presentation.master.MasterActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ibrahimabdulkadir on 06/08/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MasterActivityOrientationTest {

    private static final String TAG = "OrientationChangeTest";

    @Rule
    public ActivityTestRule<MasterActivity> activityTestRule
            = new ActivityTestRule<>(MasterActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void testRandomBehavior() {

        onView(withId(R.id.fragment_master_recycler_view)).check(matches(isDisplayed()));

        changeOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        clickOnRandomItem(R.id.fragment_master_recycler_view);

        onView(withId(R.id.map))
                .check(matches(isDisplayed()));

        pressBack();

        changeOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    private void changeOrientation(int orientation) {
        activityTestRule.getActivity().setRequestedOrientation(orientation);
    }

    private void clickOnRandomItem(int viewId) {
        int x = getRandomRecyclerPosition(viewId);

        Log.d(TAG, "clickOnRandomItem: " + x);

        onView(withId(viewId))
                .perform(RecyclerViewActions.scrollToPosition(x),
                        RecyclerViewActions.actionOnItemAtPosition(x, click()));
    }

    private int getRandomRecyclerPosition(int recyclerId) {
        Random ran = new Random();
        RecyclerView recyclerView = (RecyclerView) activityTestRule.getActivity().findViewById(recyclerId);

        int n = (recyclerView == null || recyclerView.getAdapter().getItemCount() <= 0)
                ? 1
                : recyclerView.getAdapter().getItemCount() / 2;

        int next;

        Log.d(TAG, "getRandomRecyclerPosition: " + n);

        next = (n != 0)
                ? ran.nextInt(n)
                : 0;

        return next;
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}
