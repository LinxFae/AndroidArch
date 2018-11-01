package com.noisyninja.androidlistpoc.modules

import androidx.test.runner.AndroidJUnit4
import com.noisyninja.androidlistpoc.BuildConfig
import com.noisyninja.androidlistpoc.BuildConfig.RESULT_COUNT
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by sudiptadutta on 23/05/18.
 */

@RunWith(AndroidJUnit4::class)
class NetworkModuleTest : BaseRepository() {

    @Before
    fun setup() {
        setupEnvironment()
        setupLoopers()
    }

    @After
    fun teardown() {
        tearDownLoopers()
    }

    @Test
    fun serverCallWithSuccess() {
        val url = BuildConfig.BASE_URL
        setupServer(url)

        val networkObservable = mNetworkModule.getPeople(RESULT_COUNT.toInt())
        networkObservable.subscribe(mSubscriber)

        mSubscriber.assertNoErrors()

        mSubscriber.assertValue({ it ->
            it.people?.size == 100
        })

        mSubscriber.assertValue({ it ->
            it.info != null
            it.people?.get(0)?.name != null
            it.people?.get(0)?.name?.first != null
            it.people?.get(0)?.name?.title != null
            it.people?.get(0)?.name?.last != null
            it.people?.get(0)?.picture?.large != null
            it.people?.get(0)?.picture?.medium != null
            it.people!![0].picture?.thumbnail != null
        })
    }

    /**
     * test for network availability
     */
    @Test
    fun serverCallWithError() {
        //this is an invalid uri
        val url = BuildConfig.BASE_URL + BuildConfig.API_URI + "/"
        setupServer(url)

        val networkObservable =
                mNetworkModule.getPeople(BuildConfig.RESULT_COUNT.toInt())

        networkObservable.subscribe(mSubscriber)
        mSubscriber.assertError({ t: Throwable -> t.message!!.isNotEmpty() })

    }
}
