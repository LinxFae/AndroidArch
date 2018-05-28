package com.noisyninja.androidlistpoc.views

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import android.view.View.GONE
import android.view.View.VISIBLE
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.model.Me
import com.noisyninja.androidlistpoc.views.custom.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


open class MainActivity : AppCompatActivity(), IMainActivity {

    private var mResultList: ArrayList<Me> = ArrayList()
    lateinit var mIMainPresenter: IMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mIMainPresenter = MainPresenter(this, application as NinjaApp)
        setupList()
        mIMainPresenter.getList()
    }

    /**
     * setup UI widgets
     */
    private fun setupList() {

        val mLayoutManager = LinearLayoutManager(this)
        recyclerList.layoutManager = mLayoutManager
        recyclerList.adapter = MainAdapter(mResultList, mIMainPresenter)
        recyclerList.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        refresh_layout.setOnRefreshListener {
            mIMainPresenter.getList()
        }

        fab.setOnClickListener { view ->
            val nation = mIMainPresenter.getListNation()
            Snackbar.make(view, getString(R.string.filter, nation), Snackbar.LENGTH_LONG)
                    .show()
        }
        fab2.setOnClickListener { view ->
            val gender = mIMainPresenter.getListGender()
            Snackbar.make(view, getString(R.string.filter, gender), Snackbar.LENGTH_LONG)
                    //.setAction("Action", null)
                    .show()
        }
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
        runOnUiThread({
            recyclerList.adapter = MainAdapter(mResultList, mIMainPresenter)
            recyclerList.adapter.notifyDataSetChanged()
            refresh_layout.isRefreshing = false

            if (isError) {
                recyclerList.visibility = GONE
                recyclerContainer.visibility = VISIBLE
                recyclerText.text = t?.message
            } else {
                recyclerList.visibility = VISIBLE
                recyclerContainer.visibility = GONE
            }
        })
    }

}
