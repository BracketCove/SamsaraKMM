package com.example.samsarakmm.common.ui.managehourview

import com.example.samsarakmm.common.BaseViewModel
import com.example.samsarakmm.common.domain.Task
import com.example.samsarakmm.common.domain.constants.HOUR_MODE

class HourViewModel(val hourInt: Int): BaseViewModel() {
    lateinit var tasks: List<Task>
    lateinit var hourMode: HOUR_MODE

    //:00 first quarter always active
    var firstSelectedTask: Int = 0
    var subFirstSelectedTaskIndex: ((Int) -> Unit)? = null

    //:15
    var secondIsActive: Boolean = false
    set(value) {
        field  = value
        subSecondIsActive?.invoke(value)
    }
    var subSecondIsActive: ((Boolean) -> Unit)? = null
    var secondSelectedTask: Int = 0
    var subSecondSelectedTaskIndex: ((Int) -> Unit)? = null

    //:30
    var thirdIsActive: Boolean = false
        set(value) {
            field  = value
            subThirdIsActive?.invoke(value)
        }
    var subThirdIsActive: ((Boolean) -> Unit)? = null
    var thirdSelectedTask: Int = 0
    var subThirdSelectedTaskIndex: ((Int) -> Unit)? = null

    //:45
    var fourthIsActive: Boolean = false
        set(value) {
            field  = value
            subFourthIsActive?.invoke(value)
        }
    var subFourthIsActive: ((Boolean) -> Unit)? = null
    var fourthSelectedTask: Int = 0
    var subFourthSelectedTaskIndex: ((Int) -> Unit)? = null
}