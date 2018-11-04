package com.noisyninja.androidlistpoc.views.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.databinding.FragmentDetailBinding


open class DetailFragment : Fragment(), IDetailFragment {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentDetailBinding = DataBindingUtil.bind<FragmentDetailBinding>(view)!!
        val user = arguments?.get(getString(R.string.user_id_key))
        val mIDetailPresenter: IDetailPresenter
        mIDetailPresenter = DetailPresenter(requireActivity().application as NinjaApp, fragmentDetailBinding)
        if (user is String) mIDetailPresenter.setMe(user)
    }

}
