package com.noisyninja.androidlistpoc.views.main

import android.view.View
import com.noisyninja.androidlistpoc.model.Me

/**
 * presenter interface
 * Created by sudiptadutta on 12/05/18.
 */
interface IMainPresenter {

    fun getList()
    fun getListGender(): String
    fun getListNation(): String
    fun reverseList(arrayList: ArrayList<Me>): ArrayList<Me>
    fun showDetail(view: View, me: Me)

}