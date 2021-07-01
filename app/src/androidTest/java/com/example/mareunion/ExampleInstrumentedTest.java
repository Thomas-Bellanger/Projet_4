package com.example.mareunion;


import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
        onView(ViewMatchers.withId(R.id.textView))
                .check(matches(withText("Choix de la salle:")));
    }

    @Test
    public void addParticipant() {
        onView(ViewMatchers.withId(R.id.addReunion))
                .perform(click());
        onView(ViewMatchers.withId(R.id.editTextTextEmailAddress))
                .perform(typeText("participant 1"));
        onView(ViewMatchers.withId(R.id.plus))
                .perform(click());
        onView(ViewMatchers.withId(R.id.mail))
                .check(matches(withText("participant 1")));
    }

    @Test
    public void addReunion() {
        onView(ViewMatchers.withId(R.id.addReunion))
                .perform(click());
        onView(ViewMatchers.withId(R.id.button_planifier))
                .perform(click());
        onView(ViewMatchers.withId(R.id.salleText))
                .check(matches(withText("Peach")));
    }
}