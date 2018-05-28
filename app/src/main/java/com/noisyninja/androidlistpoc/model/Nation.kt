package com.noisyninja.androidlistpoc.model

enum class Nation private constructor(val value: Int) {
    ALL(0),
    US(1),
    DK(2),
    FR(3),
    GB(4);

    operator fun next(): Nation {
        when (value) {
            0 -> return US
            1 -> return DK
            2 -> return FR
            3 -> return GB
            else -> return ALL
        }
    }

}
