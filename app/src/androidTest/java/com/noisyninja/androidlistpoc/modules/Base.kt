package com.noisyninja.androidlistpoc.modules

import android.content.Context
import android.support.test.InstrumentationRegistry
import com.noisyninja.androidlistpoc.TestApplication
import com.noisyninja.androidlistpoc.layers.UtilModule
import com.noisyninja.androidlistpoc.model.Me
import com.noisyninja.androidlistpoc.model.Name
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers


open class Base {

    /*@Rule
    @JvmField
    var mActivityTestRule = IntentsTestRule(MainActivity::class.java)*/
    lateinit var mContext: Context
    lateinit var mApp: TestApplication
    lateinit var mUtilModule: UtilModule

    lateinit var me1: Me
    lateinit var me2: Me
    lateinit var me3: Me
    lateinit var me4: Me

    val page = 1

    fun setupEnvironment() {
        mContext = InstrumentationRegistry.getTargetContext()
        mApp = InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
        mUtilModule = UtilModule(mContext)
    }

    fun setUpMocks() {
        me1 = Me(Name(FIRSTNAME1))
        me2 = Me(Name(FIRSTNAME2))
        me3 = Me(Name(FIRSTNAME3))
        me4 = Me(Name(FIRSTNAME4))
    }

    companion object {
        val FIRSTNAME1 = "firstName1"
        val FIRSTNAME2 = "firstName2"
        val FIRSTNAME3 = "firstName3"
        val FIRSTNAME4 = "firstName4"
    }

    protected fun setupLoopers() {
        //to make sure subscribeOn and observeOn run on same thread
        //async call becomes synchronous, thus waits for response
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { scheduler -> Schedulers.trampoline() }
    }

    protected fun tearDownLoopers() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

}