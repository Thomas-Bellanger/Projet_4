package com.example.mareunion;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Instrumented test, which will execute on an Android device.
 */

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    @Test
    public void reunionListIsempty() {
        onView(ViewMatchers.withId(R.id.View))
                .check(matches(hasChildCount(0)));
    }

    @Test
    public void addReunionLaunch() {
        onView(ViewMatchers.withId(R.id.addReunion))
                .perform(click());
        onView(ViewMatchers.withId(R.id.salleText))
                .check(matches(withText("Peach")));
    }

    @Test
    public void addParticipant() {
        onView(ViewMatchers.withId(R.id.addReunion))
                .perform(click());
        onView(ViewMatchers.withId(R.id.editTextTextEmailAddress))
                .perform(typeText("participant 1"));
        onView(ViewMatchers.withId(R.id.plus))
                .perform(click());
        onView(ViewMatchers.withId(R.id.participantsRecyclerView))
                .check(matches(withText("participant 1")));
    }

    @Test
    public void addReunion() {
        onView(ViewMatchers.withId(R.id.addReunion))
                .perform(click());
        onView(ViewMatchers.withId(R.id.button_planifier))
                .perform(click());
        onView(ViewMatchers.withId(R.id.View))
                .check(matches(withText("Reunion 0")));
    }
}