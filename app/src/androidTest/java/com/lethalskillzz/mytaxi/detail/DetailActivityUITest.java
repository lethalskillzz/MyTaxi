package com.lethalskillzz.mytaxi.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.data.model.Placemark;
import com.lethalskillzz.mytaxi.presentation.detail.DetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetailActivityUITest {


  private static final String BUNDLE_PLACEMARKS = "bundlePlacemarks";
  private static final String BUNDLE_PLACEMARK = "bundlePlacemark";

  private Placemark placemark;
  private List<Placemark> placemarks = new ArrayList<>();

  @Rule
  public ActivityTestRule<DetailActivity> activityTestRule =
      new ActivityTestRule<DetailActivity>(DetailActivity.class){
            @Override
            protected Intent getActivityIntent() {
              Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
              Intent result = new Intent(targetContext, DetailActivity.class);
              result.putExtra(BUNDLE_PLACEMARK, placemark);
              result.putExtra(BUNDLE_PLACEMARKS, (ArrayList<? extends Parcelable>) placemarks);
              return result;
            }
          };



  @Test
  public void map_displayed() {

    onView(withId(R.id.map))
            .check(matches(isDisplayed()));

  }
}
