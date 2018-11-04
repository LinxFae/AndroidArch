package com.noisyninja.androidlistpoc.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.model.Me
import com.noisyninja.androidlistpoc.views.custom.MainAdapter
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), IMainFragment {

    private var mResultList: ArrayList<Me> = ArrayList()
    lateinit var mIMainPresenter: IMainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setSupportActionBar(toolbar)
        mIMainPresenter = MainPresenter(this, activity?.application as NinjaApp)
        setupList()
        mIMainPresenter.getList()
    }

    /**
     * setup UI widgets
     */
    private fun setupList() {

        val mLayoutManager = LinearLayoutManager(context)
        recyclerList.layoutManager = mLayoutManager
        recyclerList.adapter = MainAdapter(mResultList, mIMainPresenter)
        recyclerList.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        refresh_layout.setOnRefreshListener {
            mIMainPresenter.getList()
        }

/*        fab.setOnClickListener { view ->
            val nation = mIMainPresenter.getListNation()
            Snackbar.make(view, getString(R.string.filter, nation), Snackbar.LENGTH_LONG)
                    .show()
        }
        fab2.setOnClickListener { view ->
            val gender = mIMainPresenter.getListGender()
            Snackbar.make(view, getString(R.string.filter, gender), Snackbar.LENGTH_LONG)
                    //.setAction("Action", null)
                    .show()
        }*/
    }

    /**
     * sets the list items once data is fetched from network/database
     */
    override fun setList(result: ArrayList<Me>) {
        mResultList.clear()
        mResultList.addAll(result)
        handleShowError(false, null)
    }

    /**
     * show an error message if loading fails
     */
    private fun handleShowError(isError: Boolean, t: Throwable?) {
        activity?.runOnUiThread {
            recyclerList.adapter?.notifyDataSetChanged()
            refresh_layout.isRefreshing = false

            if (isError) {
                recyclerList.visibility = GONE
                //recyclerContainer.visibility = VISIBLE
                //recyclerText.text = t?.message
            } else {
                recyclerList.visibility = VISIBLE
                //recyclerContainer.visibility = GONE
            }
        }
    }
}
