package com.noisyninja.androidlistpoc.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.model.Me
import com.noisyninja.androidlistpoc.views.custom.MainAdapter
import kotlinx.android.synthetic.main.content_main.*


class MainFragment : Fragment(), IMainActivity {

    private var mResultList: ArrayList<Me> = ArrayList()
    lateinit var mIMainPresenter: IMainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.content_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setSupportActionBar(toolbar)
        mIMainPresenter = MainPresenter(this, activity?.application as NinjaApp)
        setupList()
        mIMainPresenter.getList()
/*

        view.findViewById<Button>(R.id.navigate_dest_bt)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.flow_step_one, null)
        )

        val options = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()

        view.findViewById<Button>(R.id.navigate_dest_bt)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.flow_step_one, null, options)
        }
*/

        //TODO STEP 7 - Update the OnClickListener to navigate using an action
//        view.findViewById<Button>(R.id.navigate_action_bt)?.setOnClickListener(
//                Navigation.createNavigateOnClickListener(R.id.next_action, null)
//        )

        //TODO ENDSTEP 7
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

        button.findViewById<Button>(R.id.button)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.next_action, null, null)
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
