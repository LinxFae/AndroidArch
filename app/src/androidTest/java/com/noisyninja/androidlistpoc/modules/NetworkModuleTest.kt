package com.noisyninja.androidlistpoc.modules

import android.support.test.runner.AndroidJUnit4
import com.noisyninja.androidlistpoc.BuildConfig
import com.noisyninja.androidlistpoc.layers.network.NetworkModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sudiptadutta on 23/05/18.
 */

@RunWith(AndroidJUnit4::class)
class NetworkModuleTest : BaseRepository() {


    @Test
    fun serverCallWithSuccess() {

        val url = BuildConfig.BASE_URL

        val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(mMockWebServer.url(url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        val remoteDataSource = NetworkModule(retrofit)

        val meResponse = remoteDataSource
                .getPeople(page, BuildConfig.RESULT_COUNT.toInt(), BuildConfig.NETSYNC_SEED_VALUE.toInt())

        meResponse.subscribe(mSubscriber)
        mSubscriber.assertNoErrors()
        mSubscriber.assertValue({ it ->
            it.people.size == 100
        })
        mSubscriber.assertValue({ it ->
            it.info != null
            it.people.get(0).name != null
            it.people.get(0).name.first != null
            it.people.get(0).name.title != null
            it.people.get(0).name.last != null
            it.people.get(0).picture.large != null
            it.people.get(0).picture.medium != null
            it.people[0].picture.thumbnail != null
        })
    }

    /**
     * test for network availability
     */
    @Test
    fun serverCallWithError() {
        //this is an invalid uri
        val url = BuildConfig.BASE_URL + BuildConfig.API_URI + "/"

        val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(mMockWebServer.url(url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        val remoteDataSource = NetworkModule(retrofit)

        val meResponse = remoteDataSource
                .getPeople(1, -0, BuildConfig.NETSYNC_SEED_VALUE.toInt())

        meResponse.subscribe(mSubscriber)
        mSubscriber.assertError({ t: Throwable -> t.message!!.isNotEmpty() })

    }
}
