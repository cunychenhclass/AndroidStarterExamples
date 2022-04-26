package edu.cuny.bc.cisc3171.teachaverageapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testEmptyInputs_MainActivity() {
        /*
         Scenario:
         Given no inputs are given on the app
         When the user clicks at the "Check Answer" button
         Expect the app displays an error message "Expected two integer inputs and the answer"
         */
        // 1. Given no inputs are given on the app
        ViewInteraction interaction;

        interaction = Espresso.onView(ViewMatchers.withId(R.id.edittext_op_1));
        interaction.perform(ViewActions.clearText());

        interaction = Espresso.onView(ViewMatchers.withId(R.id.edittext_op_2));
        interaction.perform(ViewActions.clearText());

        interaction = Espresso.onView(ViewMatchers.withId(R.id.edittext_user_answer));
        interaction.perform(ViewActions.clearText());

        // 2. When the user clicks at the "Check Answer" button
        interaction = Espresso.onView(ViewMatchers.withId(R.id.button_check_answer));
        interaction.perform(ViewActions.click());

        // 3. Expect the app displays an error message "Expected two integer inputs and the answer"
        interaction = Espresso.onView(ViewMatchers.withId(R.id.text_feedback));
        interaction.check(ViewAssertions.matches(ViewMatchers.withText(R.string.expect_inputs)));
    }


}