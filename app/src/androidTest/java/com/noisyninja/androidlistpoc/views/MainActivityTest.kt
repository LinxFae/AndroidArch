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
    fun checkListCountTest() {
        onView(withId(R.id.recyclerList))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
    }

    /**
     * test if sort button is displayed and clickable, then click
     */
    @Test
    fun sortButtonTest() {
        onView(withId(R.id.fab)).check(matches(isDisplayed())).check(matches(isClickable())).perform(click())
    }

/*
    @Test
    fun testGetListCalled() {
        val recyclerView = onView(
                allOf(withId(R.id.recyclerList)))
        sleepShort()
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        verify(mainPresenter).showDetail(Matchers.any())
    }
*/

    /**
     * test if first element displayed
     *//*
    @Test
    fun clickFirstEntryTest() {
        val recyclerView = onView(
                allOf(withId(R.id.recyclerList)))
        sleepShort()
        recyclerView.perform(actionOnItemAtPosition<StickyHeaderGridAdapter.ViewHolder>(1, click()))

        Intents.intended(allOf(
                //hasAction(equalTo(Intent.ACTION_VIEW)),
                //hasCategories(hasItem(equalTo(Intent.CATEGORY_BROWSABLE))),
                //hasData(hasHost(equalTo("www.google.com"))),
                hasFlag(Intent.FLAG_ACTIVITY_NEW_TASK),
                hasExtras(allOf(
                        hasEntry(equalTo(context.getString(R.string.user_id_key)), any(String::class.java)))),
                IntentMatchers.hasComponent(DetailActivity::class.java.canonicalName)))
    }*/

}
