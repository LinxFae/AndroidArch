package com.noisyninja.androidlistpoc.views

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.TestApplication
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

/**
 * Created by sudiptadutta on 19/05/18.
 */
@RunWith(AndroidJUnit4::class)
class DetailActivityTest : BaseTest() {

    /**
     *  launchActivity false as we are launching the activity manually in setup with intent
     */
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule<DetailActivity>(DetailActivity::class.java, true, false)
    lateinit var context: Context
    lateinit var app: TestApplication
    lateinit var detailActivity: DetailActivity
    lateinit var detailPresenter: DetailPresenter


    /**
     *  launching activity with USER_ID extra in intent
     */
    @Before
    fun setup() {
        context = InstrumentationRegistry.getTargetContext()
        app = InstrumentationRegistry.getTargetContext().applicationContext as TestApplication

        val intent = Intent()
        intent.putExtra(context.getString(R.string.user_id_key), jsonUserString)
        mActivityTestRule.launchActivity(intent)

        detailActivity = mActivityTestRule.activity
        detailPresenter = detailActivity.mIDetailPresenter as DetailPresenter
    }

    /**
     * check detail text is correct
     */
    @Test
    fun testImageDisplayed() {
        onView(ViewMatchers.withId(R.id.image)).check(matches(isDisplayed()))
    }

    /**
     * check detail text is correct
     */
    @Test
    fun testNameDisplayed() {
        onView(ViewMatchers.withId(R.id.textView)).check(matches(isDisplayed()))
    }

    /**
     * check detail text is correct
     */
    @Test
    fun testGenderIconDisplayed() {
        onView(ViewMatchers.withId(R.id.genderIcon)).check(matches(isDisplayed()))
    }

    /**
     * check detail text is correct
     */
    @Test
    fun testAddressDisplayed() {
        onView(ViewMatchers.withId(R.id.textView5)).check(matches(isDisplayed()))
    }


    val jsonUserString = "{\n" +
            "  \"cell\": \"(920)-158-3205\",\n" +
            "  \"dob\": \"1982-10-16 10:00:49\",\n" +
            "  \"email\": \"megan.morris@example.com\",\n" +
            "  \"gender\": \"female\",\n" +
            "  \"id\": {\n" +
            "    \"name\": \"SSN\",\n" +
            "    \"value\": \"072-12-1698\"\n" +
            "  },\n" +
            "  \"location\": {\n" +
            "    \"city\": \"tallahassee\",\n" +
            "    \"postcode\": \"78594\",\n" +
            "    \"state\": \"georgia\",\n" +
            "    \"street\": \"1055 ash dr\"\n" +
            "  },\n" +
            "  \"name\": {\n" +
            "    \"first\": \"megan\",\n" +
            "    \"last\": \"morris\",\n" +
            "    \"title\": \"mrs\"\n" +
            "  },\n" +
            "  \"nat\": \"US\",\n" +
            "  \"phone\": \"(245)-687-3811\",\n" +
            "  \"picture\": {\n" +
            "    \"large\": \"https://randomuser.me/api/portraits/women/87.jpg\",\n" +
            "    \"medium\": \"https://randomuser.me/api/portraits/med/women/87.jpg\",\n" +
            "    \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/87.jpg\"\n" +
            "  }\n" +
            "}"
}