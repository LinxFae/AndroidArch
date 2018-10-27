package com.noisyninja.androidlistpoc.layers.network

import com.noisyninja.androidlistpoc.BuildConfig.NATIONS_VALUE
import com.noisyninja.androidlistpoc.BuildConfig.NETSYNC_SEED_VALUE
import com.noisyninja.androidlistpoc.model.MeResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * network module to make network calls
 * Created by sudiptadutta on 27/04/18.
 */

open class NetworkModule @Inject constructor(private val retrofit: Retrofit) {

    fun getPeople(count: Int): Observable<MeResponse> {
        return getCustomerObservable(count)
    }

    private fun getCustomerObservable(count: Int): Observable<MeResponse> {
        return retrofit.create(INetworkDao::class.java)
                .getPeople(1, count, NETSYNC_SEED_VALUE.toInt(), NATIONS_VALUE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
