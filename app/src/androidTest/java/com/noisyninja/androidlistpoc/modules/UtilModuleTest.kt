package com.noisyninja.androidlistpoc.modules

import android.support.test.runner.AndroidJUnit4
import com.noisyninja.androidlistpoc.R
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by sudiptadutta on 23/05/18.
 */

@RunWith(AndroidJUnit4::class)
class UtilModuleTest : Base() {

    @Before
    fun setup() {
        setupEnvironment()
        setUpMocks()
    }

    @After
    fun teardown() {
    }

    @Test
    fun testStringRes() {
        Assert.assertNotNull(mUtilModule.getStringRes(R.string.app_name))
    }

    @Test
    fun testStringPref() {
        mUtilModule.setStringPref(FIRSTNAME1, FIRSTNAME2)
        Assert.assertNotNull(mUtilModule.getStringPref(FIRSTNAME1))
        Assert.assertEquals(mUtilModule.getStringPref(FIRSTNAME1), FIRSTNAME2)
        mUtilModule.deleteStringPref(FIRSTNAME1)
        Assert.assertNull(mUtilModule.getStringPref(FIRSTNAME1))
    }

    fun testLog() {

    }
}
