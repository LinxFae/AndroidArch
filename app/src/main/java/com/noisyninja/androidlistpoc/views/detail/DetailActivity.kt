package com.noisyninja.androidlistpoc.views.detail


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.databinding.ActivityDetailBinding


open class DetailActivity : AppCompatActivity() {

    lateinit var mIDetailPresenter: IDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val activityDetailBinding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        val user = intent.getStringExtra(getString(R.string.user_id_key))

        mIDetailPresenter = DetailPresenter(application as NinjaApp, activityDetailBinding)
        mIDetailPresenter.setMe(user)

    }

}
