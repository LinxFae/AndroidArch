package com.noisyninja.androidlistpoc.views.main

import com.noisyninja.androidlistpoc.model.Me

/**
 * presenter interface
 * Created by sudiptadutta on 12/05/18.
 */
interface IMainPresenter {
    fun showDetail(me: Me)
    fun getList()
    fun getListGender():String
    fun getListNation():String
    fun reverseList(arrayList: ArrayList<Me>): ArrayList<Me>

}