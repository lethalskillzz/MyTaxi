package com.lethalskillzz.mytaxi.master;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.presentation.master.MasterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MasterActivityIntentTest {

  private static final String BUNDLE_PLACEMARKS = "bundlePlacemarks";
  private static final String BUNDLE_PLACEMARK = "bundlePlacemark";

  @Rule
  public IntentsTestRule<MasterActivity> intentsTestRule
          = new IntentsTestRule<>(MasterActivity.class);

  @Test
  public void clickOnRecyclerViewItem_runsMasterActivityIntent() {

    onView(ViewMatchers.withId(R.id.fragment_master_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    intended(allOf(
            hasExtraWithKey(BUNDLE_PLACEMARKS),
            hasExtraWithKey(BUNDLE_PLACEMARK)
    ));
  }

}
