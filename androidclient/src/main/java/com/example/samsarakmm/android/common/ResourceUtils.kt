package com.example.samsarakmm.android.common

import com.example.samsarakmm.android.R
import com.example.samsarakmm.common.domain.constants.COLOR
import com.example.samsarakmm.common.domain.constants.ICON


fun ICON.toResId(): Int {
    return when (this) {
        ICON.FREE_TIME -> R.drawable.ic_free_time
        ICON.BREAK -> R.drawable.ic_break
        ICON.STUDY -> R.drawable.ic_study
        ICON.WORK -> R.drawable.ic_work
        ICON.EXERCISE -> R.drawable.ic_exercise
        ICON.MENTAL_CULTIVATION -> R.drawable.ic_bhavana
        ICON.EAT -> R.drawable.ic_eat
        ICON.SLEEP -> R.drawable.ic_rest
        ICON.SHOP -> R.drawable.ic_shop
        else -> R.drawable.ic_free_time
    }
}

fun COLOR.toResId(): Int {
    return when (this) {
        COLOR.DARK_BLUE -> R.color.darkBlue
        COLOR.BURNT_ORANGE -> R.color.burntOrange
        COLOR.GREEN -> R.color.green
        COLOR.DARK_RED -> R.color.red
        COLOR.DARK_LIME -> R.color.lime
        COLOR.LIGHT_BLUE -> R.color.lightBlue
        COLOR.MAUVE -> R.color.mauve
        COLOR.BROWN -> R.color.brown
        COLOR.TEAL -> R.color.teal
        else -> R.color.lightBlue

    }
}