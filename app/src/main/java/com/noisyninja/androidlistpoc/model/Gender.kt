package com.noisyninja.androidlistpoc.model

enum class Gender private constructor(val value: Int) {
    ALL(0),
    MALE(1),
    FEMALE(2);

    operator fun next(): Gender {
        when (value) {
            0 -> return MALE
            1 -> return FEMALE
            else -> return ALL
        }
    }
}
