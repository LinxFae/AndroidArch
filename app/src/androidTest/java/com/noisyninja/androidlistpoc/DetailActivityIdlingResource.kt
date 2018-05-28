package com.noisyninja.androidlistpoc

import android.support.test.espresso.IdlingResource
import android.support.v7.widget.RecyclerView

/**
 * Idling resource to deal with asynchronous calls and waiting
 * Created by sudiptadutta on 20/05/18.
 */

class DetailActivityIdlingResource(private val recyclerView: RecyclerView, private val tag: String) : IdlingResource {

    private lateinit var mCallback: IdlingResource.ResourceCallback

    override fun getName(): String {
        return DetailActivityIdlingResource::class.java.name + ":" + tag
    }

    override fun isIdleNow(): Boolean {
        val idle = recyclerView.adapter.itemCount > 1
        if (idle) {
            mCallback.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.mCallback = callback
    }
}