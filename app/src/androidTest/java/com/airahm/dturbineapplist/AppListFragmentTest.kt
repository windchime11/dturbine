package com.airahm.dturbineapplist

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.airahm.dturbineapplist.adapter.AppListAdapter
import com.airahm.dturbineapplist.ui.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppListFragmentTest {
    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    val nameOfApp = "Mobile Strike"

    @Before
    fun init() {
        Espresso.onView(ViewMatchers.withId(R.id.frag_container))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun testShowUsers() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AppListAdapter.AppItemViewHolder>(
                    1,
                    ViewActions.click()
                )
            )
        Espresso.onView(ViewMatchers.withId(R.id.txvProductName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(nameOfApp)))
    }
}
