package com.noisyninja.androidlistpoc.modules

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import com.noisyninja.androidlistpoc.layers.database.IDatabase
import com.noisyninja.androidlistpoc.layers.network.NetworkModule
import com.noisyninja.androidlistpoc.model.MeResponse
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


abstract class BaseRepository : Base() {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mSubscriber: TestObserver<MeResponse>
    lateinit var mNetworkModule: NetworkModule

    protected lateinit var mIDatabase: IDatabase

    fun setupServer(url: String) {
        mMockWebServer = MockWebServer()
        mSubscriber = TestObserver()

        val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        mNetworkModule = NetworkModule(retrofit)
    }

    fun teardownNetwork() {
    }

    fun setupDatabase() {

        mIDatabase = Room.inMemoryDatabaseBuilder(
                mContext,
                IDatabase::class.java)
                .build()
    }

    @Throws(IOException::class)
    fun teardownDatabase() {
        mIDatabase.close()
    }

}


/**
 * This function blocks the thread, waits for the value to be passed to observer and then returns it
 */
@Throws(InterruptedException::class)
fun <T> LiveData<T>.getValueBlocking(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)
    val innerObserver = Observer<T> {
        value = it
        latch.countDown()
    }
    observeForever(innerObserver)
    latch.await(2, TimeUnit.SECONDS)
    return value
}