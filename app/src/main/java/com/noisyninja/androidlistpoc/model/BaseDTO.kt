package com.noisyninja.androidlistpoc.model

import com.google.gson.Gson

open class BaseDTO {

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
