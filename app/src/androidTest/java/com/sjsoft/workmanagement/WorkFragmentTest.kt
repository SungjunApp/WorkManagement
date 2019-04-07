package com.sjsoft.workmanagement

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.sjsoft.workmanagement.shift.work.WorkFragment
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.ClipData.Item
import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.matcher.ViewMatchers.*
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.StringContains


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class WorkFragmentTest {
    @get:Rule var activityTestRule = ActivityTestRule(MainActivity::class.java)

    private var mMainActivity: MainActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mMainActivity = activityTestRule.activity
    }

    //@Test
    fun testLaunch() {
        mMainActivity?.replaceFragmentInActivity(
            R.id.contentFrame,
            WorkFragment(),
            "WorkFragment"
        )

        Espresso.onView(ViewMatchers.withId(R.id.button_shift))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))

        Espresso.onView(ViewMatchers.withId(R.id.button_shift))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.start_shift)))

        Thread.sleep(2000)
        onView(withId(R.id.button_shift)).perform(click())
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.button_shift))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.end_shift)))

        Thread.sleep(2000)

        onView(withId(R.id.button_shift)).perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.button_shift))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.start_shift)))

    }

    @Test
    fun shiftButtonIsEnabled() {
        val f = WorkFragment()

        mMainActivity?.replaceFragmentInActivity(R.id.contentFrame, f, "WorkFragment")

        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.button_shift))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

}
