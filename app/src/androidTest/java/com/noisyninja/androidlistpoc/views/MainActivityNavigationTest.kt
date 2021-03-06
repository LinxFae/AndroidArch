package com.noisyninja.androidlistpoc.views

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.codewaves.stickyheadergrid.StickyHeaderGridAdapter
import com.noisyninja.androidlistpoc.MainActivityIdlingResource
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.views.main.MainActivity
import kotlinx.android.synthetic.main.content_main.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by sudiptadutta on 18/05/18.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityNavigationTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = IntentsTestRule(MainActivity::class.java)
    lateinit var idlingResource: MainActivityIdlingResource

    @Before
    fun setup() {

        idlingResource = MainActivityIdlingResource(mActivityTestRule.activity.recyclerList, mActivityTestRule.activity.javaClass.simpleName)
        IdlingRegistry.getInstance().register(idlingResource)
        //mActivityTestRule.launchActivity(null)
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    /**
     * test all navigation pathways
     */
    @Test
    fun mainActivityTest() {

        val recyclerView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.recyclerList)))

        val floatingActionButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.fab)))
        sleepShort()
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<StickyHeaderGridAdapter.ViewHolder>(2, ViewActions.click()))
        sleepShort()
        Espresso.pressBack()
        floatingActionButton.perform(ViewActions.click())
        sleepShort()

        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<StickyHeaderGridAdapter.ViewHolder>(1, ViewActions.click()))
        sleepShort()
        Espresso.pressBack()
        floatingActionButton.perform(ViewActions.click())
        sleepShort()

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}