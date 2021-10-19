package com.example.samsarakmm.common.domain.constants


enum class COLOR(val rgb: Long) {
    DARK_BLUE(0xff1A237E),
    BURNT_ORANGE(0xffD84315),
    GREEN(0xff388E3C),
    DARK_RED(0xffB71C1C),
    DARK_LIME(0xff827717),
    LIGHT_BLUE(0xff0288D1),
    MAUVE(0xffBA68C8),
    BROWN(0xff795548),
    TEAL(0xff00897B)
}

val COLOR.prettyName: String
    get() = when(this) {
        COLOR.DARK_BLUE -> "DARK BLUE"
        COLOR.BURNT_ORANGE -> "BURNT ORANGE"
        COLOR.GREEN -> "GREEN"
        COLOR.DARK_RED -> "DARK RED"
        COLOR.DARK_LIME -> "DARK LIME"
        COLOR.LIGHT_BLUE -> "LIGHT BLUE"
        COLOR.MAUVE -> "MAUVE"
        COLOR.BROWN -> "BROWN"
        COLOR.TEAL -> "TEAL"
    }