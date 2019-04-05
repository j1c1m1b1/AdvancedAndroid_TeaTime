package com.example.android.teatime;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


@RunWith(AndroidJUnit4.class)
public class OrderActivityBasicTest {

    @Rule
    public ActivityTestRule<OrderActivity> activityTestRule = new ActivityTestRule<>(OrderActivity.class);

    @Test
    public void clickIncrementButton_ChangesQuantityAndCost() {
        Espresso.onView(ViewMatchers.withId(R.id.increment_button))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.quantity_text_view)).check(ViewAssertions.matches(ViewMatchers.withText("1")));
        Espresso.onView(ViewMatchers.withId(R.id.cost_text_view)).check(ViewAssertions.matches(ViewMatchers.withText("$5.00")));
    }

    @Test
    public void clickDecrementButton_ChangesQuantityAndCost() {
        Espresso.onView(ViewMatchers.withId(R.id.decrement_button))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.quantity_text_view)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        Espresso.onView(ViewMatchers.withId(R.id.cost_text_view)).check(ViewAssertions.matches(ViewMatchers.withText("$0.00")));
    }
}
