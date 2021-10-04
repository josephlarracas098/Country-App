package com.exam.country_app;


import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TestUI {
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    int length = 0;
    @Test
    public void test_main_activity_recycler_scroll_to_bottom() throws InterruptedException {
        onView(withId(R.id.id_progress_dialog)).check(matches(isDisplayed()));
        Thread.sleep(3000);
        onView(withId(R.id.rv_country)).check(matches(isDisplayed()));

        mainActivityScenarioRule.getScenario().onActivity(a -> {
             length = a.adapter.countries.length;
        });

        onView(withId(R.id.rv_country)).perform( RecyclerViewActions.scrollToPosition(length -1));
    }
}
