package com.noisyninja.androidlistpoc.views.detail


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.databinding.FragmentDetailBinding
import com.noisyninja.androidlistpoc.model.Me


open class DetailFragment : Fragment(), IDetailActivity {

    override fun setMe(result: Me) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityDetailBinding = DataBindingUtil.setContentView<FragmentDetailBinding>(requireActivity(), R.layout.fragment_detail)
        val user = savedInstanceState?.get(getString(R.string.user_id_key))

        val mIDetailPresenter: IDetailPresenter
        mIDetailPresenter = DetailPresenter(requireActivity().application as NinjaApp, activityDetailBinding)
        if (user is String) mIDetailPresenter.setMe(user)
    }

}
