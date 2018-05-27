package com.noisyninja.androidlistpoc.modules

import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by sudiptadutta on 23/05/18.
 */

@RunWith(AndroidJUnit4::class)
class DatabaseModuleTest : BaseRepository() {

    @Before
    fun setup(){
        setupEnvironment()
        setUpMocks()
        setupDatabase()
    }
    @After
    fun teardown(){
        teardownDatabase()
    }

    @Test
    fun testInsert() {
        mIDatabase.databaseDao().insert(me1)

        val meList = mIDatabase.databaseDao().all.getValueBlocking()
        Assert.assertEquals(meList?.size, 1)
    }

    @Test
    fun testDelete() {
        mIDatabase.databaseDao().insert(me1)

        var meList = mIDatabase.databaseDao().all.getValueBlocking()

        Assert.assertEquals(meList?.size, 1)

        val retrievedMe = meList?.get(0)
        mIDatabase.databaseDao().delete(retrievedMe)

        meList = mIDatabase.databaseDao().all.getValueBlocking()
        Assert.assertEquals(meList?.size, 0)
    }
}
