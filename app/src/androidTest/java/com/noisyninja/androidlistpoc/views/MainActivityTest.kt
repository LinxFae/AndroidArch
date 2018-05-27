package com.noisyninja.androidlistpoc.views

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.noisyninja.androidlistpoc.NinjaIdlingResource
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.TestApplication
import junit.framework.Assert
import kotlinx.android.synthetic.main.content_main.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by sudiptadutta on 18/05/18.
 */

//@RunWith(AndroidJUnit4::class)
class MainActivityTest : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    lateinit var context: Context
    lateinit var app: TestApplication
    lateinit var mainActivity: MainActivity
    lateinit var mainPresenter: MainPresenter
    lateinit var idlingResource: NinjaIdlingResource

    @Before
    fun setup() {
        context = InstrumentationRegistry.getTargetContext()
        app = InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
        mainActivity = mActivityTestRule.activity
        mainPresenter = mainActivity.mIMainPresenter as MainPresenter

        idlingResource = NinjaIdlingResource(mActivityTestRule.activity.recyclerList, mActivityTestRule.activity.javaClass.simpleName)
        IdlingRegistry.getInstance().register(idlingResource)
        //mActivityTestRule.launchActivity(null)
    }

    @After
    fun teardown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    /**
     * test if adapter has first items clickable
     */
    @Test
    fun checkListItemClickableTest() {
        sleepMedium()
        onView(withId(R.id.recyclerList))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        sleepShort()
        //onView(withId(R.id.recyclerList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, swipeDown()))
    }

    /**
     * test if adapter has first items clickable
     */
    @Test
    fun checkListCountTest() {
        sleepShort()
        onView(withId(R.id.recyclerList)).check(matches(isDisplayed()))
        Assert.assertEquals(mainActivity.recyclerList.adapter.itemCount, 100)
    }

    /**
     * test if sort button is displayed and clickable, then click
     */
    @Test
    fun sortButtonTest() {
        sleepShort()
        onView(withId(R.id.fab)).check(matches(isDisplayed())).check(matches(isClickable())).perform(click())
    }
}
