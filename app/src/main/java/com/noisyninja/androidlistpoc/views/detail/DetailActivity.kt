package com.noisyninja.androidlistpoc.views.detail


import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.databinding.ActivityDetailBinding
import com.noisyninja.androidlistpoc.model.Me


open class DetailFragment : Fragment(), IDetailActivity {

    override fun setMe(result: Me) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
