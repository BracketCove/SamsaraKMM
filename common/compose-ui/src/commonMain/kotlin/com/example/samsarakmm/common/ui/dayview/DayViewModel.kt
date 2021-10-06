package com.example.samsarakmm.ui.dayview

import com.example.samsarakmm.common.BaseViewModel
import com.example.samsarakmm.common.domain.Day
import com.example.samsarakmm.common.domain.Tasks

/**
 * This class exists solely for the purpose of storing "front end session state". I could also have
 * the Fragment or the Logic class store this state, but I prefer to keep it separate.
 *
 * According to AAC and Android docs, A ViewModel is a hybrid between a logic class and a front end
 * model (caching front end session state).
 *
 * I use ViewModels like the name implies: it stores models necessary to render the View, so that
 * I do not need to reload them every time.
 */
class DayViewModel: BaseViewModel() {
    lateinit var day: Day
    lateinit var tasks: Tasks

    var subDay: ((Day) -> Unit)? = null
    var subTasks: ((Day) -> Unit)? = null
}