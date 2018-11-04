package com.noisyninja.androidlistpoc

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingResource

/**
 * Idling resource to deal with asynchronous calls and waiting
 * Created by sudiptadutta on 20/05/18.
 */

class MainActivityIdlingResource(private val recyclerView: RecyclerView, private val tag: String) : IdlingResource {

    private lateinit var mCallback: IdlingResource.ResourceCallback

    override fun getName(): String {
        return MainActivityIdlingResource::class.java.name + ":" + tag
    }

    override fun isIdleNow(): Boolean {
        var idle = false
        val adapter = recyclerView.adapter
        adapter?.let {
            idle = adapter.itemCount > 1
            if (idle) {
                mCallback.onTransitionToIdle()
            }
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.mCallback = callback
    }
}