package com.example.samsarakmm.android.ui.managehourview

import com.example.samsarakmm.domain.constants.HOUR_MODE

class HourViewModel(val hourInt: Int) {
    internal lateinit var taskNames: List<String>
    internal lateinit var hourMode: HOUR_MODE

    //:00 first quarter always active
    internal var firstSelectedTask: Int = 0
    internal var subFirstSelectedTaskIndex: ((Int) -> Unit)? = null

    //:15
    internal var secondIsActive: Boolean = false
    set(value) {
        field  = value
        subSecondIsActive?.invoke(value)
    }

    internal var subSecondIsActive: ((Boolean) -> Unit)? = null
    internal var secondSelectedTask: Int = 0
    internal var subSecondSelectedTaskIndex: ((Int) -> Unit)? = null

    //:30
    internal var thirdIsActive: Boolean = false
    internal var subThirdIsActive: ((Boolean) -> Unit)? = null
    internal var thirdSelectedTask: Int = 0
    internal var subThirdSelectedTaskIndex: ((Int) -> Unit)? = null

    //:45
    internal var fourthIsActive: Boolean = false
    internal var subFourthIsActive: ((Boolean) -> Unit)? = null
    internal var fourthSelectedTask: Int = 0
    internal var subFourthSelectedTaskIndex: ((Int) -> Unit)? = null
}