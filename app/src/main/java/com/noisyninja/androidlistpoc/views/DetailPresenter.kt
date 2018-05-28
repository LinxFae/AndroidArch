package com.noisyninja.androidlistpoc.views

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.databinding.ActivityDetailBinding
import com.noisyninja.androidlistpoc.databinding.ContentDetailBinding
import com.noisyninja.androidlistpoc.layers.UtilModule
import com.noisyninja.androidlistpoc.layers.database.viewmodel.MeViewModel
import com.noisyninja.androidlistpoc.layers.database.viewmodel.ViewModelFactory
import com.noisyninja.androidlistpoc.model.Me
import javax.inject.Inject


/**
 * main presenter
 * Created by sudiptadutta on 12/05/18.
 */

class DetailPresenter internal constructor(ninjaApp: NinjaApp, val mActivityDetailBinding: ActivityDetailBinding) : IDetailPresenter {

    @Inject
    lateinit var util: UtilModule

    init {
        ninjaApp.ninjaComponent.injectDetail(this)
    }

    override fun setMe(userId: String) {
        val me = util.fromJson(userId, Me::class.java)
        handleResponse(me)
    }

    private fun handleResponse(result: Me?) {
        mActivityDetailBinding.me = result
        mActivityDetailBinding.executePendingBindings()
    }
}
