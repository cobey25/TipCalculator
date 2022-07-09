package com.example.android.tipcalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    //In order to interact with the activity, your test case must first launch it
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip(){
        //input from user
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())

        //button clicked
        onView(withId(R.id.calculate_button))
            .perform(click())

        //check if you get result expected
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.00"))))
    }

    @Test
    fun calculate_18_percent_tip_no_roundUp(){

        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("60.00"))
            .perform(ViewActions.closeSoftKeyboard())

        //click the radio button 18%
        onView(withId(R.id.option_eighteen_percent))
            .perform(click())

        //toggling the round up switch key so we can test without rounding up
        onView(withId(R.id.round_up_switch))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.8"))))
    }

    @Test
    fun calculate_18_percent_tip_roundUp() {

        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("60"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.option_eighteen_percent))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("11"))))
    }

    @Test
    fun calculate_15_percent_tip_no_roundUp(){

        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("70.00"))
            .perform(ViewActions.closeSoftKeyboard())

        //click the radio button 15%
        onView(withId(R.id.option_fifteen_percent))
            .perform(click())

        //toggling the round up switch key so we can test without rounding up
        onView(withId(R.id.round_up_switch))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.5"))))
    }

    @Test
    fun calculate_15_percent_tip_roundUp() {

        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("70"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.option_fifteen_percent))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("11"))))
    }

}