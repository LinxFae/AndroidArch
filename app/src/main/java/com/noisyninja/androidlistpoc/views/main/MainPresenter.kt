package com.noisyninja.androidlistpoc.views.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.noisyninja.androidlistpoc.NinjaApp
import com.noisyninja.androidlistpoc.R
import com.noisyninja.androidlistpoc.layers.UtilModule
import com.noisyninja.androidlistpoc.layers.database.viewmodel.MeViewModel
import com.noisyninja.androidlistpoc.layers.database.viewmodel.ViewModelFactory
import com.noisyninja.androidlistpoc.model.Gender
import com.noisyninja.androidlistpoc.model.Me
import com.noisyninja.androidlistpoc.model.Nation
import javax.inject.Inject


/**
 * main presenter
 * Created by sudiptadutta on 12/05/18.
 */

class MainPresenter internal constructor(private val iMainFragment: IMainFragment, private val ninjaApp: NinjaApp) : IMainPresenter {

    @Inject
    lateinit var vmf: ViewModelFactory
    @Inject
    lateinit var util: UtilModule

    var gender: Gender = Gender.ALL
    var nation: Nation = Nation.ALL

    var meViewModel: MeViewModel
    var activity: FragmentActivity

    init {
        ninjaApp.ninjaComponent.injectMain(this)
        activity = (iMainFragment as Fragment).activity!!
        meViewModel = ViewModelProviders.of(activity, vmf).get(MeViewModel::class.java)
    }

    /**
     * fetches the list of users from viewmodel and toggles ALL/MALE/FEMALE gender
     */
    override fun getListGender(): String {
        Transformations.switchMap(meViewModel.getMeList()) { meList ->
            gender = gender.next()
            val newVal = MutableLiveData<List<Me>>()
            newVal.value = meList.filter { it ->
                when (gender) {
                    Gender.MALE -> it.isMale
                    Gender.FEMALE -> !it.isMale
                    else -> true
                }
            }
            newVal
        }.observe(activity, Observer<List<Me>> { result ->
            handleResponse(result)
        })
        return gender.toString()
    }

    /**
     * fetches the list of users from viewmodel and toggles ALL/MALE/FEMALE gender
     */
    override fun getListNation(): String {
        Transformations.switchMap(meViewModel.getMeList()) { meList ->
            nation = nation.next()
            val newVal = MutableLiveData<List<Me>>()
            newVal.value = meList.filter { it ->
                when (nation) {
                    Nation.US -> it.nat == Nation.US.toString()
                    Nation.DK -> it.nat == Nation.DK.toString()
                    Nation.FR -> it.nat == Nation.FR.toString()
                    Nation.GB -> it.nat == Nation.GB.toString()
                    else -> true
                }
            }
            newVal
        }.observe(activity, Observer<List<Me>> { result ->
            handleResponse(result)
        })
        return nation.toString()
    }

    /**
     * fetches the list of users from viewmodel which also acts as the database/network repository
     */
    override fun getList() {
        meViewModel.getMeList().observe(activity, Observer<List<Me>> { result ->
            handleResponse(result)
        })
    }

    /**
     * opens detail activity
     */
    override fun showDetail(view: View, me: Me) {
        var bundle = Bundle()
        bundle.putString(util.getStringRes(R.string.user_id_key), util.toJson(me))
        findNavController(view).navigate(R.id.next_action, bundle, null)
    }

    /**
     * reverses list order
     */
    override fun reverseList(arrayList: ArrayList<Me>): ArrayList<Me> {
        arrayList.reverse()
        return ArrayList(arrayList)
    }

    /**
     * handle response
     */
    fun handleResponse(result: List<Me>?) {
        if (result == null) {
            util.logI("null response")
            iMainFragment.setList(ArrayList())
        } else {
            util.logI("got response")
            iMainFragment.setList(ArrayList(result))
        }
    }

}
