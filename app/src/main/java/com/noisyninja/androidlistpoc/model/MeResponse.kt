package com.noisyninja.androidlistpoc.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MeResponse : BaseDTO() {

    @SerializedName("results")
    @Expose
    var people: List<Me>? = null
    @SerializedName("info")
    @Expose
    var info: Info? = null

}
