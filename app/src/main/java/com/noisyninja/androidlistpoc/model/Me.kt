package com.noisyninja.androidlistpoc.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


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

    @TypeConverters(DataConverter::class)
    var dob: Dob? = null

    var email: String? = null
    var phone: String? = null
    var cell: String? = null
    var gender: String? = null
    var nat: String? = null

    val isMale: Boolean
        get() = gender == MALE

    companion object {
        const val MALE = "male"
    }
}
