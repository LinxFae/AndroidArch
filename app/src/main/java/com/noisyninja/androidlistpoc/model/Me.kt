package com.noisyninja.androidlistpoc.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

import com.noisyninja.androidlistpoc.layers.Utils

import okhttp3.internal.Util


@Entity(tableName = "me")
class Me(@field:PrimaryKey
         @field:TypeConverters(DataConverter::class)
         var name: Name) : BaseDTO() {

    @TypeConverters(DataConverter::class)
    var picture: Picture? = null

    @TypeConverters(DataConverter::class)
    var id: Id? = null

    @TypeConverters(DataConverter::class)
    var location: Location? = null

    var email: String? = null
    var dob: String? = null
    var phone: String? = null
    var cell: String? = null
    var gender: String? = null
    var nat: String? = null

    val isMale: Boolean
        get() = gender == MALE

    companion object {

        val MALE = "male"
    }
}
