package com.lethalskillzz.mytaxi.splash;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.presentation.splash.SplashActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ibrahimabdulkadir on 06/08/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashActivityUITest {

    @Rule
    public ActivityTestRule<SplashActivity> activityTestRule =
            new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splash_screen_is_displayed() {

        onView(withId(R.id.splash_image))
                .check(matches(isDisplayed()));

        onView(withText(R.string.splash_text)).check(matches(withParent(withId(R.id.splash_layout))));
    }


}
