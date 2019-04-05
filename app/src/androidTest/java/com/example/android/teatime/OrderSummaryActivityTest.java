package com.example.android.teatime;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {

    private static final Uri MAIL_URI = Uri.parse("mailto:");

    @Rule
    public IntentsTestRule<OrderSummaryActivity> mActivityRule = new IntentsTestRule<>(
            OrderSummaryActivity.class);

    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void clickSendEmail_StartsIntentWithCorrectData() {
        onView(withId(R.id.send_email_button))
                .perform(click());

        intended(allOf(hasAction(Intent.ACTION_SENDTO),
                hasData(MAIL_URI),
                hasExtra(Intent.EXTRA_SUBJECT, "Freshly Brewed from TeaTime"),
                hasExtra(Intent.EXTRA_TEXT, "I just ordered a delicious tea from TeaTime. Next time you are craving a tea, check them out!")));
    }

}
