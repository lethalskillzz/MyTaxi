package com.lethalskillzz.mytaxi.master;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.common.FragmentTestRule;
import com.lethalskillzz.mytaxi.presentation.master.MasterFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ibrahimabdulkadir on 06/08/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MasterFragmentUITest {


    @Rule
    public FragmentTestRule<MasterFragment> fragmentTestRule =
            new FragmentTestRule<>(MasterFragment.class);

    @Test
    public void fragment_can_be_instantiated() {

        // Launch the activity to make the fragment visible
        fragmentTestRule.launchActivity(null);

        // Then use Espresso to test the Fragment
        onView(withId(R.id.fragment_master_recycler_view)).check(matches(isDisplayed()));
    }


}
