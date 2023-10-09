package com.example.cardiacrecord;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;






@RunWith(JUnit4.class)
@LargeTest

public class test_MainActivity {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddButton() {

        /*
        add
         */

        onView(withId(R.id.add_btn)).perform(click());
        onView(withId(R.id.add_measurement_date_value)).perform(ViewActions.typeText("12/10/2021"));
        onView(withId(R.id.add_measurement_time_value)).perform(ViewActions.typeText("10:19"));
        onView(withId(R.id.add_systolic_value)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.add_diastolic_value)).perform(ViewActions.typeText("90"));
        pressBack();
        onView(withId(R.id.add_heart_rate_value)).perform(ViewActions.typeText("80"));
        pressBack();
        onView(withId(R.id.add_comment_value)).perform(ViewActions.typeText("UI test successful"));
        pressBack();
        onView(withId(R.id.save_btn)).perform(click());

        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();

        /*
        To update
         */
        // Match the text in an item below the fold and check that it's displayed.
        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, ActionView.clickChildViewWithId(R.id.edit_btn)));
        onView(withId(R.id.update_measurement_date_value)).perform(ViewActions.clearText());
        onView(withId(R.id.update_measurement_date_value)).perform(ViewActions.typeText("11/10/2021"));
        onView(withId(R.id.update_measurement_time_value)).perform(ViewActions.clearText());
        onView(withId(R.id.update_measurement_time_value)).perform(ViewActions.typeText("10:19"));
        onView(withId(R.id.update_systolic_value)).perform(ViewActions.clearText());
        onView(withId(R.id.update_systolic_value)).perform(ViewActions.typeText("110"));
        pressBack();
        onView(withId(R.id.update_diastolic_value)).perform(ViewActions.clearText());
        onView(withId(R.id.update_diastolic_value)).perform(ViewActions.typeText("80"));
        pressBack();
        onView(withId(R.id.update_heart_rate_value)).perform(ViewActions.clearText());
        onView(withId(R.id.update_heart_rate_value)).perform(ViewActions.typeText("70"));
        pressBack();
        onView(withId(R.id.update_comment_value)).perform(ViewActions.clearText());
        onView(withId(R.id.update_comment_value)).perform(ViewActions.typeText("CC"));
        pressBack();
        onView(withId(R.id.update_btn)).perform(click());

        /*
        To delete
        */

//        onView(withId(R.id.recyclerView)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(0, ActionView.clickChildViewWithId(R.id.delete_btn)));

//        onView(ViewMatchers.withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        Records.mcl.clear();
        new Utilities().saveData(InstrumentationRegistry.getInstrumentation().getContext());

    }

}
