package edu.cuny.bc.cisc3171.mathapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

import androidx.test.espresso.Root;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.util.Log;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private View decorView;

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void testInputOp1Hint_mainActivity() {
        ViewInteraction interaction = onView(withId(R.id.edittext_op_1));
        interaction.check(matches(withHint(R.string.enter_an_integer)));
    }

    @Test
    public void testInputOp2Hint_mainActivity() {
        ViewInteraction interaction = onView(withId(R.id.edittext_op_2));
        interaction.check(matches(withHint(R.string.enter_an_integer)));
    }


    private void checkToast(String msg) {
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        uiDevice.waitForIdle();
        org.junit.Assert.assertTrue(uiDevice.hasObject(By.text(msg)));
    }

    @Test
    public void testNoInputs_mainActivity() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String msg = targetContext.getResources().getString(R.string.expect_two_inputs);

        // precondition (Given ...)
        ViewInteraction interaction1 = onView(withId(R.id.edittext_op_1));
        interaction1.perform(clearText());
        ViewInteraction interaction2 = onView(withId(R.id.edittext_op_2));
        interaction2.perform(clearText());

        // click button (When ...)
        activityScenarioRule.getScenario().onActivity(activity ->
                decorView = activity.getWindow().getDecorView());
        ViewInteraction interaction3 = onView(withId(R.id.button_average));
        interaction3.perform(click());
        onView(withText(R.string.expect_two_inputs))
                .check(matches(isDisplayed()));
    }
}