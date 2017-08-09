package com.lethalskillzz.mytaxi.master;

import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.presentation.master.MasterActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MasterActivityUITest {

  @Rule
  public ActivityTestRule<MasterActivity> activityTestRule =
      new ActivityTestRule<>(MasterActivity.class);


  private IdlingResource mIdlingResource;

  @Before
  public void registerIdlingResource() {
    mIdlingResource = activityTestRule.getActivity().getIdlingResource();
    Espresso.registerIdlingResources(mIdlingResource);
  }

  @Test
  public void testToolbarDesign() {
    onView(withId(R.id.master_toolbar)).check(matches(isDisplayed()));

    onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.master_toolbar))));

    onView(withId(R.id.master_toolbar)).check(matches(withToolbarBackGroundColor()));
  }

  private Matcher<? super View> withToolbarBackGroundColor() {
    return new BoundedMatcher<View, View>(View.class) {
      @Override
      public boolean matchesSafely(View view) {
        final ColorDrawable buttonColor = (ColorDrawable) view.getBackground();

        return ContextCompat
                .getColor(activityTestRule.getActivity(), R.color.colorPrimary) ==
                buttonColor.getColor();
      }

      @Override
      public void describeTo(Description description) {
      }
    };
  }


  @Test
  public void clickOnRecyclerViewItem_opensDetailActivity() {

    onView(withId(R.id.fragment_master_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    onView(withId(R.id.map))
            .check(matches(isDisplayed()));
  }

  @After
  public void unregisterIdlingResource() {
    if (mIdlingResource != null) {
      Espresso.unregisterIdlingResources(mIdlingResource);
    }
  }
}
