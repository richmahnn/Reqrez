package com.lubulwa.reqrez.ui.component.user

import android.content.Intent
import android.text.format.DateUtils
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.lubulwa.reqrez.R
import com.lubulwa.reqrez.ui.component.user.idling.ElapsedTimeIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class CreateUserActivityTest {

    private lateinit var activityTestRule: ActivityTestRule<CreateUserActivity>

    @Before
    fun setUp() {
        activityTestRule = ActivityTestRule(CreateUserActivity::class.java)

        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS);
    }

    @After
    fun tearDown() {
    }

    @Test
    fun checkUsernameEditTextIsDisplayed() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.et_username_create)).check(matches(isDisplayed()))
    }

    @Test
    fun checkJobEditTextIsDisplayed() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.et_job_create)).check(matches(isDisplayed()))
    }

    @Test
    fun checkErrorMessageIsDisplayedForEmptyData() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.btn_create_user)).check(matches(isDisplayed())).perform(click())
        onView(withText("Name and Job should not be blank!")).check(matches(isDisplayed()))
    }

    @Test
    fun checkCreateUserSuccess() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.et_username_create)).perform(typeText("John Doe"), closeSoftKeyboard())
        onView(withId(R.id.et_job_create)).perform(typeText("Leader"), closeSoftKeyboard())
        onView(withId(R.id.btn_create_user)).check(matches(isDisplayed())).perform(scrollTo(), click())

        waitFor(7) //7 seconds because of network speed
    }

    private fun waitFor(waitingTime: Int) {

        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout((waitingTime * 2).toLong(), TimeUnit.SECONDS)
        IdlingPolicies.setIdlingResourceTimeout((waitingTime * 2).toLong(), TimeUnit.SECONDS)

        val idlingResource = ElapsedTimeIdlingResource(DateUtils.SECOND_IN_MILLIS * waitingTime)
        Espresso.registerIdlingResources(idlingResource)

        onView(withText("User John Doe has been successfully created")).check(matches(isDisplayed()))

        Espresso.unregisterIdlingResources(idlingResource)
    }

}