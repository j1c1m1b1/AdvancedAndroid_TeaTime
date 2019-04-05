package com.example.android.teatime;

import com.example.android.teatime.model.Tea;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class MenuActivityScreenTest {

    @Rule
    public ActivityTestRule<MenuActivity> activityTestRule = new ActivityTestRule<>(MenuActivity.class);

    @Test
    public void clickGridViewItemWithPosition_OpensOrderActivity() {
        String teaName = "Green Tea";

        Espresso.onData(Matchers.anything())
                .inAdapterView(ViewMatchers.withId(R.id.tea_grid_view))
                .atPosition(1)
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.tea_name_text_view))
                .check(ViewAssertions.matches(ViewMatchers.withText(teaName)));
    }


    @Test
    public void clickGridViewItemMatchingText_OpensOrderActivity() {
        String teaName = "Black Tea";

        Espresso.onData(withName(Matchers.is(teaName)))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.tea_name_text_view))
                .check(ViewAssertions.matches(ViewMatchers.withText(teaName)));
    }

    private Matcher<Tea> withName(final Matcher<String> matcher) {
        return new TypeSafeMatcher<Tea>() {
            @Override
            protected boolean matchesSafely(Tea item) {
                return matcher.matches(item.getTeaName());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Matches tea");
            }
        };
    }


}
