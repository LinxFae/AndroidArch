package com.noisyninja.androidlistpoc.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Id : BaseDTO() {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null
}
