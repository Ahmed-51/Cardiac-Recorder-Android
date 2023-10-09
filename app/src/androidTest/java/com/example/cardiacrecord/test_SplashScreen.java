package com.example.cardiacrecord;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest

public class test_SplashScreen {
    @Rule
    public ActivityScenarioRule<SplashScreen> splash_screenActivityScenarioRule = new ActivityScenarioRule<SplashScreen>(SplashScreen.class);

    @Test
    public void testSplash() {
        onView(withId(R.id.mylayout)).check(matches(isDisplayed()));
    }
}
